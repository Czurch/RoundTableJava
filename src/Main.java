import java.awt.Image;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.czurch.rtl.GameModes.DungeonCrawler;
import com.czurch.rtl.mechanics.*;
import com.czurch.rtl.mechanics.Items.ItemList;
import com.czurch.rtl.mechanics.Worldbuilding.Cave;
import com.czurch.rtl.mechanics.Worldbuilding.WorldMap;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		MainMenu menu = new MainMenu();
		menu.setVisible(true);
		
		//while(true){
			//WorldMap terra = new WorldMap();
			//terra.display_height_map();
			//terra.display_real_map();
			//Cave cavern = new Cave(100, 100, 45);
			//Image img = cavern.getCaveImage();
			//JOptionPane.showMessageDialog(null, null, "Another", JOptionPane.YES_NO_OPTION, new ImageIcon(img.getScaledInstance(360, 360, Image.SCALE_AREA_AVERAGING)));
			//DungeonCrawler game = new DungeonCrawler();
			//System.out.println("End");
		//}
	}

}
