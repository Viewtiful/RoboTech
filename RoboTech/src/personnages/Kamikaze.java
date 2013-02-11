package personnages;

import jeu.Monde;

import net.phys2d.raw.CollisionEvent;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.XMLPackedSheet;
import org.newdawn.slick.state.StateBasedGame;

import blocs.Point;

public class Kamikaze extends Ennemis {

	/**
	 * Image de rendu
	 */
	private Image image;
	/**
	 * Extrémité gauche du mouvement du Kamikaze
	 */
	private Point org;
	/**
	 * Extrémité droite du mouvement du Kamikaze
	 */
	private Point fin;

	private float vitesse = (float) 0.50;
	private int i;
	private int animationStep;
	private XMLPackedSheet sheet;

	/**
	 * 
	 * @param x
	 *            Position Horizontale sur la Map
	 * @param y
	 *            Position Verticale sur la Map
	 * @param masse
	 *            Masse du Kamikaze
	 * @param tailleBlockPerso
	 *            Taille du Kamikaze
	 * @param monde
	 *            Environnement ou évolue le monde
	 * @param rayon_detection
	 *            Distance de détection
	 * @throws SlickException
	 */
	public Kamikaze(float x, float y, float masse, float tailleBlockPerso,
			Monde monde, float rayonDetection) throws SlickException {
		super(x, y, masse, tailleBlockPerso, monde, rayonDetection, 0);
		sheet = new XMLPackedSheet("res/kamikaze.png", "res/kamikaze.xml");
		image = sheet.getSprite("kamikaze_00.png");
		org = new Point(x, y);
		fin = new Point(x + getRayonDetection(), y);
		// Initialisation de la vitesse nï¿½gative
		vitesse = -vitesse;
		setDirectionDroite(false);
	}

	@Override
	public void render(Graphics g) {

		i++;
		if (i >= 6) {
			animationStep++;
			animationStep %= 3;
			image = sheet.getSprite("kamikaze_0" + animationStep + ".png");
			if (getDirectionDroite()) {
				image = image.getFlippedCopy(true, false);
			}

			i = 0;
		}

		image.drawCentered(getX(), getY());
		renderVie(g);

	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		render(g);

	}

	@Override
	public void toucher(int value) {
		setVie(0);

	}

	public void deplacement() {
		if (Math.abs(getX() - fin.getX()) < (float) 1.e-01
				|| Math.abs(getX() - org.getX()) < (float) 1.e-01) {
			vitesse = -vitesse;
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
		if(	pythagore(xa, ya, xb, yb) < getRayonDetection() && !getDetected())
		{
			setDetected(true);
			sheet = new XMLPackedSheet("res/kamikaze_bombe.png",
					"res/kamikaze.xml");
		}
		if (getDetected()) {

			if (xa < getX()) {
				setX(getX() - 1);
				setDirectionDroite(false);
			} else if (xa > getX()) {
				setX(getX() + 1);
				setDirectionDroite(true);
			}
			CollisionEvent t[] = monde.getWorld().getContacts(getBody());
			for (int i = 0; i < t.length; i++)
				if (t[i].getBodyA() == getPlayer().getBody()
						|| t[i].getBodyB() == getPlayer().getBody()) {
					getPlayer().setVie(0);
					setVie(0);
				}
		} else if (!getDetected())
			deplacement();

	}
}
