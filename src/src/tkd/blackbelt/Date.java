package tkd.blackbelt;

import android.annotation.SuppressLint;
import java.util.Calendar;

/**
 * 
 * @author jacoberg2
 *
 */
public class Date implements Comparable<Date> {
	
	private static final int YEAR_MOD = 10000;
	private static final int MNTH_MOD = 100;
	
	private int year;
	private int month;
	private int day;
	
	/**
	 * 
	 */
	public Date () {
		year = Calendar.getInstance().get(Calendar.YEAR);
		month = Calendar.getInstance().get(Calendar.MONTH);
		day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH); 
	}
	
	/**
	 * 
	 * @param y
	 * @param m
	 * @param d
	 */
	public Date (int y, int m, int d) {
		year = y;
		month = (m >= 1 && m <=12 ? m : (m % 12) + 1);
		day = d;
	}
	
	/**
	 * 
	 * @param date
	 */
	public void fromSingleInt (int date) {
		int temp = date;
		year = temp / YEAR_MOD;
		month = (temp %= YEAR_MOD) / MNTH_MOD;
		day = (temp /= MNTH_MOD);		
	}
	
	/**
	 * 
	 * @param d
	 */
	public Date (String d) {
		try {
			int temp = Integer.parseInt(d);
			this.fromSingleInt(temp);
		}
		catch (NumberFormatException e) {
			year = Calendar.getInstance().get(Calendar.YEAR);
			month = Calendar.getInstance().get(Calendar.MONTH);
			day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public int toSingleInt () {
		return (year * YEAR_MOD) 
			+ (month * MNTH_MOD) 
			+ day;
	}
	
	/**
	 * 
	 */
	@SuppressLint("DefaultLocale")
	public String toString () {
		return String.format("%04d%02d%02d", year, month, day);
	}
	
	/**
	 * 
	 */
	@Override
	public int compareTo(Date arg0) {
		return this.toSingleInt() - arg0.toSingleInt();
	}

}
