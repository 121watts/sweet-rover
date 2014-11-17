package main;
public class Rover {

	private int x;
	private int y;
	private String facing;

	// constructs X/Y position and Direction facing for Rover
	public Rover(String bearing) {
		String[] bearings = bearing.split(" ");
		setX(Integer.parseInt(bearings[0]));
		setY(Integer.parseInt(bearings[1]));
		setFacing(bearings[2]);
	}

	// Gives current location of Rover
	public String whereAmI() {
		return getX() + " " + getY() + " " + getFacing();
	}

	/* Determines which movement the Rover is making and 
	ensures rover cannot go off of map*/
	public void move(String[] movements, String mapSize) {
		for (String movement : movements) {
			if(movement.equalsIgnoreCase("R"))
				turnRight();
			else if (movement.equalsIgnoreCase("L"))
				turnLeft();
			else if  (movement.equalsIgnoreCase("M"))
				moveForward();
			checkBounds(mapSize);
		}
	}

	//Logic for staying in bounds of map	
	public void checkBounds(String mapSize) {
		String[] map = mapSize.split(" ");
		int maxX = Integer.parseInt(map[0]);
		int maxY = Integer.parseInt(map[1]);
		if (x < 0)
			x = 0;
		else if ( x > maxX)
			x = maxX;
		else if ( y < 0 )
			y = 0;
		else if ( y > maxY )
			y = maxY;
	}

	//How the Rover moves forward
	private void moveForward(){
		if(getFacing().equalsIgnoreCase("N"))
			setY(getY() + 1);
		else if(getFacing().equalsIgnoreCase("S"))
			setY(getY() - 1);
		else if(getFacing().equalsIgnoreCase("W"))
			setX(getX() - 1);
		else if(getFacing().equalsIgnoreCase("E"))
			setX(getX() + 1);

	}

	//How the Rover turns left
	private void turnLeft(){
		if(getFacing().equalsIgnoreCase("N"))
			setFacing("W");
		else if(getFacing().equalsIgnoreCase("S"))
			setFacing("E");
		else if(getFacing().equalsIgnoreCase("W"))
			setFacing("S");
		else if(getFacing().equalsIgnoreCase("E"))
			setFacing("N");
	}

	//How the Rover turns right
	private void turnRight(){
		if(getFacing().equalsIgnoreCase("N"))
			setFacing("E");
		else if(getFacing().equalsIgnoreCase("S"))
			setFacing("W");
		else if(getFacing().equalsIgnoreCase("W"))
			setFacing("N");
		else if(getFacing().equalsIgnoreCase("E"))
			setFacing("S");
	}

	public String getFacing() {
		return facing;
	}

	public void setFacing(String facing) {
		this.facing = facing;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
