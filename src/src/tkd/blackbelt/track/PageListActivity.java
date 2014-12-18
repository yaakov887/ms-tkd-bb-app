package tkd.blackbelt.track;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParserException;

import tkd.blackbelt.Checklist;
import tkd.blackbelt.ChecklistXMLHandler;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * An activity representing a list of Pages. This activity has different
 * presentations for handset and tablet-size devices. On handsets, the activity
 * presents a list of items, which when touched, lead to a
 * {@link PageDetailActivity} representing item details. On tablets, the
 * activity presents the list of items and item details side-by-side using two
 * vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link PageListFragment} and the item details (if present) is a
 * {@link PageDetailFragment}.
 * <p>
 * This activity also implements the required {@link PageListFragment.Callbacks}
 * interface to listen for item selections.
 */
public class PageListActivity extends FragmentActivity implements
		PageListFragment.Callbacks {

	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private boolean mTwoPane;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_page_list);
		Contents contents = new Contents ();
		Contents.addItem (contents.new ContentItem (R.string.physical_fitness, 
				getResources ().getString(R.string.physical_fitness)));
		Contents.addItem (contents.new ContentItem (R.string.food_journal, 
				getResources ().getString(R.string.food_journal)));
		Contents.addItem (contents.new ContentItem (R.string.weekly_journal, 
				getResources ().getString(R.string.weekly_journal)));
		Contents.addItem (contents.new ContentItem (R.string.creative_form, 
				getResources ().getString(R.string.creative_form)));
		Contents.addItem (contents.new ContentItem (R.string.class_assisting, 
				getResources ().getString(R.string.class_assisting)));
		Contents.addItem (contents.new ContentItem (R.string.writing_checklist, 
				getResources ().getString(R.string.writing_checklist)));
		Contents.addItem (contents.new ContentItem (R.string.testing_checklist, 
				getResources ().getString(R.string.testing_checklist)));
		Contents.addItem (contents.new ContentItem (R.string.important_dates, 
				getResources ().getString(R.string.important_dates)));
		Contents.addItem (contents.new ContentItem (R.string.notes, 
				getResources ().getString(R.string.notes)));

		if (findViewById(R.id.page_detail_container) != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-large and
			// res/values-sw600dp). If this view is present, then the
			// activity should be in two-pane mode.
			mTwoPane = true;

			// In two-pane mode, list items should be given the
			// 'activated' state when touched.
			((PageListFragment) getSupportFragmentManager().findFragmentById(
					R.id.page_list)).setActivateOnItemClick(true);
		}

		try {
			File file = new File(this.getFilesDir(), "checklist.xml");
			if (file.exists()){

				FileInputStream input = new FileInputStream (file);
				ChecklistXMLHandler ps = new ChecklistXMLHandler ();
				Checklist list = ps.getChecklist ();

				ps.parse (input);
				input.close ();

				for (Checklist.Items item : Checklist.Items.values ()) {
					System.out.println(item.toString () + " : " + list.isCompleted (item));

					if (item.ordinal () % 2 == 0) {
						list.itemCompleted (item);
					}
					else {
						list.resetItem (item);
					}
				}
				FileOutputStream output = new FileOutputStream (file);

				ps.generateChecklistFile (output);
				output.close ();
			}
			else {
				file.createNewFile();
				ChecklistXMLHandler ps = new ChecklistXMLHandler ();

				FileOutputStream output = new FileOutputStream (file);

				ps.generateChecklistFile (output);
				output.close ();
			}

			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO: If exposing deep links into your app, handle intents here.
	}

	/**
	 * Callback method from {@link PageListFragment.Callbacks} indicating that
	 * the item with the given ID was selected.
	 */
	@Override
	public void onItemSelected(String id) {
		if (mTwoPane) {
			// In two-pane mode, show the detail view in this activity by
			// adding or replacing the detail fragment using a
			// fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putString(PageDetailFragment.ARG_ITEM_ID, id);
			PageDetailFragment fragment = new PageDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.page_detail_container, fragment).commit();

		} else {
			// In single-pane mode, simply start the detail activity
			// for the selected item ID.
			Intent detailIntent = new Intent(this, PageDetailActivity.class);
			detailIntent.putExtra(PageDetailFragment.ARG_ITEM_ID, id);
			startActivity(detailIntent);
		}
	}
}
