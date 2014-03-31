package tkd.blackbelt;

import tkd.blackbelt.ImportantDate.Dates;

public class DateList {
	private ImportantDate[] dates;
	private DateList datelist = null;
	
	private DateList () {
		dates = new ImportantDate[Dates.size];
	}
	
	public DateList getDateList () {
		if (datelist == null) {
			datelist = new DateList ();
		}
		return datelist;
	}
	
	public ImportantDate getDate (Dates ds) {
		return dates[ds.ordinal()];
	}
	
	public void setDate (Dates ds, ImportantDate id) {
		dates[ds.ordinal()] = id;
	}
}
