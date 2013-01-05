package weapon;

import org.newdawn.slick.SlickException;

import personnages.Personnage;
import personnages.Robot;

public class BalleEnnemiVert extends Balle {

	public BalleEnnemiVert(float x, float y, Boolean directionDroite,
			float masse, int value) throws SlickException {
		super(x, y, directionDroite, masse, value);

	}

	@Override
	public boolean cible(Personnage p) {
		if (p instanceof Robot)
			return true;

		return false;
	}

}
