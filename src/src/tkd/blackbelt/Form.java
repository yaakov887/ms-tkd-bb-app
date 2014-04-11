package tkd.blackbelt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * 
 * @author jacoberg2
 *
 */
public class Form {
	
	private ArrayList<FormStep> form_list;
	private Form form = null;
	
	/**
	 * 
	 */
	private Form () {
		form_list = new ArrayList<FormStep> ();
	}
	
	/**
	 * 
	 * @return
	 */
	public Form getForm () {
		if (form == null) {
			form = new Form ();
		}
		return form;
	}
	
	/**
	 * 
	 * @param step
	 */
	public void addFormStep (FormStep step) {
		form_list.add(step);
		Collections.sort(form_list);
		int size = form_list.size();
		for (int i = 0; i < size; i++) {
			((FormStep) form_list.get(i)).setNumber(i+1);
		}
	}
	
	/**
	 * 
	 * @param num
	 */
	public void deleteFormStep (int num) {
		form_list.remove(num);
		Collections.sort(form_list);
		int size = form_list.size();
		for (int i = 0; i < size; i++) {
			((FormStep) form_list.get(i)).setNumber(i+1);
		}
	}
	
	/**
	 * 
	 * @param num
	 * @return
	 */
	public FormStep getFormStep (int num) {
		return form_list.get(num);
	}
	
	/**
	 * 
	 * @return
	 */
	public Iterator<FormStep> iterator () {
		return form_list.iterator();
	}
}
