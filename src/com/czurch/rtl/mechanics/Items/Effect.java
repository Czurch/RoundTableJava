package com.czurch.rtl.mechanics.Items;

public class Effect {
	
	/*effectors is an enum which assigns a name to the different status effects that can be altered on a Character 
	 * HEALING 		- HP 
	 * VIGOR 		- maxHP
	 * LETHAL		- attack
	 * FORTITUDE	- defense 
	 * ARMOR		- armor
	 * HASTE		- initMod
	 * DEATH		- isAlive
	 * MIGHT		- strength 
	 * AGILITY		- dexterity
	 * CONSTITUTION	- tolerance
	 * CLAIRVOYANCE	- arcana
	 * MEDITATION	- intelligence
	 * KEEN_SENSES	- wits
	 */

	public enum effectors {
		HEALING, VIGOR, LETHAL, FORTITUDE, ARMOR, HASTE, DEATH, MIGHT, 
		AGILITY, CONSTITUTION, CLAIRVOYANCE, MEDITATION, KEEN_SENSES
	}
	public effectors effect;
	public int mod;
	
	public Effect(effectors effectToApply ,int modifier){
		effect = effectToApply;
		mod = modifier;
	}
}
