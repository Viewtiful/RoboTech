package blocs;

import interfaces.SlickAdapter;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public abstract class BlocsDynamiques extends Blocs implements SlickAdapter {

	@Override
	public void render(Graphics g) {
		render_spec(g);
		get_image().drawCentered(get_x(), get_y());
	}

	public BlocsDynamiques(Image box_image, float Width, float Height, float x,
			float y) {
		super(box_image, Width, Height, x, y);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		render(g);

	}

	public abstract void render_spec(Graphics g);
}