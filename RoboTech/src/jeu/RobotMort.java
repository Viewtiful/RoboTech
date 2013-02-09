package jeu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Etat dans lequel rentre le joueur lorsque celui-ci meurt
 * 
 * @author Equipe RoboTech
 * 
 */
public class RobotMort extends BasicGameState {
	/**
	 * ID de l'état
	 */
	private int ID = -1;
	/**
	 * Contient les images de l'état
	 */
	private Image[] itemMort;
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
	 * Constructeur de RobotMort
	 * 
	 * @param ID
	 *            transmis
	 */
	public RobotMort(int ID) {
		this.ID = ID;
	}

	/**
	 * Retourne l'ID de l'état
	 */
	@Override
	public int getID() {
		return ID;
	}

	/**
	 * Initialisation les images ainsi que leurs positions
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		// initialise les images de l'état
		itemMort = new Image[1];
		itemMort[0] = new Image("res/retourOption.png");

		// initialise le tableau pour savoir si la souris se trouve sur un
		// bouton à false
		interieurBouton = new boolean[itemMort.length];

		// initialise le tableau des positions des images
		positionItemOption = new int[itemMort.length][2];
	}

	/**
	 * Gére l'affichage des images de l'état
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		// position les images et les affiche
		positionItemOption[0][0] = 300;
		positionItemOption[0][1] = 280;
		itemMort[0].draw(positionItemOption[0][0], positionItemOption[0][1]);
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
		for (int i = 0; i < itemMort.length; i++) {
			interieurBouton[i] = false;

			if ((sourisX >= positionItemOption[i][0] && sourisX <= positionItemOption[i][0]
					+ itemMort[i].getWidth())
					&& (sourisY >= positionItemOption[i][1] && sourisY <= positionItemOption[i][1]
							+ itemMort[i].getHeight())) {
				itemMort[i].setAlpha(0.65f);
				interieurBouton[i] = true;
			} else
				itemMort[i].setAlpha(1.f);
		}

		// Retour menu principal
		if (interieurBouton[0] && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			sbg.enterState(RoboTech.MENUETAT);
		}
	}
}
