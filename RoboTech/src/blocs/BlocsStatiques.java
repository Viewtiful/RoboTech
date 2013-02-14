package blocs;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * 
 * @author Equipe RoboTech 
 * Represente les Blocs Statiques, qui ne bouge pas
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
	 *            Ordonn�e du Bloc
	 */
	public BlocsStatiques(Image box_image, float width, float height, Point org) {
		super(box_image, width, height, org);
	}

	

	/**
	 * {@inheritDoc}
	 */
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
	
	}

	/**
	 * {@inheritDoc}
	 */
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		getImage().drawCentered(center.getX(), center.getY());

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
	
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
