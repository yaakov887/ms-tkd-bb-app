package tkd.blackbelt;

/**
 * 
 * @author jacoberg2
 *
 */
public class JournalEntry implements Comparable<JournalEntry>{
	
	private String 		entry;
	private int 		week_number;
	
	/**
	 * 
	 */
	public JournalEntry (int w) {
		entry = null;
		week_number = w;
	}
	
	/**
	 * 
	 * @param n
	 */
	public JournalEntry (int w, String n) {
		entry = n;
		week_number = w;
	}
	
	public void decrementWeek () {
		--week_number;
	}
	
	public void incrementWeek () {
		++week_number;
	}
	
	/**
	 * 
	 * @param n
	 */
	public void setEntry (String n) {
		entry = n;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getEntry () {
		return entry;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getWeekNumber () {
		return week_number;
	}
	
	/**
	 * 
	 * @param n
	 */
	public void appendEntry (String n) {
		entry = entry + " " + n;
	}
	
	/**
	 * 
	 * @param n
	 */
	public void appendEntryWithNewline (String n) {
		entry = entry + "\n" + n;
	}
	
	/**
	 * 
	 */
	@Override
	public int compareTo (JournalEntry arg0) {
		return this.week_number - arg0.week_number;
	}

}
