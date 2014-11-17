package main;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CommandCenter {
	
	private String mapSize; 
	private String[] commands; 
	private String[] bearings; 
	private String[] movements; 
	private int roversAmt;
	
	public CommandCenter(String filePath) {
		try {
			read(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Separates map size from other commands
		setMapSize(commands[0]);
		
		//Determines number of Rovers to be generated.
		roversAmt = (commands.length - 1) / 2;
		
		//Sets size of bearing and movement arrays based on number of Rovers.
		setBearings(new String[roversAmt]);
		setMovements(new String[roversAmt]);
		
		//Separates bearing and movements
		for(int i = 0; i < roversAmt; i++) {
			bearings[i] = commands[(i*2)+1];
			movements[i] = commands[(i+1)*2];		
		}
	}

	/*Creates a Rover and sends it the map size, 
	 where it will be placed, and movement instructions.
	 returns with final location*/
	public String makeRover(int n) {
		Rover rover = new Rover(bearings[n]);
		rover.move(movements[n].split(""), mapSize);
		return rover.whereAmI();
	}
	
	//Generates n Rover(s) and outputs final locations
	public String roverMarch() { 
		String output = "";
		for(int i = 0; i < roversAmt; i++ ) {
			output += makeRover(i) + "\n";
		}
		return output.trim();
	}
	

	String filePath = System.getProperty("user.dir") + "/src/commands.txt";
	
	//reads file and creates String array out of program input 
	private void read(String filePath) throws IOException{
		List<String> lines = Files.readAllLines(Paths.get(filePath));
	    commands = new String[lines.size()];
	    for(int i = 0; i < lines.size(); i++) {
	        commands[i] = lines.get(i);
	    }
	}	

	public String getMapSize() {
		return mapSize;
	}

	public void setMapSize(String mapSize) {
		this.mapSize = mapSize;
	}

	public String[] getBearings() {
		return bearings;
	}

	public void setBearings(String[] bearings) {
		this.bearings = bearings;
	}

	public String[] getMovements() {
		return movements;
	}

	public void setMovements(String[] movements) {
		this.movements = movements;
	}
}
