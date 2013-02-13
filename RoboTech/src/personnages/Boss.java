package personnages;

import jeu.Monde;
import jeu.RoboTech;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.XMLPackedSheet;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Gere le boss
 * @author Equipe RoboTech
 *
 */
public class Boss extends Ennemis {
	/**
	 * Image courant de l'animation du boss
	 */
	private Image image;
	/**
	 * Sprite contenant les images de l'animation du boss
	 */
	private XMLPackedSheet sheet;
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
	private boolean toucherDmg;
	
	/**
	 * Constructeur du boss
	 * @param x sa position en x
	 * @param y sa potision en y 
	 * @param masse sa masse dans le monde physique
	 * @param tailleBlockPerso sa taille dans le monde physique, notamment pour sa hitbox
	 * @param monde le monde dans lequel il evolue
	 * @param rayonDetection le rayon de detection autour de lui dans lequel il detecte le robot
	 * @throws SlickException
	 */
	public Boss(float x, float y, float masse, float tailleBlockPerso,
			Monde monde,float rayonDetection) throws SlickException {
		super(x, y, masse, tailleBlockPerso, monde,rayonDetection,3);

		sheet = new XMLPackedSheet("res/Boss.png", "res/boss.xml");
		setMax(5);
		setVie(5);
	}

	/**
	 * Initialisation du boss, affichage son image de base
	 */
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		image = sheet.getSprite("boss_00.png");
	}

	/**
	 * Gestion de l'affichage du boss
	 */
	public void render(Graphics g) {
		i++;
		//gestion des animations du boss
		if (i >= 15) {
			animationStep++;
			animationStep %= 3;
			image = sheet.getSprite("boss_0" + animationStep + ".png");
			if (getDirectionDroite()) {
				image = image.getFlippedCopy(true, false);
			}
			i = 0;
		}

		//s'il a ete touché, on le rend transparent, pour le faire clignoter
		if (toucherDmg) {
			image.setAlpha(0.001f);
			if (i >= 8) {
				image.setAlpha(1.f);
				toucherDmg = false;
			}
		}

		// dessine l'image de l'ennemi en le centrant
		image.drawCentered(getX(), getY());
		renderVie(g);

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {

		render(g);

		if (getVie() == 0) {
			game.enterState(RoboTech.NIVSUIVANTETAT);
		}
	}

	/**
	 * Gestion des degats subient lorsqu'il est touche
	 */
	@Override
	public void toucher(int value) {
		toucherDmg = true;
		setVie(getVie() - value);
	}

}
