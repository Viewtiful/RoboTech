package listener;

import org.newdawn.slick.Input;

import personnages.Robot;

/**
 * 
 * @author Antoine
 * Permet de gérer la touche Flèche Droite
 */
public class KeyRIGHTHandler extends KeyHandler {

	public KeyRIGHTHandler() {
		super(Input.KEY_RIGHT);
	}

	/**
	 * {@inheritDoc}
	 */
	public void handle(Input input, Robot robot) {
		float moveForce = 50;
		// jumpForce applique pour faire sauter le personnage
		if (input.isKeyDown(get_key())) {
			robot.setEnMouvement(true);
			robot.applyForce(moveForce, 0);
		}

		if (!input.isKeyDown(Input.KEY_W)) {
			if (robot.getSaut()) {
				robot.setVelocity(robot.getVelX(), robot.getVelY() * 0.99f);
			}
		}
	}

	
}
