package tkd.blackbelt;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * @author jacoberg2
 *
 */
public class MealsJournal {
	
	private ArrayList<MealsEntry>	entry_list;
	private static MealsJournal		journal = null;

	/**
	 * 
	 */
	private MealsJournal () {
		entry_list = new ArrayList<MealsEntry>();
	}
	
	/**
	 * 
	 * @return
	 */
	static public MealsJournal getJournal () {
		if (journal == null){
			journal = new MealsJournal();
		}
		return journal;
	}
	
	/**
	 * 
	 * @param entry
	 */
	public void addEntry (MealsEntry entry) {
		entry_list.add(entry);
		Collections.sort (entry_list);
	}
	
	/**
	 * 
	 * @param day
	 * @return
	 */
	public MealsEntry getEntry (int day) {
		if (day > 0 && day <= entry_list.size()) {
			return entry_list.get(day-1);
		}
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getNumberOfEntries () {
		if (journal == null) {
			return 0;
		}
		return entry_list.size();
	}
}
