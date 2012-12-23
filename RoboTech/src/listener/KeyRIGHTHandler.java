package listener;


import org.newdawn.slick.Input;

import personnages.Robot;


public class KeyRIGHTHandler extends KeyHandler{

	public KeyRIGHTHandler()
	{
		super(Input.KEY_RIGHT);
	}

	public void handle(Input input,Robot robot)
	{
		float moveForce = 50;
		//jumpForce applique pour faire sauter le personnage
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
	
	public int get_key()
	{
		return key;
	}
}
