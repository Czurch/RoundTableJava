package mechanics.Worldbuilding;

import java.util.ArrayList;

import mechanics.Character;

public class Location {
    
    //scratch this idea, turn these into child classes of Location
    public enum locale{
    	town,
    	village,
    	farmland,
    	
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
