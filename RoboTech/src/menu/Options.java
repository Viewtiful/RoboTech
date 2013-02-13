package menu;

import jeu.RoboTech;
import jeu.RoboTechJeu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.XMLPackedSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Etat des options du jeu
 * 
 * @author Equipe RoboTech
 * 
 */
public class Options extends BasicGameState {
	/**
	 * ID de l'état
	 */
	private int ID = -1;
	/**
	 * Image en background de l'état
	 */
	private Image imageFond;
	/**
	 * Contient les images de l'état
	 */
	private Image[] itemOption;
	/**
	 * Permet de savoir si la souris se trouve dans un bouton
	 */
	private boolean[] interieurBouton;
	/**
	 * Permet de positionner les images de l'état
	 */
	private int[][] positionItemOption;
	/**
	 * Position en x de la souris
	 */
	private int sourisX;
	/**
	 * Position en y de la souris
	 */
	private int sourisY;
	/**
	 * Sheet du robot rouge
	 */
	private XMLPackedSheet sheetRouge;
	/**
	 * Sheet du robot jaune
	 */
	private XMLPackedSheet sheetJaune;
	/**
	 * Sheet du robot vert
	 */
	private XMLPackedSheet sheetVert;

	/**
	 * Constructeur de Options
	 * 
	 * @param ID
	 *            transmis
	 */
	public Options(int ID) {
		this.ID = ID;
	}

	/**
	 * Initialisation les images ainsi que leurs positions
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		// initialise les images de l'état
		imageFond = new Image("res/backgroundOption.png");
		sheetRouge = new XMLPackedSheet("res/robotRouge.png", "res/robot.xml");
		sheetJaune = new XMLPackedSheet("res/robotJaune.png", "res/robot.xml");
		sheetVert = new XMLPackedSheet("res/robotVert.png", "res/robot.xml");

		itemOption = new Image[4];
		itemOption[0] = sheetRouge.getSprite("robot_01.png");
		itemOption[1] = sheetJaune.getSprite("robot_01.png");
		itemOption[2] = sheetVert.getSprite("robot_01.png");
		itemOption[3] = new Image("res/retourOption.png");

		// initialise le tableau pour savoir si la souris se trouve sur un
		// bouton à false
		interieurBouton = new boolean[itemOption.length];
		// premier dimension nombre item des options, deuxieme dimension
		// position x et y de cet item des options
		positionItemOption = new int[itemOption.length][2];

	}

	/**
	 * Gére l'affichage des images de l'état
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		// dessine l'image de background
		imageFond.draw(0, 0);

		// personnage bleu
		positionItemOption[0][0] = 280;
		positionItemOption[0][1] = 200;
		itemOption[0].draw(positionItemOption[0][0], positionItemOption[0][1]);

		// personnage vert
		positionItemOption[1][0] = 380;
		positionItemOption[1][1] = 200;
		itemOption[1].draw(positionItemOption[1][0], positionItemOption[1][1]);

		// personnage jaune
		positionItemOption[2][0] = 480;
		positionItemOption[2][1] = 200;
		itemOption[2].draw(positionItemOption[2][0], positionItemOption[2][1]);

		// retour menu principal
		positionItemOption[3][0] = 300;
		positionItemOption[3][1] = 280;
		itemOption[3].draw(positionItemOption[3][0], positionItemOption[3][1]);
	}

	/**
	 * Gére la mise à jour de l'état, gestion de la souris avec connaissance
	 * de sa position et action à faire si celle-ci se trouve sur un bouton
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		// gestion de la souris
		Input input = gc.getInput();

		sourisX = input.getMouseX();
		sourisY = input.getMouseY();

		// parcours les items des options, pour voir si on est dessus
		for (int i = 0; i < itemOption.length; i++) {
			interieurBouton[i] = false;

			if ((sourisX >= positionItemOption[i][0] && sourisX <= positionItemOption[i][0]
					+ itemOption[i].getWidth())
					&& (sourisY >= positionItemOption[i][1] && sourisY <= positionItemOption[i][1]
							+ itemOption[i].getHeight())) {
				itemOption[i].setAlpha(0.65f);
				interieurBouton[i] = true;
			} else {
				itemOption[i].setAlpha(1.f);
			}
		}

		// Choix personnage rouge
		if (interieurBouton[0] && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			this.choixPersonnage(sbg, "robotRouge");
		}

		// Choix personnage jaune
		if (interieurBouton[1] && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			this.choixPersonnage(sbg, "robotJaune");
		}

		// Choix personnage vert
		if (interieurBouton[2] && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			this.choixPersonnage(sbg, "robotVert");
		}

		// Retour menu principal
		if (interieurBouton[3] && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			sbg.enterState(RoboTech.MENUETAT);
		}

	}

	/**
	 * change la couleur du personnage puis retourne au menu principal du jeu
	 * 
	 * @param sbg
	 * @param string
	 *            Personnage a affiché
	 * @throws SlickException
	 */
	private void choixPersonnage(StateBasedGame sbg, String string)
			throws SlickException {
		RoboTechJeu.setImageRobot(string);
		sbg.enterState(RoboTech.MENUETAT);
	}

	/**
	 * Retourne l'ID de l'état
	 */
	@Override
	public int getID() {
		return ID;
	}

}
