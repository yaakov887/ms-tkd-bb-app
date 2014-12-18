package tkd.blackbelt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * 
 * @author jacoberg2
 *
 */
public class Journal {
	
	private ArrayList<JournalEntry> entries = null;
	private static Journal 			journal = null;
	
	/**
	 * 
	 */
	private Journal () {
		entries = new ArrayList<JournalEntry> ();
	}
	
	/**
	 * 
	 * @return
	 */
	public static Journal getJournal () {
		if (journal == null) {
			journal = new Journal ();
		}
		return journal;
	}

	/**
	 * 
	 * @param j
	 */
	public void addEntry (JournalEntry j) {
		entries.add (j);
		Collections.sort (entries);
		JournalEntry e1 = null;
		JournalEntry e2 = null;
		for (Iterator<JournalEntry> iter = iterator (); iter.hasNext ();) {
			e1 = e2;
			e2 = iter.next ();
			if (e1 != null && e2 != null) {
				if (e2.getWeekNumber () - e1.getWeekNumber () == 0 ) {
					e2.incrementWeek ();
				}
			}
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public Iterator<JournalEntry> iterator () {
		return entries.iterator ();
	}
	
	/**
	 * 
	 * @param i
	 */
	public void deleteEntry (int w) {
		if (w <= entries.size () && w > 0) {
			entries.remove(w - 1);
		}
		Collections.sort (entries);
		JournalEntry e1 = null;
		JournalEntry e2 = null;
		for (Iterator<JournalEntry> iter = iterator (); iter.hasNext ();) {
			e1 = e2;
			e2 = iter.next ();
			if (e1 != null && e2 != null) {
				if (e2.getWeekNumber () - e1.getWeekNumber () > 1) {
					e2.decrementWeek ();
				}
			}
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public int numberOfEntries () {
		return entries.size ();
	}
}
