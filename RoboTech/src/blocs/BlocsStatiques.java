package blocs;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 * 
 * @author Antoine Représente les Blocs Statiques, qui ne bouge pas
 */
public class BlocsStatiques extends Blocs {

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
		// TODO Auto-generated method stub

	}

}
