package listener;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import personnages.Robot;
import weapon.Balle;

/**
 * 
 * @author Antoine
 * Permet de gérer la touche Q
 */
public class KeyQHandler extends KeyHandler {

	public KeyQHandler() {
		super(Input.KEY_Q);
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void handle(Input input, Robot robot) {
		if (input.isKeyPressed(Input.KEY_Q)) {
			Balle balle = null;
			try {
				balle = robot.tirer();
			} catch (SlickException e) {
				System.out.println("SlickException");
			}
			if (balle != null)
				robot.monde.addBalles(balle);
		}
	}
}
