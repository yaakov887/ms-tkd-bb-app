package tkd.blackbelt;

public class Date implements Comparable<Date> {
	
	private static final int YEAR_MOD = 10000;
	private static final int MNTH_MOD = 100;
	
	private int year = 0;
	private int month = 0;
	private int day = 0;
	
	public Date (int y, int m, int d) {
		year = y;
		month = m;
		day = d;
	}
	
	public int toSingleInt () {
		return (year * YEAR_MOD) + (month * MNTH_MOD) + day;
	}

	public Date fromSingleInt (int date) {
		Date new_date = new Date (0,0,0);
		int temp_date = date;
		new_date.year = temp_date / YEAR_MOD;
		new_date.month = (temp_date -= (new_date.year * YEAR_MOD)) / MNTH_MOD;
		new_date.day = (temp_date -= (new_date.month * MNTH_MOD));
		
		return new_date;
	}
	
	@Override
	public int compareTo(Date arg0) {
		return this.toSingleInt() - arg0.toSingleInt();
	}

}
