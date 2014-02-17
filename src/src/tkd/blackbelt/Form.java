package tkd.blackbelt;

import java.util.ArrayList;
import java.util.Collections;

public class Form {
	
	private ArrayList<FormStep> form_list;
	private Form form = null;
	
	private Form () {
		form_list = new ArrayList<FormStep> ();
	}
	
	public Form getForm () {
		if (form == null) {
			form = new Form ();
		}
		return form;
	}
	
	public void addFormStep (FormStep step) {
		form_list.add(step);
		Collections.sort(form_list);
		int size = form_list.size();
		for (int i = 0; i < size; i++) {
			((FormStep) form_list.get(i)).setNumber(i+1);
		}
	}
	
	public void deleteFormStep (int num) {
		form_list.remove(num);
		Collections.sort(form_list);
		int size = form_list.size();
		for (int i = 0; i < size; i++) {
			((FormStep) form_list.get(i)).setNumber(i+1);
		}
	}
	
	public FormStep getFormStep (int num) {
		return form_list.get(num);
	}

}
