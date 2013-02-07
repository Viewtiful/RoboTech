package weapon;

import org.newdawn.slick.SlickException;

import personnages.Personnage;

/**
 * Classe gérant les balles pour le robot
 * @author Equipe RoboTech
 *
 */
public class BalleRobot extends Balle {

	public BalleRobot(float x, float y, Boolean directionDroite, float masse,
			int value) throws SlickException {
		super(x, y, directionDroite, masse, value);

	}

	/**
	 * Gestion des cibles pour le robot, affectent tous les ennemis
	 */
	@Override
	public boolean cible(Personnage p) {
		return true;
	}

}
