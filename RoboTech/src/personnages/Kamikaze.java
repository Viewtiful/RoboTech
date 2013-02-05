package personnages;

import jeu.Monde;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.XMLPackedSheet;
import org.newdawn.slick.state.StateBasedGame;

import blocs.Point;

public class Kamikaze extends Ennemis {

	private Robot player;
	private float rayon_detection;
	private Image image;
	private boolean detected = false;
	private int deplacement = 0;
	private Point org;
	private Point fin;

	private float vitesse = (float) 0.50;
	private int i;
	private int animationStep;
	private XMLPackedSheet sheet;

	public Kamikaze(float x, float y, float masse, float tailleBlockPerso,
			Monde monde, float rayon_detection) throws SlickException {
		super(x, y, masse, tailleBlockPerso, monde);
		player = monde.getPlayer();
		sheet = new XMLPackedSheet("res/kamikaze.png", "res/kamikaze.xml");
		this.rayon_detection = rayon_detection;
		image = sheet.getSprite("kamikaze_00.png");
		org = new Point(x, y);
		fin = new Point(x + rayon_detection, y);
		// Initialisation de la vitesse n�gative
		vitesse = -vitesse;
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
		if (Math.abs(getX() - fin.get_x()) < (float) 1.e-01)
			vitesse = -vitesse;
		if (Math.abs(getX() - org.get_x()) < (float) 1.e-01)
			vitesse = -vitesse;
		setX(getX() + vitesse);
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		float x1_rayon = getX() + rayon_detection;
		float x2_rayon = getX() - rayon_detection;
		float x_player = player.getX();
		if (x_player <= x1_rayon && x_player >= x2_rayon && !detected) {
			detected = true;
			sheet = new XMLPackedSheet("res/kamikaze_bombe.png", "res/kamikaze.xml");
			if (x_player <= getX())
				deplacement = -1;
			else
				deplacement = 1;
		}
		if (detected)
			setX(getX() + deplacement);

		else if (!detected)
			deplacement();
		// if(Math.abs(getX()-player.getX())<(float)(player.get_taille()+get_taille())/2)
		// player.setVie(0);
		//
	}

}
