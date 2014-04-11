package tkd.blackbelt;

public class LeadershipClass implements Comparable<LeadershipClass> {

	private boolean completed = false;
	private String 	instructor = null;
	private Date	date = null;
	
	public LeadershipClass (boolean comp, String instr, Date d) {
		completed = comp;
		instructor = instr;
		date = d;
	}
	
	public LeadershipClass (boolean comp, String instr, int d) {
		completed = comp;
		instructor = instr;
		date.fromSingleInt(d);
	}
	
	public String getInstructor () {
		return instructor;
	}
	
	public boolean getCompleted () {
		return completed;
	}
	
	public Date getDate () {
		return date;
	}
	
	public int getDateInt () {
		return date.toSingleInt();
	}
	
	@Override
	public int compareTo(LeadershipClass another) {
		int comp = date.compareTo(another.date);
		
		if (comp == 0) {
			return instructor.compareTo(another.instructor);
		}
		return comp;
	}
	
	

}
