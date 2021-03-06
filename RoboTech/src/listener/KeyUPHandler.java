package listener;

import org.newdawn.slick.Input;

import personnages.Robot;

/**
 * Permet de gerer la touche Fleche Haut
 * @author Equipe RoboTech 
 */
public class KeyUPHandler extends KeyHandler {
	public KeyUPHandler() {
		super(Input.KEY_UP);
	}

	/**
	 * {@inheritDoc}
	 */
	public void handle(Input input, Robot robot) {
		// jumpForce applique pour faire sauter le personnage
		float jumpForce = 50000;

		if (robot.auSol()) {
			if ((input.isKeyPressed(get_key()) && robot.getEnergie() > 0 && !robot
					.getPlusEnergie()) || (input.isKeyPressed(get_key()))) {
				if (robot.getDirectionDroite()) {
					robot.applyForce(0, -jumpForce);
				} else {
					robot.applyForce(0, -jumpForce);
				}
			}
		}
		// si on n'appuye pas sur la touche saut et que le perso est en train de
		// sauter, on le fait redescendre doucement
		if (!input.isKeyDown(get_key())) {
			if (robot.getSaut()) {
				robot.setVelocity(robot.getVelX(), robot.getVelY() * 0.99f);
			}
		}
	}

}
