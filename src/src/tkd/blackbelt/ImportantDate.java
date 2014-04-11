package tkd.blackbelt;

/**
 * 
 * @author jacoberg2
 *
 */
public class ImportantDate implements Comparable<ImportantDate> {
	
	/**
	 * 
	 * @author jacoberg2
	 *
	 */
	public enum Dates {
		PREQUALIFICATION_START,
		PREQUALIFICATION_END,
		REHEARSAL,
		PART_ONE,
		PART_TWO,
		COMPLETE_BOOK;
		
		public static final int size = 5;
	}
	
	public static final String PREQUAL_TAG		= "prequalification";
	
	private Dates	date_title;
	private Date	date;
	private Time	start_time;
	private Time	end_time;
	
	/**
	 * 
	 */
	public ImportantDate () {
		date_title = Dates.PREQUALIFICATION_START;
		date = new Date ();	
		start_time = null;
		end_time = null;
	}
	
	/**
	 * 
	 * @param ds
	 * @param dt
	 * @param st
	 * @param et
	 */
	public ImportantDate (Dates ds, String dt, String st, String et) {
		date_title = ds;
		date = new Date (dt);	
		start_time = new Time (st);
		end_time = new Time (et);		
	}
	
	/**
	 * 
	 * @return
	 */
	public Date getDate () {
		return date;
	}
	
	/**
	 * 
	 * @return
	 */
	public Time getStartTime () {
		return start_time;
	}
	
	/**
	 * 
	 * @return
	 */
	public Time getEndTime () {
		return end_time;
	}
	
	/**
	 * 
	 * @param dt
	 */
	public void setDate (String dt) {
		date = new Date (dt);
	}
	
	/**
	 * 
	 * @param dt
	 */
	public void setStartTime (String dt) {
		start_time = new Time (dt);
	}
	
	/**
	 * 
	 * @param dt
	 */
	public void setEndTime (String dt) {
		end_time = new Time (dt);
	}

	/**
	 * 
	 */
	@Override
	public int compareTo(ImportantDate another) {
		return date_title.ordinal() - another.date_title.ordinal();
	}

}
