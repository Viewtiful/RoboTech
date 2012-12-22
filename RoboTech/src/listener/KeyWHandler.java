package listener;

import org.newdawn.slick.Input;

import personnages.Robot;


public class KeyWHandler extends KeyHandler{
	public KeyWHandler()
	{
		super(Input.KEY_W);
	}
	
	public void handle(Input input,Robot hero)
	{
		float moveForce = 50;
		//jumpForce applique pour faire sauter le personnage
		float jumpForce = 50000;

		if (hero.auSol()) {
			if ((input.isKeyPressed(Input.KEY_LCONTROL)) ||
					(input.isKeyPressed(Input.KEY_RCONTROL))) {
				if (hero.getDirectionDroite()) {
						hero.applyForce(0, -jumpForce);
					} else {
							hero.applyForce(0, -jumpForce);
					}
			}
		}
		//si on n'appuye pas sur la touche saut et que le perso est en train de sauter, on le fait redescendre doucement
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
