package jeu;

import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import barres.Barre;
import barres.BarreEnergie;
import barres.BarreMana;
import barres.BarrePotion;
import barres.BarreVie;

import personnages.Robot;

public class RoboTechJeu extends BasicGameState {
	/** The unique ID given to the state */
	private int ID = -1;

	private boolean show = true;
	private ArrayList<Barre> barres;

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

	private boolean paused = false;

	private float decalage = 10;

	public int getID() {
		return ID;
	}

	public RoboTechJeu(int ID) {
		this.ID = ID;
		barres = new ArrayList<Barre>();
		float width = 64;
		float height = 10;
		barres.add(new BarreVie(cameraX, cameraY, width, height, "Vie"));
		barres.add(new BarreMana(cameraX, cameraY + decalage, width, height,
				"Mana"));
		barres.add(new BarreEnergie(cameraX, cameraY + 2 * decalage, width,
				height, "Energie"));

		barres.add(new BarrePotion(cameraX, cameraY + 3 * decalage, width,
				height, "Potion"));
	}

	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// demarre le niveau, en appuyant sur la touche 'R', possibilite de
		// redemarrer le niveau
		restart(game);
		monde.init(container, game);
		container.setShowFPS(show);
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
		monde.initialisationMonde();
		player = monde.getPlayer();
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {

		g.translate(-(int) cameraX, -(int) cameraY); // gere le rendu de la
		// camera
		if (!paused) {
			monde.render(container, game, g); // gere le rendu du monde complet

		}
		InfoRobot(g);
		
	}

	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		Input input = container.getInput();

		// redemarrer le jeu
		if (input.isKeyPressed(Input.KEY_R)) {
			init(container, game);
			return;
		}

		if (input.isKeyPressed(Input.KEY_P))
			paused = !paused;

		if(input.isKeyPressed(Input.KEY_F))
		{
			show = !show;
			container.setShowFPS(show);
		}
		// met a jour le monde
		if (!paused)
			monde.update(container, game, delta);

		if (player.getVie() <= 0) {
			game.enterState(RoboTech.MORTETAT);
			init(container, game);
		}
		// calcule la zone affichee par la camera
		cameraX = player.getX() - 400;
		cameraY = player.getY() - 300;
	}

	public void InfoRobot(Graphics g) {
		for (int i = 0; i < barres.size(); i++) {
			barres.get(i).update(cameraX,
					cameraY + i * (decalage + barres.get(i).getHeight()));
			barres.get(i).render(g, player);
		}

	}
}
