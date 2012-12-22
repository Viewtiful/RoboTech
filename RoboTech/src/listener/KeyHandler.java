package listener;

import org.newdawn.slick.Input;

import personnages.Robot;

/* Cette classe permet de gérer les évenements liées aux clavier
 * Elle permet de rendre le code moins lourd en dédiant un handler par touche 
 */
abstract public class KeyHandler {

		int key;
		abstract public void handle(Input input,Robot hero);
		KeyHandler(int key)
		{
				this.key = key;
		}
		
		abstract public int get_key();
		
}
