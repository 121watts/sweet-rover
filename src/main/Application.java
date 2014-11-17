package main;
public class Application {
	public static void main (String[] args){
		String filePath = System.getProperty("user.dir") + "/src/commands.txt";	
		CommandCenter cc = new CommandCenter(filePath);
		System.out.println(cc.roverMarch());
	}	
}