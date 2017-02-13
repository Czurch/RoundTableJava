package mechanics;

public class Enemy {
	//Base stats for player
		String name;
	    protected int health 
	    ,maxHealth
	    ,attack
	    ,defence
	    ,initiative
	    ,initMod;
	    boolean alive;
		    
	    public Enemy(){
	    	name = "Generic Enemy";
	    	health = 8;
	    	maxHealth = 8;
	    	attack = 0;
	    	defence = 0;
	    	initiative = 0;
	    	initMod = 0;
	    	alive = true;
	    }
	    
	    public Enemy(String nam, int HP, int ATK, int DEF, int INIT){
	    	name = nam;
	    	health = HP;
	    	maxHealth = HP;
	    	attack = ATK;
	    	defence = DEF;
	    	initiative = 0;
	    	initMod = INIT;
	    	alive = true;
	    }
	    
	    //Randomizes the base value of hitpoints
	    public void randomizeHitPoints(){
	    	int hp = coreMath.randomNumberBetween(4, 12);
	    	health = hp;
	    	maxHealth = hp;	
	    }
	    
		//Causes enemy to lose health on attack
	    public void takeDamage(int nmeATK){
	        int damage = (nmeATK - this.defence);
	        if(damage > 0)										
	        	this.health -= damage;
	        if(this.health <= 0)
	        	this.health -= damage;					//if the damage would kill, set alive to false
	        	this.alive = false;
	    }
	    
	    //rolls for and sets initiative
	    public void rollInit(){
	    	this.initiative = coreMath.rollD6() + this.initMod;
	    }
	    
	    public void Attack(Player target){
	    	int hit = this.attack + coreMath.rollD6();
	    	System.out.println(this.name + " strikes for "+ hit + " damage.");
	    	target.takeDamage(hit);
	    }
}

