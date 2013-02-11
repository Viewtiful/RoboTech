package personnages;

import jeu.Monde;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import weapon.Balle;
import weapon.BalleEnnemi;
import weapon.BalleRobot;
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
	private int degatBalle = 0;
	private final int timeout = 2;
	/**
	 * Robot détecté
	 */
	private boolean detected = false;
	/**
	 * Rayon de detection
	 */
	private float rayonDetection;
	
	private int cpt = 0;
	private int sec = 0;
	public float getRayonDetection() {
		return rayonDetection;
	}

	public void setRayonDetection(float rayonDetection) {
		this.rayonDetection = rayonDetection;
	}

	public boolean getDetected() {
		return detected;
	}

	public void setDetected(boolean detected) {
		this.detected = detected;
	}

	public Ennemis(float x, float y, float masse, float tailleBlockPerso,
			Monde monde, float rayonDetection,int degatBalle) {
		super(x, y, masse, tailleBlockPerso, monde);
		deplacementAuto = 0;
		deplacementAutoDroite = true;
		jauge = new Rectangle(getX()+1,getY()-getTailleBlockPerso()/2+2,18,3);
		player = monde.getPlayer();
		this.rayonDetection = rayonDetection;
		this.degatBalle = degatBalle;
	}

	/**
	 * Le robot à suivre
	 */
		private Robot player;

	public Robot getPlayer() {
		return player;
	}

	public void setPlayer(Robot player) {
		this.player = player;
	}

	public float pythagore(float xa, float ya, float xb, float yb) {
		float diff1 = xb - xa;
		float diff2 = yb - ya;
		diff1 = diff1 * diff1;
		diff2 = diff2 * diff2;
		return (float) Math.sqrt(diff1 + diff2);
	}

	public void detected() {
		float xa = player.getX();
		float ya = player.getY();
		float xb = getX();
		float yb = getY();
		detected = (pythagore(xa, ya, xb, yb) < rayonDetection) && !detected;
	}

	// gere le deplacement automatique des ennemies (a revoir, juste un test)
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		super.update(container, game, delta);

		cpt++;
		if(cpt==60)
		{
			sec++;
			cpt=0;
		}
		detected();
		if(sec>=timeout && detected)
		{
			monde.addBalles(tirer());
			sec=0;
		}
		System.out.println("sec = "+sec+"cpt = "+cpt+"detected"+detected);
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
		float pourcentage = (float)getVie()/(float)getMax();
		jauge.setBounds(x, y, width*pourcentage, height);
		if(pourcentage>0.50)
			g.setColor(Color.green);
		else if(pourcentage<0.25)
			g.setColor(Color.red);
		else
			g.setColor(Color.orange);
		g.fill(jauge);
	}
	
	/**
	 * Robot viens de tirer, on crÃ©e une balle et la tire
	 * 
	 * @return
	 * @throws SlickException
	 */
	public Balle tirer() throws SlickException {
			Balle balle = new BalleEnnemi(getX(), getY(), getDirectionDroite(),
					0.01f, 1);
			balle.applyForce(10000, 0);
			return balle;
		
	}
}
