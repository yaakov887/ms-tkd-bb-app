package tkd.blackbelt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * 
 * @author jacoberg2
 *
 */
public class NoteList {
	
	private ArrayList<Note> note_list;
	private static NoteList notes;
	
	/**
	 * 
	 */
	private NoteList () {
		note_list = new ArrayList<Note> ();
	}
	
	/**
	 * 
	 * @return
	 */
	public static NoteList getNoteList () {
		if (notes == null) {
			notes = new NoteList ();
		}
		return notes;
	}

	/**
	 * 
	 * @param n
	 */
	public void addNote (Note n) {
		note_list.add (n);
		Collections.sort(note_list);
	}
	
	/**
	 * 
	 * @param d
	 * @param n
	 */
	public void addNote (Date d, String n) {
		note_list.add(new Note (d, n));
		Collections.sort(note_list);
	}
	
	/**
	 * 
	 * @return
	 */
	public Iterator<Note> iterator () {
		return note_list.iterator ();
	}
	
	/**
	 * 
	 * @param d
	 * @return
	 */
	public Note getNote (Date d) {
		Note comp = new Note (d, null);
		for (Iterator<Note> iter = iterator ();iter.hasNext();) {
			Note n = iter.next ();
			if (n.equals(comp)) {
				return n;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param d
	 */
	public void deleteNote (Date d) {
		Note n = new Note (d, null);
		note_list.remove(n);
	}
}
