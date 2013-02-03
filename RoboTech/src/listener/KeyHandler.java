package listener;

import org.newdawn.slick.Input;

import personnages.Robot;

/**
 * Equipe RoboTech  Permet de gerer les commandes au clavier Un handler est affecte a une touche
 */
abstract public class KeyHandler {

	/**
	 * Code de la Cl�
	 * 
	 * @see org.newdawn.slick.Input
	 */
	int key;

	/**
	 * Gestion de l'�v�nement et action � r�aliser
	 * 
	 * @param input
	 *            Les �v�nements du clavier
	 * @param robot
	 *            Le robot, personnage control� par le joueur
	 */
	abstract public void handle(Input input, Robot robot);

	/**
	 * 
	 * @param key
	 *            Valeur de la Touche
	 */
	KeyHandler(int key) {
		this.key = key;
	}

	public int get_key() {
		return key;
	}
}
