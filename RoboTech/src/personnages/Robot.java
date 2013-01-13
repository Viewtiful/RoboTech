package personnages;

import jeu.Monde;

import listener.RobotHandlers;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.XMLPackedSheet;
import org.newdawn.slick.state.StateBasedGame;

import weapon.Balle;
import weapon.BalleRobot;

public class Robot extends Personnage {
	// l'image qui contient le sprite du robot
	private Image image;
	RobotHandlers handlers;
	private XMLPackedSheet sheet;
	private int animationStep;
	private int i;
	private int gestionPerteEnergie;
	private int z;
	private int gestionGainEnergie;
	private boolean plusEnergie = false;
	private int pertePointEnergie;
	private int gainPointEnergie;
	private int gestionPotionVitesseSaut;
	private int estomperEffetPotion;

	/**
	 * Create a new Alien actor read to add to the world
	 * 
	 * @param x
	 *            The x position of the alien
	 * @param y
	 *            The y position of the alien
	 * @param mass
	 *            The mass of the alien
	 * @param size
	 *            The size of the alien (collision size)
	 * @throws SlickException
	 *             Indicates a failure to load resources for this alien
	 **/
	public Robot(float x, float y, float mass, float size, Monde monde)
			throws SlickException {
		super(x, y, mass, size, monde);

		image = new Image("res/robotBleu.png");
		handlers = new RobotHandlers();
		sheet = new XMLPackedSheet("res/robot.png", "res/robot.xml");
	}

	public void setImage(String robot) throws SlickException {
		image = new Image("res/" + robot + ".png");
	}

	public Image getImage() {
		return image;
	}

	public void set_monde(Monde monde) {
		this.monde = monde;
	}

	public void ajouterEnergie(int value) {
		setEnergie(getEnergie() + value);

	}

	public void ajouterMana(int value) {
		setMana(getMana() + value);

	}

	public void ajouterVie(int value) {
		setVie(getVie() + value);
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {

	}

	public void render(Graphics g) {
		i++;
		gestionPerteEnergie++;
		gestionGainEnergie++;
		gestionPotionVitesseSaut++;
		if (getEnMouvement() && auSol() && i >= 4) {
			animationStep++;
			animationStep %= 15;
			if (animationStep == 0) {
				image = sheet.getSprite("robot_01.png");
			} else {
				image = sheet.getSprite("robot_0" + animationStep + ".png");
			}
			if (!getDirectionDroite()) {
				image = image.getFlippedCopy(true, false);
			}

			i = 0;
		} else {
			if (!getEnMouvement()) {
				image = sheet.getSprite("robot_00.png");
				if (!getDirectionDroite())
					image = image.getFlippedCopy(true, false);
			}
		}

		// Si, une seconde viens de s'écouler et que le robot a encore de
		// l'energie, on peut le deplacer
		if (gestionPerteEnergie >= 60) {
			// toutes les 10s perds un point d'énergie
			pertePointEnergie++;
			if (getEnergie() > 0 && !plusEnergie) {
				if (pertePointEnergie >= 10) {
					setEnergie(getEnergie() - 1);
					pertePointEnergie = 0;
				}

			} else {
				plusEnergie = true;
			}
			gestionPerteEnergie = 0;
		}

		// Si, une seconde viens de s'écouler
		if (gestionGainEnergie >= 60) {
			gainPointEnergie++;
			if (plusEnergie) {
				if (gainPointEnergie >= 2) {
					setEnergie(getEnergie() + 1);
					gainPointEnergie = 0;
					if (getEnergie() == 5) {
						plusEnergie = false;
					}
				}
			}
			gestionGainEnergie = 0;
		}

		// Si, une seconde viens de s'écouler
		if (gestionPotionVitesseSaut >= 60) {
			estomperEffetPotion++;
			if (estomperEffetPotion >= 10) {
				System.out.println("lol");
				modifierVitesseX(1);
				modifierVitesseY(1);
				estomperEffetPotion = 0;
			}
			gestionPotionVitesseSaut = 0;
		}

		// dessine l'image du robot en le centrant
		image.drawCentered(getX(), getY());

		// texte de debug
		g.drawString("sol?" + auSol() + "facingRight?" + getDirectionDroite()
				+ " " + getX() + "," + getY() + " " + velx, getX(), getY() - 50);
		g.drawString("Vie : " + getVie() + ", Mana : " + getMana()
				+ ", Energie : " + getEnergie(), getX(), getY() - 70);

	}

	public void setEstomperEffetPotion(int valeur) {
		estomperEffetPotion = valeur;
	}

	public boolean getPlusEnergie() {
		return plusEnergie;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		render(g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		Input input = container.getInput();
		super.update(container, game, delta);
		handlers.handle(input, this);

	}

	@Override
	public void toucher(int value) {
		setVie(getVie() - value);

	}

	public void modifierVitesseX(int valeurModVitesseX) {
		setVitesseX(valeurModVitesseX);

	}

	public void modifierVitesseY(int valeurModVitesseY) {
		setVitesseY(valeurModVitesseY);

	}

	public Balle tirer() throws SlickException {
		if (getMana() > 0) {
			setMana(getMana() - 1);
			Balle balle = new BalleRobot(getX(), getY(), getDirectionDroite(),
					0.01f, 1);
			balle.applyForce(10000, 0);
			return balle;
		}
		return null;
	}

}
