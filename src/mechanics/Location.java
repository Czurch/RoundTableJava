package mechanics;

import java.util.ArrayList;

public class Location {
    public enum terrain{
    	plains,
    	forest,
    	mountain,
    	swamp,
    	desert,
    	ocean,
    	tundra,
    	volano
    }
    
    
    //scratch this idea, turn these into child classes of Location
    public enum locale{
    	town,
    	village,
    	farmland,
    	
    }
    
    public enum safety_rating{
    	peaceful,
    	rowdy,
    	wild,
    	unsafe,
    	warring,
    	dangerous
    }
    
    protected terrain areaType;
    protected safety_rating safety;
    protected String name;
    protected ArrayList<Character> characterList;
    protected ArrayList<Location> nearbyLocations;
    
    public Location(String nom, terrain ter, safety_rating rating){
    	name = nom;
    	areaType = ter;
    	safety = rating;
    }
    
    public void populate_area(){
    	//generate the character list based on the area
    }
}
