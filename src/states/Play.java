package states;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Play extends BasicGameState{

	Image face;
	int faceX = 200;
	int faceY = 200;
	public String mouselocation = "No input yet.";
	
	public Play(int state)
	{
		
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		face = new Image("res/dinkle_boy.png");
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawString(mouselocation, 50, 50);
		g.drawImage(face, faceX, faceY);
		g.drawString("This is the play state", 100, 100);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		if(input.isKeyDown(Input.KEY_UP)){faceY -= 1;}
		if(input.isKeyDown(Input.KEY_DOWN)){faceY += 1;}
		if(input.isKeyDown(Input.KEY_LEFT)){faceX -= 1;}
		if(input.isKeyDown(Input.KEY_RIGHT)){faceX += 1;}
		if(input.isKeyDown(Input.KEY_ESCAPE)){sbg.enterState(0);}
		int xpos = Mouse.getX();
		int ypos = Mouse.getY();
		mouselocation = "Mouse Position x: "+ xpos + "  y: " + ypos;
	}

	@Override
	public int getID() {
		return 1;
	}

}
