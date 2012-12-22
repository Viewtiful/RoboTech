package personnages;


import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;


public abstract class Ennemis extends Personnage {
	//l'image qui contient le sprite du robot
	private int deplacementAuto;
	private boolean deplacementAutoDroite;
	

	public Ennemis(float x, float y, float masse, float tailleBlockPerso) throws SlickException {
		super(x, y, masse, tailleBlockPerso);
		deplacementAuto = 0;
		deplacementAutoDroite = true;
	}

	public abstract void render(Graphics g);
	
	//gere le deplacement automatique des ennemies (a revoir, juste un test)
	public void update(int delta) {
		super.update(delta);

		if(deplacementAuto < 230 && deplacementAutoDroite) {
			applyForce(100, getVelY());
			deplacementAuto++;
		}
		else {
			deplacementAutoDroite = false;
			applyForce(-100, getVelY());
			deplacementAuto--;
			if(deplacementAuto == 0) {
				deplacementAutoDroite = true;
			}
		}
	}

}
