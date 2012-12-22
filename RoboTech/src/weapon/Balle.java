package weapon;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import interfaces.Drawable;
import items.Items;

public class Balle implements Drawable{

	private float x;
	private float y;
	Image skin;
	
	public Balle() throws SlickException
	{
		x = 0;
		y = 0;
		skin = new Image("res/bullet.png");
	}
	
	public Balle(float x,float y) throws SlickException
	{
		this.x = x;
		this.y = y;
		skin = new Image("res/bullet.png");
	}
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		skin = new Image("res/bullet.png");
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.drawImage(skin, x, y);
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		x++;
	}

}
