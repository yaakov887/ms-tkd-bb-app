package tkd.blackbelt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class ClassList {
	
	private ArrayList<LeadershipClass> list = null;
	private static ClassList classes = null;
	
	private ClassList () {
		list = new ArrayList<LeadershipClass> ();
	}
	
	public static ClassList getClassList () {
		if (classes == null) {
			classes = new ClassList ();
		}
		return classes;
	}
	
	public void addLeadershipClass (LeadershipClass cl) {
		list.add(cl);
		Collections.sort(list);
	}
	
	public void deleteLeadershipClass (LeadershipClass cl) {
		list.remove(cl);
		Collections.sort(list);
	}
	
	public Iterator<LeadershipClass> iterator () {
		return list.iterator();
	}
	

}
