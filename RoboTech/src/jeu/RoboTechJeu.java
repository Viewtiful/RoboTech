package jeu;

import item_joueurs.Potion;
import item_joueurs.PotionEnergie;
import item_joueurs.PotionMana;
import item_joueurs.PotionVie;
import items.Baril;
import items.Caisse;
import items.Items;
import items.Poutre;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import personnages.EnnemisRouge;
import personnages.EnnemisVert;
import personnages.Personnage;
import personnages.Robot;
import weapon.Balle;

public class RoboTechJeu extends BasicGameState {
	/** The unique ID given to the state */
	private static final int ID = 1;

	private Items caisse;
	private Items baril;
	private Items poutre;
	// liste des items ramassable
	// private ArrayList<Items> itemsRamassable;
	// private ArrayList<Personnage> personnages;
	private Balle balle;
	// axe des x pour la camera
	private float cameraX;
	// axe des y pour la camera
	private float cameraY;

	// le niveau
	private Monde monde;

	private Robot player;

	public int getID() {
		return ID;
	}

	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// demarre le niveau, en appuyant sur la touche 'R', possibilite de
		// redemarrer le niveau
		restart();
		monde.init(container, game);
	}

	/**
	 * Fonction qui permet de redemarrer le niveau
	 * 
	 * @throws SlickException
	 */
	private void restart() throws SlickException {
		monde = new Monde();
		monde.initialisationMonde();
		player = new Robot(280, 150, 1.f, 32);
		monde.setplayer(player);
		monde.addPersonnages(player);
		monde.addPersonnages(new EnnemisRouge(400, 50, 2f, 64));
		monde.addPersonnages(new EnnemisVert(600, 50, 2f, 64));

		monde.addItemsRamassable(new PotionVie(880, 250, 10, 14, 0.8f, player,
				10));
		monde.addItemsRamassable(new PotionMana(305, 250, 10, 14, 0.8f, player,
				10));
		monde.addItemsRamassable(new PotionEnergie(350, 250, 13, 20, 0.8f,
				player, 10));

		caisse = new Caisse(700, 10, 40, 40, 8.f);
		baril = new Baril(1170, 200, 28, 40, 3.5f);
		poutre = new Poutre(880, 60, 25, 130, 3.5f);
		monde.addItems(caisse);
		monde.addItems(baril);
		monde.addItems(poutre);

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

		if (input.isKeyPressed(Input.KEY_X)) {
			System.out.println("Balle");
			balle = new Balle(player.getX(), player.getY(),
					player.getDirectionDroite(), 0.01f);
			monde.addBalles(balle);
			balle.applyForce(10000, 0);
		}

		// met a jour le monde
		monde.update(container, game, delta);

		// calcule la zone affichee par la camera
		cameraX = player.getX() - 400;
		cameraY = player.getY() - 300;
	}

}
