package tkd.blackbelt;

public class ImportantDate implements Comparable<ImportantDate> {
	
	public enum Dates {
		PREQUALIFICATION,
		REHEARSAL,
		PART_ONE,
		PART_TWO,
		COMPLETE_BOOK;
		
		public static final int size = 5;
	}
	
	private Dates	date_title;
	private Date	date;
	private Time	start_time;
	private Time	end_time;
	
	public ImportantDate () {
		date_title = Dates.PREQUALIFICATION;
		date = new Date ();	
		start_time = new Time ();
		end_time = new Time ();
	}
	
	public ImportantDate (Dates ds, String dt, String st, String et) {
		date_title = ds;
		date = new Date (dt);	
		start_time = new Time (st);
		end_time = new Time (et);		
	}
	
	public Date getDate () {
		return date;
	}
	
	public Time getStartTime () {
		return start_time;
	}
	
	public Time getEndTime () {
		return end_time;
	}

	@Override
	public int compareTo(ImportantDate another) {
		return date_title.ordinal() - another.date_title.ordinal();
	}

}
