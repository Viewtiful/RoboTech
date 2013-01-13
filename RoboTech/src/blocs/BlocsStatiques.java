package blocs;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * 
 * @author Antoine Représente les Blocs Statiques, qui ne bouge pas
 */
public abstract class BlocsStatiques extends Blocs {

	/**
	 * @param box_image
	 *            Image du rendu du blocs
	 * @param width
	 *            Largeur du Bloc
	 * @param height
	 *            Hauteur du Bloc
	 * @param x
	 *            Abscisse du Bloc
	 * @param y
	 *            Ordonnée du Bloc
	 */
	public BlocsStatiques(Image box_image, float width, float height, float x,
			float y) {
		super(box_image, width, height, x, y);
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void render(Graphics g) {
		get_image().drawCentered(get_x(), get_y());

	}

	/**
	 * {@inheritDoc}
	 */
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub

	}

}
