package tkd.blackbelt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * 
 * @author jacoberg2
 *
 */
public class ClassList {
	
	private ArrayList<LeadershipClass> list = null;
	private static ClassList classes = null;
	
	/**
	 * 
	 */
	private ClassList () {
		list = new ArrayList<LeadershipClass> ();
	}
	
	/**
	 * 
	 * @return
	 */
	public static ClassList getClassList () {
		if (classes == null) {
			classes = new ClassList ();
		}
		return classes;
	}
	
	/**
	 * 
	 * @param cl
	 */
	public void addLeadershipClass (LeadershipClass cl) {
		list.add(cl);
		Collections.sort(list);
	}
	
	/**
	 * 
	 * @param cl
	 */
	public void deleteLeadershipClass (LeadershipClass cl) {
		list.remove(cl);
		Collections.sort(list);
	}
	
	/**
	 * 
	 * @return
	 */
	public Iterator<LeadershipClass> iterator () {
		return list.iterator();
	}
	

}
