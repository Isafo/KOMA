public class person 
{
	
	public String name;
	public double seconds;
	
	//Konstructor
	public person(String theName, double theSeconds){
		name = theName;
		seconds = theSeconds;
	}
	
	public void setName(String theName){
		name = theName;
	}

	public void setSeconds(double theSeconds){
		seconds = theSeconds;
	}
	
	//get the name on the person
	public String getName(){
		return name;
	}
	
	//get the points(seconds)
	public double getSeconds(){
		return seconds;
	}
	
}
