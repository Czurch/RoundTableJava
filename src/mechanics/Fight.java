package mechanics;

import mechanics.*;
import mechanics.Items.Weapon.combat_options;

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
			resolveCombat(p.combatChoice(), e.combatChoice());
		}
	}
	
	public void resolveCombat(combat_options player, combat_options enemy){
		if(player == combat_options.lunge){							//  LUNGE
			System.out.println("You lunge at the " + e.getName());
			
			if(enemy == combat_options.lunge){								//  vs lunge
				System.out.println(e.getName() + " lunges as well!");
				p.Attack(e);
				e.Attack(p);
			}else if(enemy == combat_options.slash){						//  vs slash
				System.out.println("and " + e .getName() + " slashes at you");
				p.Attack(e);
				e.Attack(p);			
			}else if(enemy == combat_options.slam){							//  vs slam
				System.out.println("and " + e.getName() + " slams into you");
				p.Attack(e);
				e.Attack(p);	
			}else if(enemy == combat_options.block){						//  vs block
				System.out.println("but " + e.getName() + " blocked the attack!");
			}else if(enemy == combat_options.parry){						//  vs parry
				System.out.println("but " + e.getName() + " parried your attack!");
				e.Attack(p);				
			}else if(enemy == combat_options.feint){						//  vs feint
				System.out.println(e.getName() + " tried to feint.");
				p.Attack(e);
			}
		}else if(player == combat_options.slash){					//   SLASH
			System.out.println("You slash at the " + e.getName());
			
			if(enemy == combat_options.lunge){								//  vs lunge
				System.out.println(e.getName() + " lunges at you!");
				p.Attack(e);
				e.Attack(p);
			}else if(enemy == combat_options.slash){						//  vs slash
				System.out.println("and " + e .getName() + " slashes as well");
				p.Attack(e);
				e.Attack(p);			
			}else if(enemy == combat_options.slam){							//  vs slam
				System.out.println("and " + e.getName() + " slams into you");
				p.Attack(e);
				e.Attack(p);	
			}else if(enemy == combat_options.block){						//  vs block
				System.out.println("but " + e.getName() + " blocked the attack!");
			}else if(enemy == combat_options.parry){						//  vs parry
				System.out.println("but " + e.getName() + " parried your attack!");
				e.Attack(p);				
			}else if(enemy == combat_options.feint){						//  vs feint
				System.out.println(e.getName() + " tried to feint.");
				p.Attack(e);
			}
		}else if(player == combat_options.slam){
			System.out.println("You slam the " + e.getName());
			
			if(enemy == combat_options.lunge){								//  vs lunge
				System.out.println(e.getName() + " lunges at you!");
				p.Attack(e);
				e.Attack(p);
			}else if(enemy == combat_options.slash){						//  vs slash
				System.out.println("and " + e .getName() + " slashes as well");
				p.Attack(e);
				e.Attack(p);			
			}else if(enemy == combat_options.slam){							//  vs slam
				System.out.println("and " + e.getName() + " slams into you");
				p.Attack(e);
				e.Attack(p);	
			}else if(enemy == combat_options.block){						//  vs block
				System.out.println("but " + e.getName() + " blocked the attack!");
			}else if(enemy == combat_options.parry){						//  vs parry
				System.out.println("but " + e.getName() + " parried your attack!");
				e.Attack(p);				
			}else if(enemy == combat_options.feint){						//  vs feint
				System.out.println(e.getName() + " tried to feint.");
				p.Attack(e);
			}
		}else if(player == combat_options.block){
			
			if(enemy == combat_options.lunge){								//  vs lunge
				System.out.println(e.getName() + " lunges at you!");
				System.out.println("You block the lunge!");

			}else if(enemy == combat_options.slash){						//  vs slash
				System.out.println(e.getName() + " slashes at you!");
				System.out.println("You block the slash!");		
			}else if(enemy == combat_options.slam){							//  vs slam
				System.out.println(e.getName() + " tries to slam you!");
				System.out.println("You block the slam!");
			}else if(enemy == combat_options.block){						//  vs block
				System.out.println("You both attempt to block.. it's kind of awkward.");
			}else if(enemy == combat_options.parry){						//  vs parry
				System.out.println("You block and " + e.getName() +" parries and you both reset.");		
			}else if(enemy == combat_options.feint){						//  vs feint
				System.out.print(e.getName() + " feints. Then ");
				e.Attack(p);
			}
		}else if(player == combat_options.parry){
			if(enemy == combat_options.lunge){								//  vs lunge
				System.out.println(e.getName() + " lunges at you!");
				System.out.println("You parry the lunge!");
				p.Attack(e);
			}else if(enemy == combat_options.slash){						//  vs slash
				System.out.println(e.getName() + " slashes at you!");
				System.out.println("You parry the slash!");	
				p.Attack(e);
			}else if(enemy == combat_options.slam){							//  vs slam
				System.out.println(e.getName() + " slams into you and breaks through your parry!");
				e.Attack(p);
			}else if(enemy == combat_options.block){						//  vs block
				System.out.println("You both attempt to block.. it's kind of awkward.");
			}else if(enemy == combat_options.parry){						//  vs parry
				System.out.println("You both parry and reset.");		
			}else if(enemy == combat_options.feint){						//  vs feint
				System.out.print(e.getName() + " feints. Then ");
				e.Attack(p);
			}
			
		}else if(player == combat_options.feint){

			if(enemy == combat_options.lunge){								//  vs lunge
				System.out.println(e.getName() + " lunges at you!");
				System.out.println("your feint fails.");
				e.Attack(p);
			}else if(enemy == combat_options.slash){						//  vs slash
				System.out.println(e .getName() + " slashes as well");
				System.out.println("your feint fails.");
				e.Attack(p);			
			}else if(enemy == combat_options.slam){							//  vs slam
				System.out.println(e.getName() + " slams into you");
				System.out.println("your feint fails.");
				e.Attack(p);	
			}else if(enemy == combat_options.block){						//  vs block
				System.out.println("You bait his block then counter.");
				p.Attack(e);
			}else if(enemy == combat_options.parry){						//  vs parry
				System.out.println("You bait his parry then counter.");
				p.Attack(e);				
			}else if(enemy == combat_options.feint){						//  vs feint
				System.out.println("You both try to feint and rest.");
			}
		}
	}
}
