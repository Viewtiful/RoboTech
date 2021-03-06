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

/**
 * Classe gérant le robot
 * 
 * @author Equipe RoboTech
 * 
 */
public class Robot extends Personnage {
	/**
	 * l'image qui contient le sprite du robot
	 */
	private Image imageCouranteRobot;
	/**
	 * Gestion touche robot
	 */
	RobotHandlers handlers;
	/**
	 * Sheet contenant l'animation du robot
	 */
	private XMLPackedSheet sheet;
	/**
	 * Gestion de l'enchainement des animations du robot
	 */
	private int EtapeAnimation;
	/**
	 * Sert de compteur ( compte les secondes)
	 */
	private int i;
	/**
	 * Toutes les 1s, le robot perd un point d'énergie
	 */
	private int gestionPerteEnergie;
	/**
	 * Lorsque le robot n'a plus d'énergie, il regagne 1 point d'énergie
	 * toutes les secondes
	 */
	private int gestionGainEnergie;
	/**
	 * Gére le cas ou le robot n'a plus d'énergie
	 */
	private boolean plusEnergie = false;
	/**
	 * Représente le point d'énergie perdu toutes les 10sec
	 */
	private int pertePointEnergie;
	/**
	 * Représente le point d'énergie gagné toutes les 3sec
	 */
	private int gainPointEnergie;
	/**
	 * Représente les secondes écoulés pour la perte de l'effet de la potion
	 * de saut ou vitesse
	 */
	private int gestionPotionVitesseSaut;
	/**
	 * Au bout de 10secondes, l'effet de la potion s'estompe
	 */
	private int estomperEffetPotion;
	/**
	 * Consomation d'une Potion de Vitesse et ou de saut
	 */
	private boolean potionVitesseSaut = false;

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
		handlers = new RobotHandlers();
	}

	/**
	 * Permet de choisir le skin du robot, notamment via le menu des options
	 * 
	 * @param robotCouleur chaine de caractere representant la nouvelle image du robot
	 * @throws SlickException
	 */
	public void setImage(String robotCouleur) throws SlickException {
		assert(!robotCouleur.equals(""));
		
		sheet = new XMLPackedSheet("res/" + robotCouleur + ".png",
				"res/robot.xml");
	}

	/**
	 * Permet de recuperer l'image courante du robot utilisee
	 * 
	 * @return
	 */
	public Image getImage() {
		return imageCouranteRobot;
	}

	/**
	 * met le robot dans un monde
	 * 
	 * @param monde
	 */
	public void setMonde(Monde monde) {
		assert(monde != null);
		this.monde = monde;
	}

	/**
	 * Ajoute de l'énergie au robot
	 * 
	 * @param value
	 *            valeur ajoutée à l'énergie du robot
	 */
	public void ajouterEnergie(int value) {
		assert(value >= 0);
		setEnergie(getEnergie() + value);

	}

	/**
	 * Ajoute du mana au robot
	 * 
	 * @param value
	 *            valeur ajoutée au mana du robot
	 */
	public void ajouterMana(int value) {
		assert(value >= 0);
		setMana(getMana() + value);

	}

	/**
	 * Ajoute de la vie au robot
	 * 
	 * @param value
	 *            valeur ajoutée à la vie du robot
	 */
	public void ajouterVie(int value) {
		assert(value >= 0);
		setVie(getVie() + value);
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		sheet = new XMLPackedSheet("res/robotRouge.png", "res/robot.xml");
	}

	/**
	 * Gestion du rendu, animation etc...
	 */
	public void render(Graphics g) {
		i++;
		gestionPerteEnergie++;
		gestionGainEnergie++;
		gestionPotionVitesseSaut++;

		// gestion des animations du robot
		if (getEnMouvement() && auSol() && i >= 4) {
			EtapeAnimation++;
			// modulo le nombre d'image de l'animation du robot
			EtapeAnimation %= 15;

			// initialise a la premiere image du robot, sinon en fonction de
			// animationStep, charge l'image correspondante
			if (EtapeAnimation == 0) {
				imageCouranteRobot = sheet.getSprite("robot_01.png");
			} else {
				imageCouranteRobot = sheet.getSprite("robot_0" + EtapeAnimation
						+ ".png");
			}
			// si le robot se déplace vers la gauche, on "retourne" l'image
			if (!getDirectionDroite()) {
				imageCouranteRobot = imageCouranteRobot.getFlippedCopy(true,
						false);
			}
			i = 0;
		} 
		else {
			// si le robot n'est pas en mouvement, on laisse l'image par défaut
			// du robot
			if (!getEnMouvement()) {
				imageCouranteRobot = sheet.getSprite("robot_00.png");
				// si le robot se déplace vers la gauche, on "retourne" l'image
				if (!getDirectionDroite())
					imageCouranteRobot = imageCouranteRobot.getFlippedCopy(
							true, false);
			}
		}

		// Si, une seconde viens de s'écouler et que le robot a encore de
		// l'energie, on peut le deplacer
		if (gestionPerteEnergie >= 60) {
			// toutes les 10s perds un point d'énergie
			pertePointEnergie++;
			// au bout de 10sec, si le robot a encore de l'énergie, perd 1 point
			if (getEnergie() > 0 && !plusEnergie) {
				if (pertePointEnergie >= 10) {
					setEnergie(getEnergie() - 1);
					pertePointEnergie = 0;
				}

				// sinon, c'est que le robot n'a plus d'énergie
			} else {
				plusEnergie = true;
			}
			gestionPerteEnergie = 0;
		}

		// Si, une seconde viens de s'écouler et que le robot n'a plus
		// d'énergie, il regagne un point d'énergie toutes les ~2secs,
		// l'énergie remonte à 5, le robot peut de nouveau bouger
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

		// Si, une seconde viens de s'écouler, on enleve 1sec sur l'effet de la
		// potion de vitesse ou saut
		if(potionVitesseSaut)
		{
			if (gestionPotionVitesseSaut >= 60) {
				estomperEffetPotion++;
				// si on arrive au 10sec, on remet la vitesse et le saut du robot
				// par défaut
				if (estomperEffetPotion >= 10) {
					modifierVitesseX(1);
					modifierVitesseY(1);
					estomperEffetPotion = 0;
					potionVitesseSaut = !potionVitesseSaut;
				}
				gestionPotionVitesseSaut = 0;
			}
		}
		// dessine l'image du robot en le centrant
		imageCouranteRobot.drawCentered(getX(), getY());

	}

	public void setPotionVitesse(boolean potionVitesse) {
		this.potionVitesseSaut = potionVitesse;
	}

	public boolean getPotionVitesse() {
		return potionVitesseSaut;
	}
	public int getEstomperEffetPotion() {
		return estomperEffetPotion;
	}

	public void setPlusEnergie(boolean plusEnergie) {
		this.plusEnergie = plusEnergie;
	}

	public void setEstomperEffetPotion(int valeur) {
		estomperEffetPotion = valeur;
	}

	public boolean getPlusEnergie() {
		return plusEnergie;
	}

	/**
	 * Gere l'affichage du robot
	 */
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		render(g);
	}

	/**
	 * Gere la mise a jour du robot
	 */
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		Input input = container.getInput();
		super.update(container, game, delta);
		handlers.handle(input, this);

	}

	/**
	 * Si le robot est touché, il perd autant de point de vie qu'il a subit
	 */
	@Override
	public void toucher(int value) {
		assert(value >= 0);
		setVie(getVie() - value);

	}

	/**
	 * Modification de sa vitesse lorsqu'il a pris notamment une potion de vitesse
	 * @param valeurModVitesseX
	 */
	public void modifierVitesseX(int valeurModVitesseX) {
		setVitesseX(valeurModVitesseX);

	}

	/**
	 * Modification de hauteur de saut en y, notamment lorsqu'il a pris une potion de saut
	 * @param valeurModVitesseY
	 */
	public void modifierVitesseY(int valeurModVitesseY) {
		setVitesseY(valeurModVitesseY);

	}

	/**
	 * Robot viens de tirer, on crée une balle et la tire
	 * 
	 * @return
	 * @throws SlickException
	 */
	public Balle tirer() throws SlickException {
		if (getMana() > 0) {
			setMana(getMana() - 1);
			//cree une nouvelle balle, qui part dans la direction dans laquelle le robot pointe
			Balle balle = new BalleRobot(getX(), getY(), getDirectionDroite(),
					0.01f, 1);
			balle.applyForce(10000, 0);
			return balle;
		}
		return null;
	}

}
