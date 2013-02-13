package jeu;

import gui.Barre;
import gui.BarreEnergie;
import gui.BarreMana;
import gui.BarrePotion;
import gui.BarreVie;

import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.Color;
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

	/**
	 * Affiche/cache fps
	 */
	private boolean affichageFPS = false;
	
	/**
	 * Les barres de vie/mana/energie/temps restant potion vitesse/saut
	 */
	private ArrayList<Barre> barres;

	/**
	 * axe des x pour la camera
	 */
	private float cameraX;

	/**
	 * axe des y pour la camera
	 */
	private float cameraY;

	/**
	 * Gestion du niveau en cours
	 */
	private Monde monde;

	/**
	 * Le robot du jeu
	 */
	private static Robot player;

	
	/**
	 * Gestion de la pause du jeu
	 */
	private boolean paused = false;

	private float decalage = 10;

	public int getID() {
		return ID;
	}

	/**
	 * Constructeur de RoboTechJeu
	 * @param ID
	 */
	public RoboTechJeu(int ID) {
		//initialisation des differentes variables, notamment des barres du robot pour l'affiche dans l'interface
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

	/**
	 * Initialisation du jeu
	 */
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// demarre le niveau, en appuyant sur la touche 'R', possibilite de
		// redemarrer le niveau
		restart(game);
		monde.init(container, game);
		
		//affichage des fps
		container.setShowFPS(affichageFPS);
	}

	/**
	 * Permet de modifier l'image du robot a afficher dans le jeu en fonction du choix fait dans le menu option
	 * @param robot
	 * @throws SlickException
	 */
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

	/**
	 * Gere le rendu du jeu
	 */
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {

		g.translate(-(int) cameraX, -(int) cameraY); // gere le rendu de la
		// camera
		
		//si le jeu n'est pas en pause
		if (!paused) {
			monde.render(container, game, g); // gere le rendu du monde complet

		}
		
		//affichage de l'interface des barres du robot
		InfoRobot(g);
		
	}

	/**
	 * Gere la mise a jour du jeu
	 */
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		Input input = container.getInput();

			
		// redemarrer le jeu
		if (input.isKeyPressed(Input.KEY_R)) {
			init(container, game);
			return;
		}

		//mettre le jeu en pause
		if (input.isKeyPressed(Input.KEY_P))
			paused = !paused;

		//afficher/cacher les fps
		if(input.isKeyPressed(Input.KEY_F))
		{
			affichageFPS = !affichageFPS;
			container.setShowFPS(affichageFPS);
		}
		
		// met a jour le monde s'il n'est pas en pause et que la fenetre a le focus
		if (!paused && container.hasFocus()==true)
			monde.update(container, game, delta);

		//si le robot est mort, on rentre dans l'etat de la mort du robot et redemarre le jeu
		if (player.getVie() <= 0) {
			game.enterState(RoboTech.MORTETAT);
			init(container, game);
		}
		
		// calcule la zone affichee par la camera
		cameraX = player.getX() - 400;
		cameraY = player.getY() - 300;
	}

	/**
	 * Affichage des barres du robot pour l'interface
	 * @param g
	 */
	public void InfoRobot(Graphics g) {
		for (int i = 0; i < barres.size(); i++) {
			barres.get(i).update(cameraX-1,
					cameraY + i * (decalage + barres.get(i).getHeight()));
			barres.get(i).render(g, player);
		}

	}
}
