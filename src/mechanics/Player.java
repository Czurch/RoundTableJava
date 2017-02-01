package mechanics;


public class Player {
	
	 	//Base stats for player
		String name;
	    private int health 
	    ,maxHealth
	    ,attack
	    ,defence
	    ,initiative
	    ,initMod;
	    boolean alive
	    ,isDefending;
	    
	    //Skill Checks
	    //to be added soon

	    public Player(){
	    	name = "Placeholder";
	    	health = 8;
	    	maxHealth = 8;
	    	attack = 0;
	    	defence = 0;
	    	initiative = 0;
	    	initMod = 0;
	    	alive = true;
	    	isDefending = false;
	    }
	    
	    public Player(String nam, int HP, int ATK, int DEF, int INIT){
	    	name = nam;
	    	health = HP;
	    	maxHealth = HP;
	    	attack = ATK;
	    	defence = DEF;
	    	initiative = 0;
	    	initMod = INIT;
	    	alive = true;
	    	isDefending = false;
	    }	
	    
	    public int attackMove(Enemy target){
	    	int damage = attack + coreMath.rollDie();
	    	return damage;
	    }
	    
	   //Causes player to lose health on attack
	    public void takeDamage(int nmeATK){
	        int damage = (nmeATK - defence);
	        if(damage > 0)										
	        	health -= damage;
	        if(health <= 0)
	        	health -= damage;					//if the damage would kill, set alive to false
	        	alive = false;
	    }
	    
	    public void rollInit(){
	    	initiative = coreMath.rollDie() + initMod;
	    }
}