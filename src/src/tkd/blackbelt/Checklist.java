package tkd.blackbelt;

/**
 * 
 * @author jacoberg2
 *
 */
public class Checklist {
	
	/**
	 * 
	 * @author jacoberg2
	 *
	 */
	public enum Items {
		RECOMMENDATION, 
		PHOTO, 
		PROJECT, 
		ESSAY_ONE, 
		ESSAY_TWO, 
		TEST_FEES,
		FASTING,
		HEALTHY_HABITS,
		PERSERVERANCE,
		THANK_YOU,
		RESPECT,
		RIGHTING_WRONGS;
		
		public static final int size = 12;
	};
		
	private boolean[] completed;
	private static Checklist checklist = null;
	
	/**
	 * 
	 */
	private Checklist () {
		completed = new boolean[Items.size];
		for (Items i: Items.values()) {
			completed[i.ordinal()] = false;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public static Checklist getChecklist () {
		if (checklist == null) {
			checklist = new Checklist ();
		}
		return checklist;
	}
	
	/**
	 * 
	 * @param item
	 */
	public void itemCompleted (Items item) {
		completed[item.ordinal()] = true;
	}
	
	/**
	 * 
	 * @param str
	 */
	public void itemCompleted (String str) {
		completed[Items.valueOf(str.toUpperCase()).ordinal()] = true;
	}
	
	/**
	 * 
	 * @param item
	 */
	public void resetItem (Items item) {
		completed[item.ordinal()] = false;
	}
	
	/**
	 * 
	 * @param str
	 */
	public void resetItem (String str) {
		completed[Items.valueOf(str.toUpperCase()).ordinal()] = false;
	}
	
	/**
	 * 
	 * @param item
	 * @return
	 */
	public boolean isCompleted (Items item) {
		return completed[item.ordinal()];
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public boolean isCompleted (String str) {
		return completed[Items.valueOf(str.toUpperCase()).ordinal()];
	}
}
