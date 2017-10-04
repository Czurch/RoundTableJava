package com.czurch.rtl.mechanics;

import com.czurch.rtl.mechanics.Player;
import com.czurch.rtl.mechanics.Enemy;

public class Fight {
	public Player p;
	public Enemy e;
	
	public Fight(Player ply, Enemy nme){
		p = ply;
		e = nme;
		
		startPhase();
		combatPhase();
	}
	
	//Rolls for initiative
	public void startPhase(){
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
		while(p.alive && e.alive){
			if(p.initiative >= e.initiative)
			{
				p.turn(e);
				if(e.alive){
					e.Attack(p);
				}
			}else{
				e.Attack(p);
				if(p.alive)
				{
					p.turn(e);
				}
			}
		}
	}
}
