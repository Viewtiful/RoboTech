package listener;

import org.newdawn.slick.Input;

import personnages.Robot;

/**
 * Permet de gerer la touche Fleche Droite
 * @author Equipe RoboTech 
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
		if (input.isKeyDown(get_key()) && robot.getEnergie() > 0
				&& !robot.getPlusEnergie()) {
			robot.setEnMouvement(true);
			robot.applyForce(moveForce, 0);
		}

		if (!input.isKeyDown(Input.KEY_W) && robot.getEnergie() > 0
				&& !robot.getPlusEnergie()) {
			if (robot.getSaut()) {
				robot.setVelocity(robot.getVelX(), robot.getVelY() * 0.99f);
			}
		}
	}

}
