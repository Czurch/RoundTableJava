package com.czurch.rtl.mechanics.Items;

import java.util.HashMap;

public class ItemList {
	public HashMap<String,Item> tier_1_items = new HashMap<String,Item>();
	public HashMap<String,Item> tier_2_items = new HashMap<String,Item>();
	public HashMap<String,Item> tier_3_items = new HashMap<String,Item>();
	public HashMap<String,Item> tier_4_items = new HashMap<String,Item>();
	public HashMap<String,Item> tier_5_items = new HashMap<String,Item>();
	
	public HashMap<String,Consumable> ConsumableMap = new HashMap<String,Consumable>();
	public HashMap<String,Consumable> DamageMap = new HashMap<String,Consumable>();
	
	public ItemList()
	{
		
		//Populate the List of Consumables
						//		name								name		 	value	  weight	heal	MaxHP	atk		def		armr	init	dead					
		ConsumableMap.put("health potion"		,new Consumable("health potion"		, 2		, 1		, 	6	, 	0	, 	0	, 	0	, 	0	, 	0	, 	false));
		ConsumableMap.put("yak milk"			,new Consumable("yak milk"	   		, 2		, 1		, 	4	, 	0	, 	0	, 	0	, 	0	, 	0	, 	false));
		ConsumableMap.put("loaf of bread"		,new Consumable("loaf of bread"		, 2		, 1		, 	3	, 	0	, 	0	, 	0	, 	0	, 	0	, 	false));
		ConsumableMap.put("fresh meat"			,new Consumable("fresh meat"   		, 2		, 1		, 	3	, 	0	, 	0	, 	0	, 	0	, 	0	, 	false));
		ConsumableMap.put("tincture of health"	,new Consumable("tincture of health", 2		, 1		, 	2	, 	0	, 	0	, 	0	, 	0	, 	0	, 	false));
		ConsumableMap.put("handful of berries"	,new Consumable("handful of berries", 2		, 1		, 	1	, 	0	, 	0	, 	0	, 	0	, 	0	, 	false));
		ConsumableMap.put("rotten meat"			,new Consumable("rotten meat"		, 2		, 1		,  -2	, 	0	, 	3	, 	0	, 	0	, 	0	, 	false));
		
		//Populate the list of tier 1 items
		tier_1_items.put("health potion"	, ConsumableMap.get("health potion"));
		tier_1_items.put("yak milk"			, ConsumableMap.get("yak milk"));
		tier_1_items.put("loaf of bread"	, ConsumableMap.get("loaf of bread"));
		tier_1_items.put("fresh meat"		, ConsumableMap.get("fresh meat"));
		tier_1_items.put("tincture of health", ConsumableMap.get("tincture of health"));
		tier_1_items.put("handful of berries"	, ConsumableMap.get("handful of berries"));
		tier_1_items.put("rotten meat", ConsumableMap.get("rotten meat"));
		//Populate the list of tier 2 items
		tier_2_items.put("health potion"	, ConsumableMap.get("health potion"));
		tier_2_items.put("yak milk"			, ConsumableMap.get("yak milk"));
		tier_2_items.put("loaf of bread"	, ConsumableMap.get("loaf of bread"));
		tier_2_items.put("fresh meat"		, ConsumableMap.get("fresh meat"));
		tier_2_items.put("tincture of health", ConsumableMap.get("tincture of health"));
		tier_2_items.put("rotten meat", ConsumableMap.get("rotten meat"));
		//Populate the list of tier 3 items
		//Populate the list of tier 4 items
		//Populate the list of tier 5 items
	}
}
