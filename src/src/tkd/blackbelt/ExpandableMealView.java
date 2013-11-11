package tkd.blackbelt;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils.TruncateAt;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.List;

import tkd.blackbelt.track.R;

public class ExpandableMealView extends BaseExpandableListAdapter {

	public enum Meals {Breakfast, Lunch, Dinner, SnackOne, SnackTwo};
	
	private final Meals[] mealValues = Meals.values();
	
	private Context context;
	private List<String> breakfast;
	private List<String> lunch;
	private List<String> dinner;
	private List<String> snack_1;
	private List<String> snack_2;
	
	public ExpandableMealView(Context context) {
        this.context = context;
    }

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		switch (mealValues[groupPosition]){
			case Breakfast:
				return breakfast.get(childPosition);
			case Lunch:
				return lunch.get(childPosition);
			case Dinner:
				return dinner.get(childPosition);
			case SnackOne:
				return snack_1.get(childPosition);
			case SnackTwo:
				return snack_2.get(childPosition);
			default:
				return null;
		}
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		
		String mealItemText = (String) getChild(groupPosition, childPosition);
		
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.meal_item, null);
        }
 
        TextView mealItem = (TextView) convertView
                .findViewById(R.id.meal_item_text);
        
        if (isLastChild) {
        	ImageButton mealEdit = (ImageButton) convertView
        		.findViewById(R.id.edit_meal_item_button);
        	mealItem.setTypeface(null, Typeface.ITALIC);
        	mealItem.setText(R.string.add_food);
        	mealItem.setEllipsize(TruncateAt.END);
        }
        else {
        	mealItem.setTypeface(null, Typeface.BOLD);
        	mealItem.setText(mealItemText);
        }
 
        return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		switch (mealValues[groupPosition]){
			case Breakfast:
				return breakfast.size() + 1;
			case Lunch:
				return lunch.size() + 1;
			case Dinner:
				return dinner.size() + 1;
			case SnackOne:
				return snack_1.size() + 1;
			case SnackTwo:
				return snack_2.size() + 1;
			default:
				return 1;
		}
	}

	@Override
	public Object getGroup(int groupPosition) {
		switch (mealValues[groupPosition]){
			case Breakfast:
				return Meals.Breakfast.name();
			case Lunch:
				return Meals.Lunch.name();
			case Dinner:
				return Meals.Dinner.name();
			case SnackOne:
				return Meals.SnackOne.name();
			case SnackTwo:
				return Meals.SnackTwo.name();
			default:
				return null;
		}
	}

	@Override
	public int getGroupCount() {
		return mealValues.length;
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		
		String mealIdText = (String) getGroup(groupPosition);
		
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.meal_group, null);
        }
 
        TextView mealId = (TextView) convertView
                .findViewById(R.id.meal_id_text);
        mealId.setTypeface(null, Typeface.BOLD);
        mealId.setText(mealIdText);
 
        return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}

}
