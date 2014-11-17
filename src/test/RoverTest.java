package test;
import static org.junit.Assert.*;
import main.Rover;

import org.junit.Test;


public class RoverTest {

	String[] moveOne  = {"m"};
	String[] moveFive = {"M","M","M","M","M"};
	String[] moveOut  = {"M","M","R","M","M"};
	String[] moveNeg  = {"M","M","M","L","M", "M","M"};
	String[] moveCra  = {"M","M","M","L","M","M","M","L","M","M","M"};
	String[] right    = {"R"};
	String[] left     = {"L"};
	String[] caseOne  = {"L","M","L","M","L","M","L","M","M"};
	String[] caseTwo  = {"M","M","R","M","M","R", "M","R","R","M"};
	String[] mixedUp  = {"M","m","r","M","m","R", "m","r","R","M"};
	String mapSize    = "5 5";
	
	@Test
	public void testRoverCanParseABearing() {
		Rover rover = new Rover("1 2 N");
		assertEquals("Rover knows what direction its facing","N", rover.getFacing());
		assertEquals("Rover parses x position into integer", 1, rover.getX());
		assertEquals("Rover parses y position into integer", 2, rover.getY());
	}
	
	@Test
	public void testRoverCanMoveLeftfromNorth() {
		Rover rover = new Rover("1 2 N");
		rover.move(left, mapSize);
		assertEquals("Rover can turn left from N", "W", rover.getFacing());
	}
	
	@Test
	public void testRoverCanMoveRightfromNorth() {
		Rover rover = new Rover("1 2 N");
		rover.move(right, mapSize);
		assertEquals("Rover can turn right from N", "E", rover.getFacing());
	}
	
	@Test
	public void testRoverCanMoveLeftfromSouth() {
		Rover rover = new Rover("1 2 S");
		rover.move(left, mapSize);
		assertEquals("Rover can turn left from S", "E", rover.getFacing());
	}
	
	@Test
	public void testRoverCanMoveRightfromSouth() {
		Rover rover = new Rover("1 2 S");
		rover.move(right, mapSize);
		assertEquals("Rover can turn right from S", "W", rover.getFacing());
	}
	
	@Test
	public void testRoverCanMoveLeftfromEast() {
		Rover rover = new Rover("1 2 E");
		rover.move(left, mapSize);
		assertEquals("Rover can turn left from E", "N", rover.getFacing());
	}
	
	@Test
	public void testRoverCanMoveRightfromEast() {
		Rover rover = new Rover("1 2 E");
		rover.move(right, mapSize);
		assertEquals("Rover can turn right from E", "S", rover.getFacing());
	}
	
	@Test
	public void testRoverCanMoveLeftfromWest() {
		Rover rover = new Rover("1 2 W");
		rover.move(left, mapSize);
		assertEquals("Rover can turn left from W", "S", rover.getFacing());
	}
	
	@Test
	public void testRoverCanMoveRightfromWest() {
		Rover rover = new Rover("1 2 W");
		rover.move(right, mapSize);
		assertEquals("Rover can turn right from W", "N", rover.getFacing());
	}
	
	@Test
	public void testRoverCanMoveUpYAxis() {
		Rover rover = new Rover("1 2 N");
		rover.move(moveOne, mapSize);
		assertEquals("Rover can move up y axis when facing north", "1 3 N", rover.whereAmI());
	}
	
	@Test
	public void testRoverCanMoveDownYAxis() {
		Rover rover = new Rover("1 1 S");
		rover.move(moveOne, mapSize);
		assertEquals("Rover can move down y axis when facing south", "1 0 S", rover.whereAmI());
	}
	
	@Test
	public void testRoverCanMoveDownXAxis(){
		Rover rover = new Rover("1 1 W");
		rover.move(moveOne, mapSize);
		assertEquals("Rover moves down x axis when facing W", "0 1 W", rover.whereAmI());		
	}

	@Test
	public void testRoverCanMoveUpXAxis(){
		Rover rover = new Rover("1 1 E");
		rover.move(moveOne, mapSize);
		assertEquals("Rover moves up x axis when facing E", "2 1 E", rover.whereAmI());		
	}
	
	@Test
	public void testRoverCanTakeMultipleCommands(){
		Rover rover = new Rover("1 2 N");
		rover.move(caseOne, mapSize);
		assertEquals("For first ThoughtWordsCase", "1 3 N", rover.whereAmI());		
	}
	
	@Test
	public void testRoverReturnsProperInputforSecondCase(){
		Rover rover = new Rover("3 3 E");
		rover.move(caseTwo, mapSize);
		assertEquals("For second ThoughtWorks case", "5 1 E", rover.whereAmI());		
	}
	
	@Test
	public void  testRoverIsNotCaseSensitive() {
		Rover rover = new Rover("3 3 E");
		rover.move(mixedUp, mapSize);
		assertEquals("For second ThoughtWorks case", "5 1 E", rover.whereAmI());
	}
	
	@Test
	public void testRoverCannotGoOutofBoundsonYAxis() {
		Rover rover = new Rover("5 3 N");
		rover.move(moveFive, mapSize);
		assertEquals("Moving north", "5 5 N", rover.whereAmI());
	}
	
	@Test
	public void testRoverCannotGoOutofBoundsonXAxis() {
		Rover rover = new Rover("3 5 E");
		rover.move(moveFive, mapSize);
		assertEquals("Moving north, then east", "5 5 E", rover.whereAmI());
	}
	
	@Test
	public void testRoverCannotGoOutOfBoundsOnXandY() {
		Rover rover1 = new Rover("1 1 N");
		rover1.move(moveNeg, mapSize);
		assertEquals("Moving north", "0 4 W", rover1.whereAmI());
		
		Rover rover2 = new Rover("1 1 W");
		rover2.move(moveNeg, mapSize);
		assertEquals("Moving west", "0 0 S", rover2.whereAmI());
		
		Rover rover3 = new Rover("1 1 S");
		rover3.move(moveCra, mapSize);
		assertEquals("Moving south, west, then North", "4 3 N", rover3.whereAmI());
	}
}
