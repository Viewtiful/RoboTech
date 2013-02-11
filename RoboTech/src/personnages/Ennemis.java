package personnages;

import jeu.Monde;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
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
	private Rectangle jauge;
	public Ennemis(float x, float y, float masse, float tailleBlockPerso,
			Monde monde) {
		super(x, y, masse, tailleBlockPerso, monde);
		deplacementAuto = 0;
		deplacementAutoDroite = true;
		jauge = new Rectangle(getX()+1,getY()-getTailleBlockPerso()/2+2,18,3);
		
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

	public void renderVie(Graphics g)
	{
		float x = getX();
		float y = getY()-getTailleBlockPerso()/2;
		float width = 25;
		float height= 3;
		jauge.setBounds(x, y, width*(getVie()/getMax()), height);
		g.setColor(Color.green);
		g.fill(jauge);
	}
}
