package jeu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Etat du menu principal du jeu
 * @author Equipe RoboTech
 *
 */
public class Menu extends BasicGameState {
	/**
	 * ID de l'état
	 */
	private int ID = -1;
	/**
	 * Contient les images de l'état
	 */
	private Image itemMenu[];
	/**
	 * Image en background de l'état
	 */
	private Image imageFond;
	/**
	 * Position en x et y de la souris
	 */
	private int sourisX, sourisY;
	/**
	 * Permet de savoir si la souris se trouve dans un bouton
	 */
	private boolean interieurBouton[];
	/**
	 * Permet de positionner les images de l'état
	 */
	private int positionItemMenu[][];

	/**
	 * Constructeur de Menu
	 * @param ID transmis
	 */
	public Menu(int ID) {
		this.ID = ID;
	}

	
	/**
	 * Initialisation les images ainsi que leurs positions
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		//initialise les images de l'état
		imageFond = new Image("res/backgroundMenu.png");
		itemMenu = new Image[3];
		itemMenu[0] = new Image("res/jeuMenu.png");
		itemMenu[1] = new Image("res/optionMenu.png");
		itemMenu[2] = new Image("res/quitterMenu.png");

		//initialise le tableau pour savoir si la souris se trouve sur un bouton à false
		interieurBouton = new boolean[itemMenu.length];
		// premier dimension nombre item du menu, deuxieme dimension position x
		// et y de cet item du menu
		positionItemMenu = new int[itemMenu.length][2];
	}

	/**
	 * Gére l'affichage des images de l'état
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		// image de fond du menu
		imageFond.draw(0, 0);

		// item pour demarrer le jeu
		positionItemMenu[0][0] = 50;
		positionItemMenu[0][1] = 220;
		itemMenu[0].draw(positionItemMenu[0][0], positionItemMenu[0][1]);

		// item pour acceder au option
		positionItemMenu[1][0] = 70;
		positionItemMenu[1][1] = 290;
		itemMenu[1].draw(positionItemMenu[1][0], positionItemMenu[1][1]);

		// item pour quitter le jeu
		positionItemMenu[2][0] = 75;
		positionItemMenu[2][1] = 360;
		itemMenu[2].draw(positionItemMenu[2][0], positionItemMenu[2][1]);
	}

	/**
	 * Gére la mise à jour de l'état, gestion de la souris avec connaissance de sa position et action à faire si celle-ci se trouve
	 * sur un bouton
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		//gestion de la souris
		Input input = gc.getInput();

		sourisX = input.getMouseX();
		sourisY = input.getMouseY();

		// parcours les items du menu, pour voir si on est dessus
		for (int i = 0; i < itemMenu.length; i++) {
			interieurBouton[i] = false;

			if ((sourisX >= positionItemMenu[i][0] && sourisX <= positionItemMenu[i][0]
					+ itemMenu[i].getWidth())
					&& (sourisY >= positionItemMenu[i][1] && sourisY <= positionItemMenu[i][1]
							+ itemMenu[i].getHeight())) {
				itemMenu[i].setAlpha(0.65f);
				interieurBouton[i] = true;
			} else
				itemMenu[i].setAlpha(1.f);
		}

		// Lancer le jeu
		if (interieurBouton[0] && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			this.lancerJeu(sbg);
		}

		// Lancer Option
		if (interieurBouton[1] && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			this.lancerOption(sbg);
		}

		// Quitter le jeu
		if (interieurBouton[2] && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			gc.exit();
		}

	}

	/**
	 * lance le jeu
	 * @param sbg
	 */
	public void lancerJeu(StateBasedGame sbg) {
		sbg.enterState(RoboTech.JEUETAT);
	}

	/**
	 * lance le menu des options
	 * @param sbg
	 */
	public void lancerOption(StateBasedGame sbg) {
		sbg.enterState(RoboTech.OPTIONETAT);
	}

	/**
	 * Retourne l'ID de l'état
	 */
	@Override
	public int getID() {
		return ID;
	}

}
