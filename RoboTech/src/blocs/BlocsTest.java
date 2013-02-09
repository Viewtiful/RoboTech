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

	public BlocsTest(Image box_image, float Width, float Height, Point org,
			Image showed) {
		super(box_image, Width, Height, org);
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

	public void renderSpec(Graphics g) {
		if (showed == true) {
			setImage(image_showed);
			showed = false;
		} else if (showed == false)
			setImage(image_base);
	}

	@Override
	public void collisionAction(Robot player) {
		showed = true;
	}

}
