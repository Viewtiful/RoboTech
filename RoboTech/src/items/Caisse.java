package items;

import net.phys2d.raw.Body;
import net.phys2d.raw.shapes.Box;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * 
 * @author Equipe RoboTech  Une caisse
 */
public class Caisse extends Items {
	/** The image to display for the crate */
	private Image image;
	/** The width of the crate */
	private float width;
	/** The height of the crate */
	private float height;

	/**
	 * 
	 * @param x
	 *            Position horizontale de la caisse
	 * @param y
	 *            Position verictale de la caisse
	 * @param width
	 *            Largeur de la caisse
	 * @param height
	 *            Hauteur de la caisse
	 * @param mass
	 *            Masse de la caisse
	 */
	public Caisse(float x, float y, float width, float height, float mass) {
		this.width = width;
		this.height = height;

		body = new Body(new Box(width, height), mass);
		body.setPosition(x, y);
		body.setFriction(0.1f);
	}

	public Body getBody() {
		return body;
	}

	public void preUpdate(int delta) {
	}

	@Override
	public float getWidth() {
		return width;
	}

	@Override
	public float getHeight() {
		return height;
	}

	@Override
	public void setPickedUp(boolean b) {

	}

	@Override
	/**
	{@inheritDoc}
	 */
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		image = new Image("res/caisse.jpg");

	}

	/**
	 * {@inheritDoc}
	 */
	public void render(Graphics g) {
		g.translate(getX(), getY());
		g.rotate(0, 0, (float) Math.toDegrees(body.getRotation()));
		image.draw(-width / 2, -height / 2, width, height);
		g.rotate(0, 0, (float) -Math.toDegrees(body.getRotation()));
		g.translate(-getX(), -getY());

	}

	@Override
	/**
	{@inheritDoc}
	 */
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		render(g);
	}

	@Override
	/**
	{@inheritDoc}
	 */
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {

	}

}
