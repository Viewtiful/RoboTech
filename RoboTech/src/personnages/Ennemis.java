package personnages;

import interfaces.Drawable;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Ennemis extends Personnage {
	// l'image qui contient le sprite du robot
	private int deplacementAuto;
	private boolean deplacementAutoDroite;

	public Ennemis(float x, float y, float masse, float tailleBlockPerso) {
		super(x, y, masse, tailleBlockPerso);
		deplacementAuto = 0;
		deplacementAutoDroite = true;
	}

	// gere le deplacement automatique des ennemies (a revoir, juste un test)
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		super.update(container, game, delta);

		if (deplacementAuto < 230 && deplacementAutoDroite) {
			applyForce(100, getVelY());
			deplacementAuto++;
		} else {
			deplacementAutoDroite = false;
			applyForce(-100, getVelY());
			deplacementAuto--;
			if (deplacementAuto == 0) {
				deplacementAutoDroite = true;
			}
		}
	}

}
