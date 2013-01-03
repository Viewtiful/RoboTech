package blocs;

import interfaces.SlickAdapter;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import bouton.Signal;

public abstract class BlocsDynamiques extends Blocs implements Signal,
		SlickAdapter {

	private boolean signal;

	// L'interface Receiver permettra d'implémenté une fonction qui sera appélé
	// lorsqu'un interrupteur
	// sera actionné.
	@Override
	public void render(Graphics g) {
		get_image().drawCentered(get_x(), get_y());
	}

	public BlocsDynamiques(Image box_image, float Width, float Height,float x,float y) {
		super(box_image, Width, Height, x, y);
		signal = false;
	}

	public boolean get_signal() {
		return signal;
	}

	public void set_signal(boolean signal) {
		this.signal = signal;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		render(g);

	}
}