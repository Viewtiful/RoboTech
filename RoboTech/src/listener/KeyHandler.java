package listener;

import org.newdawn.slick.Input;

import personnages.Robot;

/** 
 * Permet de gérer les commandes au clavier
 * Un handler est affecté à une touche 
 */
abstract public class KeyHandler {

	/**
	 * Code de la Clé
	 * @see org.newdawn.slick.Input
	 */
	int key;

	/**
	 * Gestion de l'évènement et action à réaliser
	 * @param input Les évènements du clavier
	 * @param robot Le robot, personnage controlé par le joueur
	 */
	abstract public void handle(Input input, Robot robot);

	/**
	 * 
	 * @param key Valeur de la Touche
	 */
	KeyHandler(int key) {
		this.key = key;
	}

	public int get_key()
	{
		return key;
	}
}
