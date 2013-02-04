package interfaces;

import org.newdawn.slick.Graphics;

/**
 * 
 * @author Equipe RoboTech <i> Permet de specifier avec un interface les objets
 *         pouvant etre dessiner </i>
 */
public interface Drawable {

	/**
	 * Permet d'afficher un composant graphique
	 * 
	 * @param g
	 *            Objet Graphique
	 * @see org.newdawn.Slick.Graphics
	 */
	public void render(Graphics g);
}
