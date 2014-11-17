package test;
import static org.junit.Assert.*;
import main.CommandCenter;

import org.junit.Test;
public class CommandCenterTest {
	
	String[] bearing = {"1 2 N", "3 3 E"};
	String[] moves = {"LMLMLMLMM", "MMRMMRMRRM"};
	
	
	String filePath  = System.getProperty("user.dir") + "/src/commands.txt";
	
	//included a test case for commands that would make the rover crash 
	String crazyPath = System.getProperty("user.dir") + "/src/crazycommands.txt";
	
	@Test
	public void testCommanCenterCanGiveMeMapSize() {
		CommandCenter cc = new CommandCenter(filePath);
		assertEquals("can give map size from files", "5 5", cc.getMapSize());
	}
	
	@Test
	public void testCommnadCenterGivesMeBearing() {
		CommandCenter cc = new CommandCenter(filePath);
		assertArrayEquals("can give bearings", bearing, cc.getBearings());
	}

	@Test
	public void testCommnadCenterGivesMeMovements() {
		CommandCenter cc = new CommandCenter(filePath);
		assertArrayEquals("can give movements", moves, cc.getMovements());
	}
	
	@Test
	public void testFirstRoverCanPewPew() {
		CommandCenter cc = new CommandCenter(filePath);
		assertEquals("Will make a rover and report its location", "1 3 N",cc.makeRover(0));
	}
	
	@Test
	public void testSecondRoverCanPewPew(){
		CommandCenter cc = new CommandCenter(filePath);
		assertEquals("Will make another rover and report its location", "5 1 E",cc.makeRover(1));
	}
	
	@Test
	public void testAllRoversCanPewPew(){
		CommandCenter cc = new CommandCenter(filePath);
		assertEquals("Can make n rovers and report their location(s)", "1 3 N\n5 1 E",cc.roverMarch());
	}
	
	@Test
	public void testCommandCenterCanHandleCrazyCommands() {
		CommandCenter cc = new CommandCenter(crazyPath);
		assertEquals("Rover wont fall off the plateu", "2 0 S\n0 2 N",cc.roverMarch());
	}
}
	
