package states;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState{

	public String mouselocation = "No input yet.";
	
	public Menu(int state)
	{
		
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		g.drawString(mouselocation, 50, 50);
		
		g.fillRect(75, 100, 100, 100);
		g.drawString("Play Now!", 80, 70);
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		int xpos = Mouse.getX();
		int ypos = Mouse.getY();
		if((xpos>75&&xpos<175)&&(ypos>160&&ypos<260) && input.isMouseButtonDown(0))
		{
			sbg.enterState(1);
		}
		mouselocation = "Mouse Position x: "+ xpos + "  y: " + ypos;
	}
	
	@Override
	public int getID() {
		return 0;
	}
}
