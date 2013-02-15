package personnages;

import jeu.Monde;
import net.phys2d.raw.CollisionEvent;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.XMLPackedSheet;
import org.newdawn.slick.state.StateBasedGame;
import blocs.Point;

/**
 * Gere du kamikaze
 * @author Equipe RoboTech
 *
 */
public class Kamikaze extends Ennemis {

	/**
	 * Image de rendu
	 */
	private Image image;
	/**
	 * Extremite gauche du mouvement du Kamikaze
	 */
	private Point org;
	/**
	 * Extremite droite du mouvement du Kamikaze
	 */
	private Point fin;

	private float vitesse = (float) 0.50;
	
	/**
	 * Permet de compter les secondes
	 */
	private int i;
	/**
	 * Gestion de l'animation courante
	 */
	private int animationStep;
	/**
	 * Permet de savoir si le serpent a ete touche
	 */
	private XMLPackedSheet sheet;

	/**
	 * Constructeur du kamikaze
	 * @param x sa position en x
	 * @param y sa potision en y 
	 * @param masse sa masse dans le monde physique
	 * @param tailleBlockPerso sa taille dans le monde physique, notamment pour sa hitbox
	 * @param monde le monde dans lequel il evolue
	 * @param rayonDetection le rayon de detection autour de lui dans lequel il detecte le robot
	 * @throws SlickException
	 */
	public Kamikaze(float x, float y, float masse, float tailleBlockPerso,
			Monde monde, float rayonDetection) throws SlickException {
		super(x, y, masse, tailleBlockPerso, monde, rayonDetection, 0);
		org = new Point(x, y);
		fin = new Point(x + getRayonDetection(), y);
		
		// Initialisation de la vitesse negative
		vitesse = -vitesse;
		setDirectionDroite(false);
	}

	

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		sheet = new XMLPackedSheet("res/kamikaze.png", "res/kamikaze.xml");
		image = sheet.getSprite("kamikaze_00.png");
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		i++;
		//gestion des animations du kamikaze
		if (i >= 6) {
			animationStep++;
			animationStep %= 3;
			image = sheet.getSprite("kamikaze_0" + animationStep + ".png");
			if (getDirectionDroite()) {
				image = image.getFlippedCopy(true, false);
			}

			i = 0;
		}

		// dessine l'image de l'ennemi en le centrant
		image.drawCentered(getX(), getY());
		renderVie(g);

	}

	/**
	 * Gestion des degats subient lorsqu'il est touche
	 */
	@Override
	public void toucher(int value) {
		setVie(0);

	}

	public void deplacement() {
		// Si on arrive aux extrémité de la trajectoire
		if (Math.abs(getX() - fin.getX()) < (float) 1.e-01
				|| Math.abs(getX() - org.getX()) < (float) 1.e-01) {
			vitesse = -vitesse;
			// on change la direction du déplacement du Kamikaze
			setDirectionDroite(!getDirectionDroite());
		}

		setX(getX() + vitesse);
	}

	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {

		


		float xa = getPlayer().getX();
		float ya = getPlayer().getY();
		float xb = getX();
		float yb = getY();
		if(pythagore(xa, ya, xb, yb) < getRayonDetection() && !getDetected())
		{
			// Le joueur est detecté
			setDetected(true);
			sheet = new XMLPackedSheet("res/kamikaze_bombe.png",
					"res/kamikaze.xml");
		}
		if (getDetected()) {

			if (xa < getX()) {
				// l'ennemi est à gauche
				setX(getX() - 1);
				setDirectionDroite(false);
			} else if (xa > getX()) {
				// l'ennemmis est à droite
				setX(getX() + 1);
				setDirectionDroite(true);
			}
			// je recherche si il y a une collision avec le robot
			CollisionEvent t[] = monde.getWorld().getContacts(getBody());
			for (int i = 0; i < t.length; i++)
				if (t[i].getBodyA() == getPlayer().getBody()
						|| t[i].getBodyB() == getPlayer().getBody()) {
						//Collision avec le robot et mort
					getPlayer().setVie(0);
					setVie(0);
				}
		} else if (!getDetected())
			deplacement();

	}
}
