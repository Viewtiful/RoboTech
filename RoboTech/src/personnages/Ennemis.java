package personnages;

import jeu.Monde;

import org.newdawn.slick.GameContainer;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
/**
 * 
 * @author Antoine
 *
 */
public abstract class Ennemis extends Personnage {
	// l'image qui contient le sprite du robot
	private int deplacementAuto;
	private boolean deplacementAutoDroite;

	public Ennemis(float x, float y, float masse, float tailleBlockPerso,
			Monde monde) {
		super(x, y, masse, tailleBlockPerso, monde);
		deplacementAuto = 0;
		deplacementAutoDroite = true;
	}

	// gere le deplacement automatique des ennemies (a revoir, juste un test)
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		super.update(container, game, delta);

		if (deplacementAutoDroite
				&& monde.estSolPosition((int) (this.getX()),
						(int) (this.getY() + 32), this)) {
			applyForce(100, getVelY());
			deplacementAutoDroite = true;
			if (!monde.estSolPosition((int) (this.getX() + 10),
					(int) (this.getY() + 32), this)) {
				deplacementAutoDroite = false;
			}
		} else if (!deplacementAutoDroite
				&& monde.estSolPosition((int) (this.getX()),
						(int) (this.getY() + 32), this)) {
			applyForce(-100, getVelY());
			deplacementAutoDroite = false;
			if (!monde.estSolPosition((int) (this.getX() - 10),
					(int) (this.getY() + 32), this)) {
				deplacementAutoDroite = true;
			}
		}
	}

}
