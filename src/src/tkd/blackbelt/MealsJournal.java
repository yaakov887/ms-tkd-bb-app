package tkd.blackbelt;

import java.util.ArrayList;
import java.util.Collections;

public class MealsJournal {
	
	private ArrayList<MealsEntry>	entry_list;
	private static MealsJournal		journal = null;

	private MealsJournal () {
		entry_list = new ArrayList<MealsEntry>();
	}
	
	static public MealsJournal getJournal () {
		if (journal == null){
			journal = new MealsJournal();
		}
		return journal;
	}
	
	public void addEntry (MealsEntry entry) {
		entry_list.add(entry);
		Collections.sort (entry_list);
	}
	
	public MealsEntry getEntry (int day) {
		if (day > 0 && day <= entry_list.size()) {
			return entry_list.get(day-1);
		}
		return null;
	}
	
	public int getNumberOfEntries () {
		if (journal == null) {
			return 0;
		}
		return entry_list.size();
	}
}
