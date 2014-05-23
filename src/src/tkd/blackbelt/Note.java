package tkd.blackbelt;

/**
 * 
 * @author jacoberg2
 *
 */
public class Note implements Comparable<Note> {
	
	private Date date;
	private String note;
	
	/**
	 * 
	 */
	public Note () {
		date = new Date ();
		note = null;
	}
	
	/**
	 * 
	 * @param d
	 * @param n
	 */
	public Note (Date d, String n) {
		date = d;
		note = n;
	}
	
	/**
	 * 
	 * @param n
	 */
	public void setNote (String n) {
		note = n;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getNote () {
		return note;
	}
	
	/**
	 * 
	 * @param d
	 */
	public void setDate (Date d) {
		date = d;
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
	 * @param n
	 */
	public void appendNote (String n) {
		note = note + " " + n;
	}
	
	/**
	 * 
	 * @param n
	 */
	public void appendNoteWithNewline (String n) {
		note = note + "\n" + n;
	}

	/**
	 * 
	 */
	@Override
	public int compareTo(Note arg0) {
		return this.date.compareTo(arg0.date);
	}
	
	/**
	 * 
	 */
	public boolean equals (Object arg0) {
		return this.date.compareTo(((Note)arg0).date) == 0;
	}
}
