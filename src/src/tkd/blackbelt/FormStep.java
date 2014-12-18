package tkd.blackbelt;

/**
 * 
 * @author jacoberg2
 *
 */
public class FormStep implements Comparable<FormStep>{
	
	/**
	 * 
	 * @author jacoberg2
	 *
	 */
	public enum Direction {FRONT, BACK, LEFT, RIGHT, 
						   F_LEFT, F_RIGHT, B_LEFT, B_RIGHT, 
						   IN_PLACE};
						   
	private	int			number;
	private Direction 	direction;
	private String 		stance;
	private String		foot_technique;
	private String		hand_technique;
	
	/**
	 * 
	 * @param dir
	 * @return
	 */
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
	
	/**
	 * 
	 * @param dir
	 * @return
	 */
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
	
	/**
	 * Constructor
	 */
	public FormStep () {
		number			=	0;
		direction 		= 	Direction.IN_PLACE;
		stance 			= 	null;
		foot_technique	=	null;
		hand_technique	=	null;
	}
	
	/**
	 * 
	 * @param num
	 * @param dir
	 * @param st
	 * @param foot
	 * @param hand
	 */
	public FormStep (int num, Direction dir, String st, String foot, String hand) {
		number			=	num;
		direction 		= 	dir;
		stance 			= 	st;
		foot_technique	=	foot;
		hand_technique	=	hand;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getNumber () {
		return number;
	}
	
	/**
	 * 
	 * @param num
	 */
	public void setNumber (int num) {
		number = num;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getDirectionStr () {
		return getDirectionName (direction);
	}
	
	/**
	 * 
	 * @return
	 */
	public Direction getDirectionEnum () {
		return direction;
	}
	
	/**
	 * 
	 * @param dir
	 */
	public void setDirection (Direction dir) {
		direction = dir;
	}
	
	/**
	 * 
	 * @param dir
	 */
	public void setDirection (String dir) {
		direction = getDirectionEnum (dir);
	}
	
	/**
	 * 
	 * @return
	 */
	public String getStance () {
		return stance;
	}
	
	/**
	 * 
	 * @param sta
	 */
	public void setStance (String sta) {
		stance = sta;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getFootTechnique () {
		return foot_technique;
	}
	
	/**
	 * 
	 * @param foot
	 */
	public void setFootTechnique (String foot) {
		foot_technique = foot;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getHandTechnique () {
		return hand_technique;
	}
	
	/**
	 * 
	 * @param hand
	 */
	public void setHandTechnique (String hand) {
		hand_technique = hand;
	}

	/**
	 * 
	 */
	@Override
	public int compareTo(FormStep another) {
		return number - another.number;
	}
}
