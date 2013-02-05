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
	 * Le robot à suivre
	 */
	private Robot player;
	/**
	 * Rayon de detection
	 */
	private float rayon_detection;
	/**
	 * Image de rendu
	 */
	private Image image;
	/**
	 * Robot détecté
	 */
	private boolean detected = false;
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
			Monde monde, float rayon_detection) throws SlickException {
		super(x, y, masse, tailleBlockPerso, monde);
		player = monde.getPlayer();
		sheet = new XMLPackedSheet("res/kamikaze.png", "res/kamikaze.xml");
		this.rayon_detection = rayon_detection;
		image = sheet.getSprite("kamikaze_00.png");
		org = new Point(x, y);
		fin = new Point(x + rayon_detection, y);
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
		g.setColor(Color.gray);
		g.drawLine(org.get_x(), org.get_y(), fin.get_x(), fin.get_y());

	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub

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
		if (Math.abs(getX() - fin.get_x()) < (float) 1.e-01
				|| Math.abs(getX() - org.get_x()) < (float) 1.e-01) {
			vitesse = -vitesse;
			setDirectionDroite(!getDirectionDroite());
		}

		setX(getX() + vitesse);
	}

	float Phytagore(float xa, float ya, float xb, float yb) {
		float diff1 = xb - xa;
		float diff2 = yb - ya;
		diff1 = diff1 * diff1;
		diff2 = diff2 * diff2;
		return (float) Math.sqrt(diff1 + diff2);
	}

	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {

		float xa = player.getX();
		float ya = player.getY();
		float xb = getX();
		float yb = getY();
		if (Phytagore(xa, ya, xb, yb) < rayon_detection && !detected) {
			detected = true;
			sheet = new XMLPackedSheet("res/kamikaze_bombe.png",
					"res/kamikaze.xml");
		}

		if (detected) {
			if (xa < getX()) {
				setX(getX() - 1);
				setDirectionDroite(false);
			} else if (xa > getX()) {
				setX(getX() + 1);
				setDirectionDroite(true);
			}
			CollisionEvent t[] = monde.getWorld().getContacts(getBody());
			for (int i = 0; i < t.length; i++)
				if (t[i].getBodyA() == player.getBody()
						|| t[i].getBodyB() == player.getBody()) {
					player.setVie(0);
					setVie(0);
				}
		}

		else if (!detected)
			deplacement();
	}

}
