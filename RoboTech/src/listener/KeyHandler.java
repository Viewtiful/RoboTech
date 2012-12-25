package listener;

import org.newdawn.slick.Input;

import personnages.Robot;

/* Cette classe permet de g�rer les �venements li�es aux clavier
 * Elle permet de rendre le code moins lourd en d�diant un handler par touche 
 */
abstract public class KeyHandler {

	int key;

	abstract public void handle(Input input, Robot robot);

	KeyHandler(int key) {
		this.key = key;
	}

	abstract public int get_key();

}
