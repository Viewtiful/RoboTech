package interfaces;

import org.newdawn.slick.Graphics;
/**
 * 
 * @author Antoine
 * <i> Permet de sp�cifier avec un interface les objets pouvant �tre d�ssiner
 */
public interface Drawable {

	/**
	 * Permet d'afficher un composant graphique
	 * @param g Objet Graphique
	 * @see org.newdawn.Slick.Graphics
	 */
	public void render(Graphics g);
}
