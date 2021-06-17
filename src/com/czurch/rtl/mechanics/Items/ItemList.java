package com.czurch.rtl.mechanics.Items;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;

import com.czurch.rtl.mechanics.Items.Effect.effectors;

public class ItemList {
	
	String filePath = new File("").getAbsolutePath();
	
	public HashMap<String,Item> all_items = new HashMap<String,Item>();
	
	public HashMap<String,Item> tier_1_items = new HashMap<String,Item>();
	public HashMap<String,Item> tier_2_items = new HashMap<String,Item>();
	public HashMap<String,Item> tier_3_items = new HashMap<String,Item>();
	public HashMap<String,Item> tier_4_items = new HashMap<String,Item>();
	public HashMap<String,Item> tier_5_items = new HashMap<String,Item>();
	
	public HashMap<String,Consumable> consumable_items = new HashMap<String,Consumable>();
	//public HashMap<String,Consumable> DamageMap = new HashMap<String,Consumable>();
	
	/*
	 * Each instance of the ItemList creates
	 * a catalog of all known items in the game.
	 * 
	 * ItemList can be used to randomly generate
	 * or specifcally select in game items.
	 * 
	 * try using:
	 * 	-getItemByName
	 * 	-getRandom
	 * 	-getRandomByType
	 * 	-getRandomByTier
	 */
	
	public ItemList() throws IOException
	{
		FileReader input;
		BufferedReader bufRead = null;
		String currentLine;
		System.out.println("Loading item catalog...");
		try 
		{
			input = new FileReader(filePath + "/res/Item_List.csv");
			bufRead = new BufferedReader(input);
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while ( (currentLine = bufRead.readLine()) != null)
		{   
			String currentToken;
			int selected_tier;
			String item_type;
			StringTokenizer token = new StringTokenizer(currentLine,",");
			Item temp_item = new Item();	
			
			currentToken = token.nextToken();
			item_type = currentToken.toUpperCase();

			switch(item_type)	// ensure the temp_item is the right type of item before proceeding
			{
			case "CONSUMABLE":
				temp_item = new Consumable();
				break;
			case "POTION":
				temp_item = new Potion();
				break;
			case "EQUIPMENT":
				temp_item = new Equipment();
				break;
			case "GEM":
				temp_item = new Gem();
				break;
			default:
				temp_item = new Item();
				item_type = "GENERAL";
				break;
			}
			
			// Collect item details
			temp_item.name 			= token.nextToken();
			temp_item.description	= token.nextToken(".");
			selected_tier			= Integer.parseInt(token.nextToken(".,"));
			temp_item.value 		= Integer.parseInt(token.nextToken());
			temp_item.weight 		= Integer.parseInt(token.nextToken());
			
			System.out.println("Adding " + temp_item.name);
			// Add any effects
			while(token.hasMoreTokens())
			{
				parseEffect(temp_item, token.nextToken(), token);
			}
			
			// add new item to the catalog
			all_items.put(temp_item.name, temp_item);
			
	
			//add item to type catalog
			switch(item_type) 
			{
			case "CONSUMABLE":
					break;
			case "GENERAL":
			break;
			}
			
			// add item to tier catalog
			switch(selected_tier)
			{
			case 1:
				tier_1_items.put(temp_item.name, temp_item);
				break;
			case 2:
				tier_2_items.put(temp_item.name, temp_item);
				break;
			case 3:
				tier_3_items.put(temp_item.name, temp_item);
				break;
			case 4:
				tier_4_items.put(temp_item.name, temp_item);
				break;
			case 5:
				tier_5_items.put(temp_item.name, temp_item);
				break;
			default:
				break;
			}
			System.out.println("done.\n");
			
		}
		
		//close the buffered reader
		bufRead.close();
	}
	
	public void parseEffect(Item item, String effect, StringTokenizer t)
	{
		int mod = Integer.parseInt(t.nextToken());
		int time = Integer.parseInt(t.nextToken());
		
		System.out.println("\twith " + effect + " effect");
		switch(effect)
		{
		case "HLTH":
			item.addActive(effectors.HEALING, mod, time);
			break;
		case "VGR":
			item.addActive(effectors.VIGOR, mod, time);
			break;
		case "DAM":
			item.addActive(effectors.LETHAL, mod, time);
			break;
		case "FORT":
			item.addActive(effectors.FORTITUDE, mod, time);
			break;
		case "ARMOR":
			item.addActive(effectors.ARMOR, mod, time);
			break;
		case "HASTE":
			item.addActive(effectors.HASTE, mod, time);
			break;
		case "DEATH":
			item.addActive(effectors.DEATH, mod, time);
			break;
		case "MIGHT":
			item.addActive(effectors.MIGHT, mod, time);
			break;
		case "AGILITY":
			item.addActive(effectors.AGILITY, mod, time);
			break;
		case "CON":
			item.addActive(effectors.CONSTITUTION, mod, time);
			break;
		case "ARC":
			item.addActive(effectors.CLAIRVOYANCE, mod, time);
			break;
		case "INTL":
			item.addActive(effectors.MEDITATION, mod, time);
			break;
		case "WIT":
			item.addActive(effectors.KEEN_SENSES, mod, time);
			break;
		}
	}
	
	public Item getItemByName()
	{

		Random generator = new Random();
		Item[] values = (Item[]) all_items.values().toArray();
		Item randomValue = values[generator.nextInt(values.length)];
		return randomValue;

	}
	
	public Item getRandom()
	{
		Random generator = new Random();
		Item[] values = (Item[]) all_items.values().toArray();
		Item randomValue = values[generator.nextInt(values.length)];
		return randomValue;
	}
	
	public Item getRandomByType(item_type selected_type)
	{
		Random generator = new Random();
		Item[] values;
		switch(selected_type)
		{
		case consumable:
			values = (Item[]) consumable_items.values().toArray();
			break;
		case general:
			values = (Item[]) all_items.values().toArray();
			break;
		default:
			System.out.println("ItemList Error: Invalid Type selected");
			values = (Item[]) all_items.values().toArray();
			break;
		}
		Item randomValue = values[generator.nextInt(values.length)];
		return randomValue;
	}
	
	public Item getRandomByTier(int selected_tier)
	{
		Random 		generator 	= new Random();
		Object[]	keys;	
		Object 		randomKey;
		Item 		randomItem;
		
		switch(selected_tier)
		{
		case 1:
			keys		= all_items.keySet().toArray();
			randomKey	= keys[generator.nextInt(keys.length)];
			randomItem 	= all_items.get(randomKey);
			break;
		case 2:
			keys		= all_items.keySet().toArray();
			randomKey	= keys[generator.nextInt(keys.length)];
			randomItem 	= all_items.get(randomKey);
			break;
		case 3:
			keys		= all_items.keySet().toArray();
			randomKey	= keys[generator.nextInt(keys.length)];
			randomItem 	= all_items.get(randomKey);
			break;
		case 4:
			keys		= all_items.keySet().toArray();
			randomKey	= keys[generator.nextInt(keys.length)];
			randomItem 	= all_items.get(randomKey);
			break;
		case 5:
			keys		= all_items.keySet().toArray();
			randomKey	= keys[generator.nextInt(keys.length)];
			randomItem 	= all_items.get(randomKey);
			break;
		default:
			System.out.println("Error: Invalid tier selected");
			keys		= all_items.keySet().toArray();
			randomKey	= keys[generator.nextInt(keys.length)];
			randomItem 	= all_items.get(randomKey);
			break;
		}
		return randomItem;
	}
	
	public enum item_type
	{
		consumable,
		general
	}
}
