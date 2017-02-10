package mechanics;

public class coreMath {

	//Simulates the roll of a die
	public static int rollD6(){
		return (int)(Math.random() * 6) + 1;
	}
	
	public static int randomNumberBetween(int low, int high){
		return (int)(Math.random() * high) + low;
	}
}
