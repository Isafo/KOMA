public class person 
{
	
	private String name;
	private String seconds;
	
	//Konstructor
	public person(String theName, String theSeconds){
		name = theName;
		seconds = theSeconds;
	}
	
	public void setName(String theName){
		name = theName;
	}

	
	public void setSeconds(String theSeconds){
		seconds = theSeconds;
	}
	

	
	//get the name on the person
	public String getName(){
		return name;
	}
	
	//get the points(seconds)
	public String getSeconds(){
		return seconds;
	}
	
}
