package listener;


import org.newdawn.slick.Input;

import personnages.Robot;


public class KeyRIGHTHandler extends KeyHandler{

	public KeyRIGHTHandler()
	{
		super(Input.KEY_RIGHT);
	}

	public void handle(Input input,Robot hero)
	{
		float moveForce = 50;
		//jumpForce applique pour faire sauter le personnage
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			hero.setEnMouvement(true);
			hero.applyForce(moveForce, 0);
		}
		
		
		if (!input.isKeyDown(Input.KEY_LCONTROL)) {
			if (hero.getSaut()) {
					hero.setVelocity(hero.getVelX(), hero.getVelY() * 0.99f);
			}
		}
	}
	
	public int get_key()
	{
		return key;
	}
}
