package interfaces;

import org.newdawn.slick.Graphics;
/**
 * 
 * @author Antoine
 * <i> Permet de spécifier avec un interface les objets pouvant être déssiner
 */
public interface Drawable {

	/**
	 * Permet d'afficher un composant graphique
	 * @param g Objet Graphique
	 * @see org.newdawn.Slick.Graphics
	 */
	public void render(Graphics g);
}
