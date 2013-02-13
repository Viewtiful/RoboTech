package listener;

import org.newdawn.slick.Input;

import personnages.Robot;

/**
 * Permet de gerer les commandes au clavier Un handler est
 * affecte a une touche
 * Equipe RoboTech 
 */
abstract public class KeyHandler {

	/**
	 * Code de la Cle
	 * 
	 * @see org.newdawn.slick.Input
	 */
	int key;

	/**
	 * Gestion de l'evenement et action a realiser
	 * 
	 * @param input
	 *            Les evenements du clavier
	 * @param robot
	 *            Le robot, personnage controle par le joueur
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
