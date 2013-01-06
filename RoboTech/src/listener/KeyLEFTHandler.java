package listener;

import org.newdawn.slick.Input;

import personnages.Robot;

/**
 * 
 * @author Antoine Permet de gérer la touche Flèche Gauche
 */
public class KeyLEFTHandler extends KeyHandler {

	public KeyLEFTHandler() {
		super(Input.KEY_LEFT);
	}

	/**
	 * {@inheritDoc}
	 */
	public void handle(Input input, Robot robot) {
		float moveForce = 50;
		// setup the player's moving flag, this control the animation
		robot.setEnMouvement(false);
		if (input.isKeyDown(get_key())) {
			robot.setEnMouvement(true);
			robot.applyForce(-moveForce, 0);
		}

		if (!input.isKeyDown(Input.KEY_W)) {
			if (robot.getSaut()) {
				robot.setVelocity(robot.getVelX(), robot.getVelY() * 0.99f);
			}
		}
	}

}
