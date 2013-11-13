

import java.io.*;
import java.util.*;

public class highscore
{
	//instansvarabler
	public Vector<person> vec;
	
	
	//konstructor
	public highscore()
	{
		vec = new Vector<person>();	
	}
	
	public void load() throws IOException
	{
	
   		String line, name;
   		String seconds;
   		
   		BufferedReader infile = new BufferedReader
                			(new FileReader("highscore.txt"));
					
   		while ((line = infile.readLine()) != null)
   		{
   			StringTokenizer tokens = new StringTokenizer(line);
   			name = tokens.nextToken();
   			seconds = tokens.nextToken();
   			vec.add(new person(name, seconds));
   			
   		}
   		infile.close();
		
	}
	
	public String getNames(int i){
		String name = "";
		
		name = vec.get(i).getName();
		
		return name;
	}

	public String getScores(int i){
		String score = "";
		
		score = vec.get(i).getSeconds();
		
		return score;
	}
	
	//Kunna spara nya spelare till txtfilen 
	//se till att de sparade spelarna Šr sorterade.
	//ta bort resterande spelare >5
	//
	

}
