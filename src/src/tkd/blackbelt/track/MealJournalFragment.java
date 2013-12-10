package tkd.blackbelt.track;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import tkd.blackbelt.*;

public class MealJournalFragment extends Fragment {

	private ExpandableListView mealsview;
	private boolean expandedGroups[];
	
	@Override
	public void onPause() {
		super.onPause();
		
		expandedGroups = new boolean[MealsEntry.MealValues.length];
		for(int i = 0; i < MealsEntry.MealValues.length; i++) {
			expandedGroups[i] = mealsview.isGroupExpanded(i);
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		
		for (int i = 0; i < MealsEntry.MealValues.length; i++){
			mealsview.expandGroup(i);
		}
	}

	public static final String entryKey = "ENTRYNO";
	private MealsEntry mealsEntry		= null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
		if (savedInstanceState.containsKey(entryKey)) {
			int entryNum = savedInstanceState.getInt(entryKey);
			
			mealsEntry = MealsJournal.getJournal().getEntry(entryNum);
		}
		else {
			mealsEntry = MealsJournal.getJournal().getEntry(1);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (mealsEntry != null) {
			View contentview = inflater.inflate(R.layout.food_journal_entry, container);
			mealsview = (ExpandableListView)contentview.findViewById(R.id.journal_entry_list);
			ExpandableListAdapter adapter = new ExpandableMealViewAdaptor (null, mealsEntry);
			mealsview.setAdapter(adapter);
		}
		return null;
	}
	
}
