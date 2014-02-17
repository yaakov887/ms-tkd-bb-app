package tkd.blackbelt;

public class FormStep implements Comparable<FormStep>{
	
	public enum Direction {FRONT, BACK, LEFT, RIGHT, 
						   F_LEFT, F_RIGHT, B_LEFT, B_RIGHT, 
						   IN_PLACE};
						   
	private	int			number;
	private Direction 	direction;
	private String 		stance;
	private String		foot_technique;
	private String		hand_technique;
	
	static public String getDirectionName (Direction dir) {
		switch (dir) {
		case FRONT:
			return "Front";
		case BACK:
			return "Back";
		case LEFT:
			return "Left";
		case RIGHT:
			return "Right";
		case F_LEFT:
			return "Front Left";
		case F_RIGHT:
			return "Front Right";
		case B_LEFT:
			return "Back Left";
		case B_RIGHT:
			return "Back Right";
		case IN_PLACE:
			return "In Place";
		}
		return "";
	}
	
	static public Direction getDirectionEnum (String dir) {
		if (dir == "Front") {
			return Direction.FRONT;
		}
		else if (dir == "Back") {
			return Direction.BACK;
		}
		else if (dir == "Left") {
			return Direction.LEFT;
		}
		else if (dir == "Right") {
			return Direction.RIGHT;
		}
		else if (dir == "Front Left") {
			return Direction.F_LEFT;
		}
		else if (dir == "Front Right") {
			return Direction.F_RIGHT;
		}
		else if (dir == "Back Left") {
			return Direction.B_LEFT;
		}
		else if (dir == "Back Right") {
			return Direction.B_RIGHT;
		}
		else {
			return Direction.IN_PLACE;
		}
	}
	
	public FormStep (int num, Direction dir, String st, String foot, String hand) {
		number			=	num;
		direction 		= 	dir;
		stance 			= 	st;
		foot_technique	=	foot;
		hand_technique	=	hand;
	}
	
	public int getNumber () {
		return number;
	}
	
	public void setNumber (int num) {
		number = num;
	}
	
	public String getDirectionStr () {
		return getDirectionName (direction);
	}
	
	public Direction getDirectionEnum () {
		return direction;
	}
	
	public void setDirection (Direction dir) {
		direction = dir;
	}
	
	public void setDirection (String dir) {
		direction = getDirectionEnum (dir);
	}
	
	public String getStance () {
		return stance;
	}
	
	public void setStance (String sta) {
		stance = sta;
	}
	
	public String getFootTechnique () {
		return foot_technique;
	}
	
	public void setFootTechnique (String foot) {
		foot_technique = foot;
	}
	
	public String getHandTechnique () {
		return hand_technique;
	}
	
	public void setHandTechnique (String hand) {
		hand_technique = hand;
	}

	@Override
	public int compareTo(FormStep another) {
		return number - another.number;
	}

}
