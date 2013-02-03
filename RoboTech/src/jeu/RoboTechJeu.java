package jeu;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import personnages.Robot;

public class RoboTechJeu extends BasicGameState {
	/** The unique ID given to the state */
	private int ID = -1;

	// liste des items ramassable
	// private ArrayList<Items> itemsRamassable;
	// private ArrayList<Personnage> personnages;
	// axe des x pour la camera
	private float cameraX;
	// axe des y pour la camera
	private float cameraY;

	// le niveau
	private Monde monde;

	private static Robot player;

	public int getID() {
		return ID;
	}

	public RoboTechJeu(int ID) {
		this.ID = ID;
	}

	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// demarre le niveau, en appuyant sur la touche 'R', possibilite de
		// redemarrer le niveau
		restart(game);
		monde.init(container, game);
	}

	public static void setImageRobot(String robot) throws SlickException {
		player.setImage(robot);
	}

	/**
	 * Fonction qui permet de redemarrer le niveau
	 * 
	 * @throws SlickException
	 */
	private void restart(StateBasedGame game) throws SlickException {
		monde = new Monde();
		// monde.setPlayer(player);
		monde.initialisationMonde();
		player = monde.getPlayer();
		// monde.addPersonnages(player);
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {

		g.translate(-(int) cameraX, -(int) cameraY); // gere le rendu de la
		// camera
		monde.render(container, game, g); // gere le rendu du monde complet
	}

	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		Input input = container.getInput();

		// redemarrer le jeu
		if (input.isKeyPressed(Input.KEY_R)) {
			init(container, game);
			return;
		}

		// met a jour le monde
		monde.update(container, game, delta);

		if (player.getVie() <= 0) {
			// System.out.println("Fin du jeu");

			game.enterState(RoboTech.MORTETAT);
			init(container, game);
		}
		// calcule la zone affichee par la camera
		cameraX = player.getX() - 400;
		cameraY = player.getY() - 300;
	}

}
