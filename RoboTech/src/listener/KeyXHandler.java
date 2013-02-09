package listener;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import personnages.Robot;
import weapon.Balle;

/**
 * 
 * @author Equipe RoboTech Permet de gerer la touche Q
 */
public class KeyXHandler extends KeyHandler {

	public KeyXHandler() {
		super(Input.KEY_X);
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void handle(Input input, Robot robot) {
		if (input.isKeyPressed(Input.KEY_X)) {
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
