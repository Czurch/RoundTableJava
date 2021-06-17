package com.czurch.rtl.mechanics.CharacterTypes;

public class HumanNameGen {
	

	public static String nameGen()
	{
		int NUMBER_OF_NAMES = 35;
		
		String name;
		
		name = namePicker((int)(Math.random() * NUMBER_OF_NAMES) + 1);
		name += namePicker((int)(Math.random() * NUMBER_OF_NAMES) + 1);
		name += " ";
		name += namePicker((int)(Math.random() * NUMBER_OF_NAMES) + 1);
		name += namePicker((int)(Math.random() * NUMBER_OF_NAMES) + 1);
		name += namePicker((int)(Math.random() * NUMBER_OF_NAMES) + 1);
		
		return name;
	}
	
	public static String namePicker(int i){
		String s = "doo";
		
		switch(i)
		{
		case 1:
			s = "bung";
			break;
		case 2:
			s = "bu";
			break;
		case 3:
			s = "shi";
			break;
		case 4:
			s = "fla";
			break;
		case 5:
			s = "zib";
			break;
		case 6:
			s = "ja";
			break;
		case 7:
			s = "mo";
			break;
		case 8:
			s = "mar";
			break;
		case 9:
			s = "ha";
			break;
		case 10:
			s = "gir";
			break;
		case 11:
			s = "pin";
			break;
		case 12:
			s = "ren";
			break;
		case 13:
			s = "zo";
			break;
		case 14:
			s = "qui";
			break;
		case 15:
			s = "ya";
			break;
		case 16:
			s = "yo";
			break;
		case 17:
			s = "yi";
			break;
		case 18:
			s = "ye";
			break;
		case 19:
			s = "zi";
			break;
		case 20:
			s = "zo";
			break;
		case 21:
			s = "za";
			break;
		case 22:
			s = "ki";
			break;
		case 23:
			s = "kaz";
			break;
		case 24:
			s = "per";
			break;
		case 25:
			s = "po";
			break;
		case 26:
			s = "no";
			break;
		case 27:
			s = "ti";
			break;
		case 28:
			s = "jo";
			break;
		case 29:
			s = "dor";
			break;
		case 30:
			s = "ban";
			break;
		case 31:
			s = "go";
			break;
		case 32:
			s = "tab";
			break;
		case 33:
			s = "ol";
			break;
		case 34:
			s = "am";
			break;
		case 35:
			s = "en";
			break;
		}
		
		return s;
	}

}
