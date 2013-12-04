

import java.io.*;
import java.util.*;
import java.text.DecimalFormat;

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
   		double doubleSeconds;
   		
   		BufferedReader infile = new BufferedReader
                			(new FileReader("highscore.txt"));
					
   		while ((line = infile.readLine()) != null)
   		{
   			StringTokenizer tokens = new StringTokenizer(line);
   			name = tokens.nextToken();
   			seconds = tokens.nextToken();
   			doubleSeconds = (new Double(seconds)).doubleValue();//Double.parseDouble(seconds);
   			vec.add(new person(name, doubleSeconds));
   			
   		}
   		infile.close();
		
	}
	
	public String getNames(int i){
		String name = "";
		
		name = vec.get(i).getName();
		
		return name;
	}

	public double getScores(int i){
		double score;
		
		score = vec.get(i).getSeconds();
		
		return score;
	}
	
	public void realSort(){
		
		Collections.sort(vec,new Comparator<person>(){
			@Override
			public int compare(person o1, person o2){
				if (o1.seconds>o2.seconds)
					return 0;
				return 1;
			}
		});
	
		
		
		
		//Comparator<person> comparator = new scoreComparator();
		//Collections.sort(vec,comparator);
		
		/*for(int i = 0; i < vec.size(); i++){
			for(int j = 0; j < vec.size(); j++){
				if((int)vec.get(j+1).getSeconds()
				>(int)vec.get(j).getSeconds()){
					//person pers = new person(vec.get(j+1).getName(),vec.get(j+1).getSeconds());
					//person pers2 = new person(vec.get(j).getName(),vec.get(j).getSeconds());
					System.out.println("hej");
					//	vec.add(j, pers);
					//	vec.add(j+1,pers2);
				}
			}
		}*/	
	}
	public void sort(String playerName, double theScore) throws IOException{
		load();
		
		vec.add(new person(playerName,theScore));
		System.out.println(playerName);

		//Collections.sort(vec);
		realSort();
		
		saveFile();

	}
		
	
	public void saveFile() throws IOException
	{
    	DecimalFormat df = new DecimalFormat("##.##");
    	
    	
        PrintWriter outfile = new PrintWriter
                             (new BufferedWriter
                             (new FileWriter("highscore.txt"))); 
                               
        for (int i = 0; i < vec.size(); i++)
        { 
            outfile.format("%-20s%-20s\n",vec.get(i).getName(),df.format(vec.get(i).getSeconds()));
        }
                                                
        outfile.close(); 
        }
        
           /* 
        class scoreComparator implements Comparators<person>{
        	public int compare(person o1, person o2)
        	{
        		return o1.getSeconds().compareTo(o2.getSeconds());
        	}
        } */
}
