package items;

import net.phys2d.raw.Body;
import net.phys2d.raw.shapes.Box;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Represente une poutre
 * @author Equipe RoboTech 
 */
public class Poutre extends Items {
	/**
	 * Image de la poutre
	 */
	private Image image;
	/**
	 * Largeur de la poutre
	 */
	private float width;
	/**
	 * Hauteur de la poutre
	 */
	private float height;

	/**
	 * 
	 * @param x
	 *            Position horizontale de la poutre
	 * @param y
	 *            Position verictale de la poutre
	 * @param width
	 *            Largeur de la poutre
	 * @param height
	 *            Hauteur de la poutre
	 * @param mass
	 *            Masse de la poutre
	 * @throws SlickException
	 * @see org.newdawn.slick.SlickException
	 */
	public Poutre(float x, float y, float width, float height, float mass)
			throws SlickException {
		assert(x >= 0);
		assert(y >= 0);
		assert(width > 0);
		assert(height > 0);
		assert(mass > 0);
		
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
	public float getWidth() {
		return width;
	}

	@Override
	public float getHeight() {
		return height;
	}

	@Override
	public void setPickedUp(boolean b) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		image = new Image("res/poutre.png");

	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		render(g);

	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub

	}

}
