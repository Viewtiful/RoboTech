package items;

import net.phys2d.raw.Body;
import net.phys2d.raw.shapes.Box;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Un baril
 * @author Equipe RoboTech
 *
 */
public class Baril extends Items {
	/**
	 * image du baril
	 */
	private Image image;
	/**
	 * La largeur du baril
	 */
	private float width;
	/**
	 * La hauteur du baril
	 */
	private float height;

	/**
	 * 
	 * @param x Abscisse du Baril
	 * @param y Ordonnee du Baril
	 * @param width largeur
	 * @param height hauteur
	 * @param mass masse 
	 * @throws SlickException
	 */
	public Baril(float x, float y, float width, float height, float mass)
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
	 * Initialisation
	 * {@inheritDoc}
	 */
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		image = new Image("res/baril.png");

	}

	/**
	 * Affichage
	 * {@inheritDoc}
	 */
	public void render(Graphics g) {
		g.translate(getX(), getY());
		g.rotate(0, 0, (float) Math.toDegrees(body.getRotation()));
		image.draw(-width / 2, -height / 2, width, height);
		g.rotate(0, 0, (float) -Math.toDegrees(body.getRotation()));
		g.translate(-getX(), -getY());

	}

	/**
	 * {@inheritDoc}
	 */
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		render(g);
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub

	}

}
