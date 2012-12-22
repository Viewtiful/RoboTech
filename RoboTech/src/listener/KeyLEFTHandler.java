package listener;


import org.newdawn.slick.Input;

import personnages.Robot;

public class KeyLEFTHandler extends KeyHandler{

	public KeyLEFTHandler()
	{
		super(Input.KEY_LEFT);
	}

	public void handle(Input input,Robot hero)
	{
		float moveForce = 50;
		// setup the player's moving flag, this control the animation
		hero.setEnMouvement(false);
		if (input.isKeyDown(get_key())) {
			hero.setEnMouvement(true);
			hero.applyForce(-moveForce, 0);
		}
		
		if (!input.isKeyDown(Input.KEY_W)) {
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
