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


/**
 * Gere les ennemis du jeu
 * @author Equipe RoboTech
 *
 */
public abstract class Ennemis extends Personnage {

	/**
	 * Gere dans quel direction se deplace l'ennemi
	 */
	private boolean deplacementAutoDroite;
	
	/**
	 * Gere l'affichage du rectangle de sa vie, au dessus de sa tete
	 */
	private Rectangle jauge;
	
	/**
	 * Gere les degats qu'il fait lorsqu'il tire
	 */
	private int degatBalle = 0;
	
	
	private final int timeout = 2;
	
	/**
	 * Robot detecte
	 */
	private boolean detected = false;
	/**
	 * Rayon de detection
	 */
	private float rayonDetection;
	
	private int cpt = 0;
	private int sec = 0;
	
	/**
	 * Le robot a suivre
	 */
	private Robot player;
	
	/**
	 * Retourne le rayon autour de l'ennemi
	 * @return
	 */
	public float getRayonDetection() {
		return rayonDetection;
	}

	/**
	 * Donne une valeur au rayon autour de l'ennemi
	 * @param rayonDetection
	 */
	public void setRayonDetection(float rayonDetection) {
		this.rayonDetection = rayonDetection;
	}

	/**
	 * Retourne la valeur de detected
	 * @return
	 */
	public boolean getDetected() {
		return detected;
	}

	/**
	 * Met la valeur de detected
	 * @return
	 */
	public void setDetected(boolean detected) {
		this.detected = detected;
	}

	/**
	 * Constructeur de ennemi
	 * @param x sa position en x
	 * @param y sa position en y
	 * @param masse sa masse
	 * @param tailleBlockPerso : la taille de l'ennemi pour sa hitbox, sa taille dans le monde physique
	 * @param monde le monde dans lequel il evolue
	 * @param rayonDetection le rayon de detection autour de lui pour detecter le robot
	 * @param degatBalle les degats qu'il fait subir au robot
	 */
	public Ennemis(float x, float y, float masse, float tailleBlockPerso,
			Monde monde, float rayonDetection,int degatBalle) {
		//initialise les variables
		super(x, y, masse, tailleBlockPerso, monde);
		
		assert(rayonDetection >= 0);
		assert(degatBalle >= 0);
		
		deplacementAutoDroite = true;
		jauge = new Rectangle(getX()+1,getY()-getTailleBlockPerso()/2+2,18,3);
		player = monde.getPlayer();
		this.rayonDetection = rayonDetection;
		this.degatBalle = degatBalle;
	}



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

	
	/**
	 * gere le deplacement automatique des ennemies
	 */
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		super.update(container, game, delta);

		float xa = player.getX();
		float ya = player.getY();
		float xb = getX();
		float yb = getY();
	
		cpt++;
		if(cpt==60)
		{
			sec++;
			cpt=0;
		}
		if(	pythagore(xa, ya, xb, yb) < rayonDetection && !getDetected())
			setDetected(true);
		if(sec>=timeout && getDetected())
		{
			monde.addBalles(tirer());
			sec=0;
		}
		
		//si l'ennemi se deplace vers la droite, il teste le bloc juste devant lui pour savoir s'il peut continuer d'avancer ou faire demi-tour
		if (deplacementAutoDroite
				&& monde.estSolPosition((int) (this.getX()),
						(int) (this.getY() + 32), this)) {
			applyForce(100, getVelY());
			deplacementAutoDroite = true;
			if (!monde.estSolPosition((int) (this.getX() + 10),
					(int) (this.getY() + 32), this)) {
				deplacementAutoDroite = false;
			}
		} 
		//si l'ennemi se deplace vers la gauce, il teste le bloc juste devant lui pour savoir s'il peut continuer d'avancer ou faire demi-tour
		else if (!deplacementAutoDroite
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

	/**
	 * Gere l'affichage de la vie de l'ennemi
	 * @param g
	 */
	public void renderVie(Graphics g)
	{
		float x = getX();
		float y = getY()-getTailleBlockPerso()/2;
		float width = 25;
		float height= 3;
		float pourcentage = (float)getVie()/(float)getMax();
		jauge.setBounds(x, y, width*pourcentage, height);
		//Si sa vie est suprieure a 50%, elle est verte
		if(pourcentage>0.50)
			g.setColor(Color.green);
		//Si sa vie est inferieure a 25%, elle est rouge	
		else if(pourcentage<0.25)
			g.setColor(Color.red);
		//si elle est entre ces deux valeurs 25% < x < 50%, elle est orange
		else
			g.setColor(Color.orange);
		g.fill(jauge);
	}
	
	/**
	 * Ennemi viens de tirer, on crée une balle et la tire
	 * 
	 * @return
	 * @throws SlickException
	 */
	public Balle tirer() throws SlickException {
			Balle balle = new BalleEnnemi(getX(), getY(), getDirectionDroite(),
					0.01f, degatBalle);
			balle.applyForce(10000, 0);
			return balle;
		
	}
}
