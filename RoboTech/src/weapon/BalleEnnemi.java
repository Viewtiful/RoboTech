package weapon;

import org.newdawn.slick.SlickException;

import personnages.Personnage;
import personnages.Robot;

/**
 * Classe gérant les balles pour les serpents
 * 
 * @author Equipe RoboTech
 * 
 */
public class BalleEnnemi extends Balle {

	/**
	 * Constructeur de la classe BalleEnnemiSerpent
	 * 
	 * @param x
	 * @param y
	 * @param directionDroite
	 * @param masse
	 * @param value
	 * @throws SlickException
	 */
	public BalleEnnemi(float x, float y, Boolean directionDroite,
			float masse, int value) throws SlickException {
		super(x, y, directionDroite, masse, value);

	}

	/**
	 * Gestion de la cible, dommage, si c'est le robot
	 */
	@Override
	public boolean cible(Personnage p) {
		assert(p != null);
		
		if (p instanceof Robot)
			return true;

		return false;
	}

}
