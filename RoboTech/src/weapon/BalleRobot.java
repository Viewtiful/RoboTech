package weapon;

import org.newdawn.slick.SlickException;

import personnages.Personnage;

public class BalleRobot extends Balle {

	public BalleRobot(float x, float y, Boolean directionDroite, float masse,
			int value) throws SlickException {
		super(x, y, directionDroite, masse, value);

	}

	@Override
	public boolean cible(Personnage p) {
		return true;
	}

}
