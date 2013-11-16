package tkd.blackbelt;

import java.util.ArrayList;
import java.lang.Comparable;

public class MealsEntry implements Comparable<MealsEntry> {

	public enum Meals 			{Breakfast, Lunch, Dinner, SnackOne, SnackTwo};
	public static final Meals[] MealValues = Meals.values();
	
	static int 					nextEntryDay = 1;
	
	private final int 			entryDay;
	private ArrayList<String> 	breakfast;
	private ArrayList<String> 	lunch;
	private ArrayList<String> 	dinner;
	private ArrayList<String> 	snackOne;
	private ArrayList<String> 	snackTwo;
	
	public MealsEntry () {
		entryDay 	= nextEntryDay;
		breakfast 	= new ArrayList<String>();
		lunch 		= new ArrayList<String>();
		dinner 		= new ArrayList<String>();
		snackOne 	= new ArrayList<String>();
		snackTwo 	= new ArrayList<String>();
		++nextEntryDay;
	}
	
	public MealsEntry (ArrayList<String> bfast, ArrayList<String> lnch,
					   ArrayList<String> dnr, ArrayList<String> sn1, 
					   ArrayList<String> sn2) {
		entryDay 	= nextEntryDay;
		breakfast 	= (ArrayList<String>)bfast.clone();
		lunch 		= (ArrayList<String>)lnch.clone();
		dinner 		= (ArrayList<String>)dnr.clone();
		snackOne 	= (ArrayList<String>)sn1.clone();
		snackTwo 	= (ArrayList<String>)sn2.clone();
		++nextEntryDay;
	}
	
	public void addFoodToMeal (Meals meal, String food) {
		switch (meal) {
			case Breakfast:
				breakfast.add(food);
				break;
			case Lunch:
				lunch.add(food);
				break;
			case Dinner:
				dinner.add(food);
				break;
			case SnackOne:
				snackOne.add(food);
				break;
			case SnackTwo:
				snackTwo.add(food);
				break;
			default:
				break;
		}
	}
	
	public ArrayList<String> getMeal (Meals meal) {
		switch (meal) {
			case Breakfast:
				return breakfast;
			case Lunch:
				return lunch;
			case Dinner:
				return dinner;
			case SnackOne:
				return snackOne;
			case SnackTwo:
				return snackTwo;
			default:
				return null;
		}
	}
	
	public int getMealCount (Meals meal) {
		switch (meal) {
			case Breakfast:
				return breakfast.size();
			case Lunch:
				return lunch.size();
			case Dinner:
				return dinner.size();
			case SnackOne:
				return snackOne.size();
			case SnackTwo:
				return snackTwo.size();
			default:
				return 0;
		}
	}
	
	public String getFoodFromMeal (Meals meal, int index) {
		switch (meal) {
		case Breakfast:
			return breakfast.get(index);
		case Lunch:
			return lunch.get(index);
		case Dinner:
			return dinner.get(index);
		case SnackOne:
			return snackOne.get(index);
		case SnackTwo:
			return snackTwo.get(index);
		default:
			return null;
		}
	}
	
	public int getDay () {
		return entryDay;
	}

	@Override
	public int compareTo(MealsEntry another) {
		return this.entryDay - another.entryDay;
	}
}
