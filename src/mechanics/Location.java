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
    	glacier,
    	volcano
    }
    
    protected terrain areaType;
    protected String name;
    protected ArrayList<Character> characterList;
}
