package tkd.blackbelt.track;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils.TruncateAt;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import tkd.blackbelt.MealsEntry;
import tkd.blackbelt.track.R;

public class ExpandableMealView extends BaseExpandableListAdapter {

	private Context 	context;
	private MealsEntry 	mealsEntry;
	
	public ExpandableMealView(Context context, MealsEntry meals) {
        this.context = context;
        this.mealsEntry = meals;
    }

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		MealsEntry.Meals meal = MealsEntry.MealValues[groupPosition];
		return mealsEntry.getFoodFromMeal(meal, childPosition);
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
        	
        	mealEdit.setImageResource(R.drawable.ic_add);
        }
        else {
        	mealItem.setTypeface(null, Typeface.BOLD);
        	mealItem.setText(mealItemText);
        }
 
        return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		MealsEntry.Meals meal = MealsEntry.MealValues[groupPosition];
		return mealsEntry.getMealCount(meal);
	}

	@Override
	public Object getGroup(int groupPosition) {
		MealsEntry.Meals meal = MealsEntry.MealValues[groupPosition];
		return meal.name();
	}

	@Override
	public int getGroupCount() {
		return MealsEntry.MealValues.length;
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
