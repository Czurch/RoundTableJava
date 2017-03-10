package mechanics.Items;

import mechanics.coreMath;

public class Weapon extends Item{
	public enum weaponType{
		two_handed,
		one_handed,
		ranged,
		shield
	}
	public enum combat_options{
		lunge,
		slash,
		slam,
		parry,
		feint,
		block
	}
	
	public String name;
	protected weaponType type;
	protected int 	minDmg,
					maxDmg;
	
	public Weapon(String nom, weaponType typ, int min, int max, int val){
		this.name = nom;
		this.type = typ;
		this.minDmg = min;
		this.maxDmg = max;
		this.value = val;
	}
	
	@Override
	public int active() {
		// TODO Auto-generated method stub
		return coreMath.randomNumberBetween(minDmg, maxDmg);
	}

	@Override
	public void passive() {
		// TODO Auto-generated method stub
		
	}

}
