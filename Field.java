import java.util.*;
import java.awt.Rectangle;
import java.io.*;

public class Field {

	static ArrayList<Wall> walls;
	ArrayList<Player> players;
	String filePath;
	private static int howMany;
	int xWall, yWall;
	
	public Field(String path){
		walls = new ArrayList<Wall>();
		players = new ArrayList<Player>();
		
		filePath = path;
		
		try {
			createWalls();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createWalls() throws IOException{
		
		ArrayList<String> lines = new ArrayList<String>();
		
		BufferedReader input  = new BufferedReader(new FileReader(filePath));
		
		String line;
		
		while((line = input.readLine()) != null){
			lines.add(line);
		}
		
		input.close();
		
		//checks the row
		for (int i = 0; i < lines.size(); i++){
			//Checks every char in the row
			for (int j = 0; j < lines.get(i).length(); j++){
				
				char symbol = lines.get(i).charAt(j);
				
				switch (symbol){
					
				case '#': //add a wall
					walls.add(new Wall(j * 30, i * 30));
					break;
				
				case 'P': // add a player
					players.add(new Player(j * 30 + 3, i * 30));
					break;
				}
			}
		}
		
		howMany = walls.size();
		
	}
	
	public ArrayList<Wall> getWalls(){
		return walls;
	}
	
	public static int getHowManyWalls(){
		return howMany;
	}
	
	public ArrayList<Player> getPlayers(){
		return players;
	}
}