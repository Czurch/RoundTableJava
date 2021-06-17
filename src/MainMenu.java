import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import java.awt.Image;
import java.awt.image.BufferedImage;

import com.czurch.rtl.mechanics.Worldbuilding.Cave;
import com.czurch.rtl.mechanics.Worldbuilding.WorldMap;

public class MainMenu extends JFrame {

	JPanel mainMenuPanel;
	JPanel mapGenPanel;
	JPanel dungeonGenPanel;
	ImageIcon generatedWorldImage;
	ImageIcon generatedDungeonImage;
	
	public MainMenu()
	{
		super("Roundtable Java v0.3");
	    setSize( 580, 580 );
	    setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	    
	    initializeMainMenuPanel();
	    initializeMapGenPanel();
	    initializeDungeonGenPanel();
	    
	    changePanel(mainMenuPanel);
		
	}
	
	private void goToGenWorldPanel()
	{
		//GenerateWorld();
		changePanel(mapGenPanel);
	}
	
	private void goToGenDungeonPanel()
	{
		changePanel(dungeonGenPanel);
	}
	
	private void GenerateWorld()
	{
		WorldMap terra = new WorldMap();
		//terra.display_height_map();
		BufferedImage img = terra.display_real_map();
		generatedWorldImage = new ImageIcon(img.getScaledInstance(terra.WORLD_WIDTH, terra.WORLD_HEIGHT, Image.SCALE_AREA_AVERAGING));
	}
	
	private void GenerateDungeon()
	{
		Cave cavern = new Cave(100, 100, 45);
		Image img = cavern.getCaveImage();
		generatedDungeonImage = new ImageIcon(img.getScaledInstance(512, 512, Image.SCALE_AREA_AVERAGING));
		
	}
	
	private void changePanel(JPanel panel) {
	    getContentPane().removeAll();
	    getContentPane().add(panel, BorderLayout.CENTER);
	    getContentPane().doLayout();
	    update(getGraphics());
	    revalidate();
	}
	
