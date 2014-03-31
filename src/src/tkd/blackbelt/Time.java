package tkd.blackbelt;

import android.annotation.SuppressLint;
import java.util.Calendar;

public class Time implements Comparable<Time>{
	
	private static final int HOUR_MOD = 100;
	
	private int hour;
	private int minute;
	
	public Time () {
		hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		minute = Calendar.getInstance().get(Calendar.MINUTE);
	}
	
	public Time (int hr, int min, boolean is_am) {
		hour = (hr >= 0 && hr < 24 ? hr : 12);
		minute = (min >= 0 && min < 60 ? min : 0);
		if (!is_am && hour < 12) {
			hr += 12;
		}
	}
	
	public Time (int hr, int min) {
		hour = (hr >= 0 && hr < 24 ? hr : 12);
		minute = (min >= 0 && min < 60 ? min : 0);
	}
	
	public Time (String str) {
		try {
			int temp = Integer.parseInt(str);
			this.fromSingleInt(temp);
		}
		catch (NumberFormatException e) {
			hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
			minute = Calendar.getInstance().get(Calendar.MINUTE);
		}
	}
	
	public int toSingleInt () {
		int temp = hour * HOUR_MOD;
		temp += minute;
		
		return temp;
	}
	
	public void fromSingleInt (int i) {
		hour = i / HOUR_MOD;
		minute = i % HOUR_MOD;
	}
	
	@SuppressLint("DefaultLocale")
	public String toString () {
		return String.format("%02d%02d", hour, minute);
	}

	@Override
	public int compareTo(Time another) {
		return toSingleInt () - another.toSingleInt();
	}

}
