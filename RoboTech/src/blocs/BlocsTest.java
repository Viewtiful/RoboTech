package blocs;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import personnages.Robot;

public class BlocsTest extends BlocsDynamiques {

	private boolean showed = false;
	private Image image_showed;
	private Image image_base;
	public BlocsTest(Image box_image, float Width, float Height, float x,
			float y,Image showed) {
		super(box_image, Width, Height, x, y);
		this.image_showed = showed;
		this.image_base = box_image;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	
	public void render_spec(Graphics g) {
		if(showed==true)
		{
			set_image(image_showed);
			showed = false;
		}
		else if(showed == false)
			set_image(image_base);
	}

	@Override
	public void collision_action(Robot player) {
		showed = true;
	}

}
