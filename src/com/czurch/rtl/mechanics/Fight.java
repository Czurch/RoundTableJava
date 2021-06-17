package com.czurch.rtl.mechanics;

import com.czurch.rtl.mechanics.Player;
import com.czurch.rtl.mechanics.Enemy;

public class Fight {
	public Player p;
	public Character e;
	
	/* A typical Fight is between the player and his enemies 
	 * NPC's may have combat with each other but it will be settled more simply
	 */
	public Fight(Player ply, Character nme){
		p = ply;
		e = nme;
		
		initiativeRoll();
		
		combatPhase();
		if(e.isAlive() == false)
		{
			e.showInventory();
			p.loot(e);
		}
	}
	
	//Rolls for initiative
	public void initiativeRoll(){
		this.p.rollInit();
		this.e.rollInit();
		System.out.println("Your initiative roll was " + p.initiative);
		System.out.println(e.name + " initiative roll was " + e.initiative);
		
		if(p.initiative >= e.initiative){
			System.out.println("You will go first!");
		}else{
			System.out.println(e.name + " will act first!");
		}
	}
	
	//determines attack order and fights to the death
	public void combatPhase(){
		while(p.isAlive() && e.isAlive()){
			if(p.initiative >= e.initiative)
			{
				p.turn(e);
				if(e.isAlive()){
					
					e.Attack(p);
					
					try{System.in.read();}
					catch(Exception e){}
				}
			}else{
				
				e.Attack(p);
				
				try{System.in.read();}
				catch(Exception e){}
				
				if(p.isAlive())
				{
					p.turn(e);
				}
			}
		}
	}
}
