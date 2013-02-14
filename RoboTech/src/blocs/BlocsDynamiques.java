package blocs;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import slick.Adapter;

/**
 * <i>Elle caracterise les Blocs dynamique comme les plateforme</i>
 * 
 * @author Equipe RoboTech
 */
public abstract class BlocsDynamiques extends Blocs implements Adapter {

	

	/**
	 * @param box_image
	 *            Image du rendu du blocs
	 * @param Width
	 *            Largeur du Bloc
	 * @param Height
	 *            Hauteur du Bloc
	 * @param x
	 *            Abscisse du Bloc
	 * @param y
	 *            Ordonn�e du Bloc
	 */
	public BlocsDynamiques(Image box_image, float Width, float Height,
			Point center) {
		super(box_image, Width, Height, center);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		renderSpec(g);
		getImage().drawCentered(center.getX(), center.getY());
	
	}

	/**
	 * Permet la possibilit� au classe fille d'int�grer d'autre �lement
	 * � dessiner
	 * 
	 * @param g
	 *            Objet Graphique
	 * @see org.newdawn.slick.Graphics
	 */
	public abstract void renderSpec(Graphics g);

	@Override
	public String toString() {
		return super.toString();
	}
}