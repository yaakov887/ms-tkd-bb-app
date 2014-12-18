package tkd.blackbelt;

import java.util.ArrayList;
import java.lang.Comparable;

/**
 * 
 * @author jacoberg2
 *
 */
public class MealsEntry implements Comparable<MealsEntry> {

	/**
	 * 
	 * @author jacoberg2
	 *
	 */
	public enum Meals 			{Breakfast, Lunch, Dinner, SnackOne, SnackTwo};
	public static final Meals[] MealValues = Meals.values();
	
	static int 					nextEntryDay = 1;
	
	private final int 			entryDay;
	private ArrayList<String> 	breakfast;
	private ArrayList<String> 	lunch;
	private ArrayList<String> 	dinner;
	private ArrayList<String> 	snackOne;
	private ArrayList<String> 	snackTwo;
	
	/**
	 * 
	 */
	public MealsEntry () {
		entryDay 	= nextEntryDay;
		breakfast 	= new ArrayList<String>();
		lunch 		= new ArrayList<String>();
		dinner 		= new ArrayList<String>();
		snackOne 	= new ArrayList<String>();
		snackTwo 	= new ArrayList<String>();
		++nextEntryDay;
	}
	
	/**
	 * 
	 * @param bfast
	 * @param lnch
	 * @param dnr
	 * @param sn1
	 * @param sn2
	 */
	@SuppressWarnings("unchecked")
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
	
	/**
	 * 
	 * @param meal
	 * @param food
	 */
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
	
	/**
	 * 
	 * @param meal
	 * @return
	 */
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
	
	/**
	 * 
	 * @param meal
	 * @return
	 */
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
	
	/**
	 * 
	 * @param meal
	 * @param index
	 * @return
	 */
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
	
	/**
	 * 
	 * @return
	 */
	public int getDay () {
		return entryDay;
	}

	/**
	 * 
	 */
	@Override
	public int compareTo(MealsEntry another) {
		return this.entryDay - another.entryDay;
	}
	
	/**
	 * 
	 * @param meal
	 * @param index
	 * @param food
	 */
	public void setFoodInMeal (Meals meal, int index, String food) {
		
		if (index >= getMealCount(meal)) {
			addFoodToMeal (meal, food);
			return;
		}
		else {
			switch (meal) {
			case Breakfast:
				breakfast.add(index, food);
				return;
			case Lunch:
				lunch.add(index, food);
				return;
			case Dinner:
				dinner.add(index, food);
				return;
			case SnackOne:
				snackOne.add(index, food);
				return;
			case SnackTwo:
				snackTwo.add(index, food);
				return;
			default:
				return;
			}
		}
		
	}
}
