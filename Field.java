import java.util.*;
import java.io.*;

public class Field {

	ArrayList<Wall> walls;
	ArrayList<Player> players;
	String filePath;
	
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
		
		int count = 1;
		
		//checks the row
		for (int i = 0; i < lines.size(); i++){
			//Checks every char in the row
			for (int j = 0; j < lines.get(i).length(); j++){
				
				char symbol = lines.get(i).charAt(j);
				
				switch (symbol){
					
				case '#': //add a wall
					walls.add(new Wall(j * 30 + 8, i * 30 + 30));
					break;
				
				case 'P': // add a player
					players.add(new Player(j * 30 + 8, i * 30 + 30, count));
					break;
				}
			}
		}
	}
	
	public ArrayList<Wall> getWalls(){
		return walls;
	}
	
	public ArrayList<Player> getPlayers(){
		return players;
	}
}