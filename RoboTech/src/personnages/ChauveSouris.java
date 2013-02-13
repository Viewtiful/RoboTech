package personnages;

import jeu.Monde;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.XMLPackedSheet;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Gere les chauve souris
 * @author Equipe RoboTech
 *
 */
public class ChauveSouris extends Ennemis {
	/**
	 * Image courant de l'animation de la chauve souris
	 */
	private Image image;
	/**
	 * Sprite contenant les images de l'animation de la chauve souris
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
	 * Constructeur de chauve souris
	 * @param x sa position en x
	 * @param y sa potision en y 
	 * @param masse sa masse dans le monde physique
	 * @param tailleBlockPerso sa taille dans le monde physique, notamment pour sa hitbox
	 * @param monde le monde dans lequel il evolue
	 * @param rayonDetection le rayon de detection autour de lui dans lequel il detecte le robot
	 * @throws SlickException
	 */
	public ChauveSouris(float x, float y, float masse, float tailleBlockPerso,
			Monde monde,float rayonDetection) throws SlickException {
		super(x, y, masse, tailleBlockPerso, monde,rayonDetection,1);
		sheet = new XMLPackedSheet("res/bat.png", "res/bat.xml");
		setMax(2);
		setVie(2);
	}

	/**
	 * Initialisation du serpent, affichage son image de base
	 */
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		image = sheet.getSprite("bat_00.png");
	}

	/**
	 * Gestion de l'affichage de la chauve souris
	 */
	public void render(Graphics g) {
		i++;
		//gestion des animations du serpent
		if (i >= 6) {
			animationStep++;
			animationStep %= 3;
			image = sheet.getSprite("bat_0" + animationStep + ".png");
			if (getDirectionDroite()) {
				image = image.getFlippedCopy(true, false);
			}

			i = 0;
		}
		
		//s'il a ete touché, on le rend transparent, pour le faire clignoter
		if (toucherDmg) {
			image.setAlpha(0.001f);
			if (i >= 5) {
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