	private void initializeMainMenuPanel()
	{
	    //Create the layout of the main Menu
	    mainMenuPanel = new JPanel();
		mainMenuPanel.setLayout(new BoxLayout(mainMenuPanel, BoxLayout.PAGE_AXIS));
		mainMenuPanel.setAlignmentY(CENTER_ALIGNMENT);
		mainMenuPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		add(mainMenuPanel);
		
		JPanel genpanel = new JPanel();
		genpanel.setBorder(new EmptyBorder(10, 0, 10, 0));
		genpanel.setAlignmentY(CENTER_ALIGNMENT);
		genpanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		mainMenuPanel.add(genpanel);
		
		JButton genWorld = new JButton();
		genWorld.setText("Generate World");
	    genWorld.setVerticalAlignment(SwingConstants.BOTTOM);  // of text and icon
	    genWorld.setHorizontalAlignment(SwingConstants.RIGHT); // of text and icon
	    genWorld.setHorizontalTextPosition(SwingConstants.LEFT); // of text relative to icon
	    genWorld.setVerticalTextPosition(SwingConstants.CENTER);    // of text relative to icon
	    genWorld.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
	    genWorld.setAlignmentX(Component.CENTER_ALIGNMENT);
	    genWorld.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){  
	    	            goToGenWorldPanel();  
	    	        }  
	    });
	    genpanel.add(genWorld);
	    
		JPanel dungeonPanel = new JPanel();
		dungeonPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
		dungeonPanel.setAlignmentY(CENTER_ALIGNMENT);
		dungeonPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		mainMenuPanel.add(dungeonPanel);
	    
		JButton genDungeon = new JButton();
		genDungeon.setText("Generate Dungeon");
	    genDungeon.setVerticalAlignment(SwingConstants.BOTTOM);  // of text and icon
	    genDungeon.setHorizontalAlignment(SwingConstants.RIGHT); // of text and icon
	    genDungeon.setHorizontalTextPosition(SwingConstants.LEFT); // of text relative to icon
	    genDungeon.setVerticalTextPosition(SwingConstants.CENTER);    // of text relative to icon
	    genDungeon.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
	    genDungeon.setAlignmentX(Component.CENTER_ALIGNMENT);
	    genDungeon.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){  
	    	            goToGenDungeonPanel();  
	    	        }  
	    });
	    dungeonPanel.add(genDungeon);
	}
	
	private void initializeMapGenPanel()
	{
		mapGenPanel = new JPanel();
		mapGenPanel.setLayout(new BoxLayout(mapGenPanel, BoxLayout.PAGE_AXIS));
		mapGenPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		//add(mapGenPanel);
		
		JPanel mapImagePanel = new JPanel();
		mapImagePanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		mapGenPanel.add(mapImagePanel);
		
		JLabel map = new JLabel();
		GenerateWorld();
		map.setIcon(generatedWorldImage);
		mapImagePanel.add(map);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		buttonPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		mapGenPanel.add(buttonPanel);
		
		JButton generateNew = new JButton();
		generateNew.setText("Generate New World");
		generateNew.setVerticalAlignment(SwingConstants.BOTTOM);  // of text and icon
		generateNew.setHorizontalAlignment(SwingConstants.RIGHT); // of text and icon
	    generateNew.setHorizontalTextPosition(SwingConstants.LEFT); // of text relative to icon
	    generateNew.setVerticalTextPosition(SwingConstants.TOP);    // of text relative to icon
	    generateNew.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
	    generateNew.setAlignmentX(Component.CENTER_ALIGNMENT);
	    generateNew.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){  
	    	            GenerateWorld();
	    	            map.setIcon(generatedWorldImage);
	    	            revalidate();
	    	        }  
	    });
	    buttonPanel.add(generateNew);
	    
		JButton backToMain = new JButton();
		backToMain.setText("Back to Main Menu");
		backToMain.setVerticalAlignment(SwingConstants.BOTTOM);  // of text and icon
		backToMain.setHorizontalAlignment(SwingConstants.RIGHT); // of text and icon
	    backToMain.setHorizontalTextPosition(SwingConstants.LEFT); // of text relative to icon
	    backToMain.setVerticalTextPosition(SwingConstants.TOP);    // of text relative to icon
	    backToMain.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
	    backToMain.setAlignmentX(Component.CENTER_ALIGNMENT);
	    backToMain.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){  
	    	            changePanel(mainMenuPanel);  
	    	        }  
	    });
		buttonPanel.add(backToMain);
		
	}
	
	private void initializeDungeonGenPanel()
	{
		dungeonGenPanel = new JPanel();
		dungeonGenPanel.setLayout(new BoxLayout(dungeonGenPanel, BoxLayout.PAGE_AXIS));
		dungeonGenPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		//add(dungeonGenPanel);
		
		JPanel dungeonImagePanel = new JPanel();
		dungeonImagePanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		dungeonGenPanel.add(dungeonImagePanel);
		
		JLabel map = new JLabel();
		GenerateDungeon();
		map.setIcon(generatedDungeonImage);
		dungeonImagePanel.add(map);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		buttonPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		dungeonGenPanel.add(buttonPanel);
		
		JButton generateNew = new JButton();
		generateNew.setText("Generate New Dungeon");
		generateNew.setVerticalAlignment(SwingConstants.BOTTOM);  // of text and icon
		generateNew.setHorizontalAlignment(SwingConstants.RIGHT); // of text and icon
	    generateNew.setHorizontalTextPosition(SwingConstants.LEFT); // of text relative to icon
	    generateNew.setVerticalTextPosition(SwingConstants.TOP);    // of text relative to icon
	    generateNew.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
	    generateNew.setAlignmentX(Component.CENTER_ALIGNMENT);
	    generateNew.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){  
	    				GenerateDungeon();
	    	            map.setIcon(generatedDungeonImage);
	    	            revalidate();
	    	        }  
	    });
	    buttonPanel.add(generateNew);
	    
		JButton backToMain = new JButton();
		backToMain.setText("Back to Main Menu");
		backToMain.setVerticalAlignment(SwingConstants.BOTTOM);  // of text and icon
		backToMain.setHorizontalAlignment(SwingConstants.RIGHT); // of text and icon
	    backToMain.setHorizontalTextPosition(SwingConstants.LEFT); // of text relative to icon
	    backToMain.setVerticalTextPosition(SwingConstants.TOP);    // of text relative to icon
	    backToMain.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
	    backToMain.setAlignmentX(Component.CENTER_ALIGNMENT);
	    backToMain.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){  
	    	            changePanel(mainMenuPanel);  
	    	        }  
	    });
		buttonPanel.add(backToMain);
	}
}
