package blocs;

import interfaces.SlickAdapter;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * <i>Elle caractérise les Blocs dynamique comme les plateforme</i>
 */
public abstract class BlocsDynamiques extends Blocs implements SlickAdapter {

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void render(Graphics g) {
		render_spec(g);
		get_image().drawCentered(get_x(), get_y());
	}
	/**
	 * @param box_image
	 * Image du rendu du blocs
	 * @param Width
	 * Largeur du Bloc
	 * @param Height
	 * Hauteur du Bloc
	 * @param x
	 * Abscisse du Bloc
	 * @param y
	 *Ordonnée du Bloc
	 */
	public BlocsDynamiques(Image box_image, float Width, float Height, float x,
			float y) {
		super(box_image, Width, Height, x, y);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		render(g);

	}
	/**
	 * Permet la possibilité au classe fille
	 * d'intégrer d'autre élement à dessiner
	 * @param g Objet Graphique
	 * @see org.newdawn.slick.Graphics
	 */
	public abstract void render_spec(Graphics g);
}