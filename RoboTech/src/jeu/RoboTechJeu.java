package jeu;

import item_joueurs.Potion;
import items.Baril;
import items.Caisse;
import items.Items;
import items.Poutre;

import java.util.ArrayList;

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

public class RoboTechJeu extends BasicGameState {
	/** The unique ID given to the state */
	private static final int ID = 1;
	

	//le robot
	private Personnage player;
	private Personnage ennemi;
	private Personnage ennemi2;
	private Items caisse;
	private Items baril;
	private Items poutre;
	//liste des items ramassable
	protected ArrayList<Items> itemsRamassable;
	
	//axe des x pour la camera
	private float cameraX;
	//axe des y pour la camera
	private float cameraY;

	//le niveau
	private Monde monde;
	

	public int getID() {
		return ID;
	}

	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		//demarre le niveau, en appuyant sur la touche 'R', possibilite de redemarrer le niveau
		restart();
		monde.init(container,game);
	}

	/**
	 * Fonction qui permet de redemarrer le niveau
	 * @throws SlickException
	 */
	private void restart() throws SlickException {
		monde = new Monde();
		monde.initialisationMonde();
		itemsRamassable = new ArrayList<Items>();
		itemsRamassable.add(new Potion(880,250, 10,14,0.8f, "potionVie"));
		itemsRamassable.add(new Potion(305,250, 10,14,0.8f, "potionMana"));
		itemsRamassable.add(new Potion(350,250, 13,20,0.8f, "potionEnergie"));

		
		player = new Robot(280,150,1f,32);
		ennemi = new EnnemisRouge(400, 50, 2f, 64);
		ennemi2 = new EnnemisVert(600, 50, 2f, 64);
		caisse = new Caisse(700,10, 40,40,4.f);
		baril = new Baril(1170,200, 28,40,3.5f);
		poutre = new Poutre(880,60, 25,130,3.5f);
		monde.addPersonnages(player);
		monde.addPersonnages(ennemi);
		monde.addPersonnages(ennemi2);
		monde.addItems(caisse);
		monde.addItems(baril);
		monde.addItems(poutre);
		for (int i=0;i<itemsRamassable.size();i++) {
			monde.addItemsRamassable(itemsRamassable.get(i));
		}
		
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
		g.translate(-(int) cameraX, -(int) cameraY);  //gere le rendu de la camera
	
		   
		monde.render(container, game, g);  //gere le rendu du monde complet
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		Input input = container.getInput();
		
		// redemarrer le jeu
		if (input.isKeyPressed(Input.KEY_R)) {
			restart();
			return;
		}

		// redemarrer le jeu
		if (input.isKeyPressed(Input.KEY_R)) {
		restart();
		return;
		}

		//moveForce : applique pour deplacer le personnage
		float moveForce = 50;
		//jumpForce applique pour faire sauter le personnage
		float jumpForce = 50000;

		// setup the player's moving flag, this control the animation
		player.setEnMouvement(false);

		if (input.isKeyDown(Input.KEY_LEFT)) {
			player.setEnMouvement(true);
			player.applyForce(-moveForce, 0);
		}
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			player.setEnMouvement(true);
			player.applyForce(moveForce, 0);
		}
		if (player.auSol()) {
			if ((input.isKeyPressed(Input.KEY_LCONTROL)) ||
					(input.isKeyPressed(Input.KEY_RCONTROL))) {
				if (player.getDirectionDroite()) {
						player.applyForce(0, -jumpForce);
					} else {
							player.applyForce(0, -jumpForce);
					}
			}
		}
		//si on n'appuye pas sur la touche saut et que le perso est en train de sauter, on le fait redescendre doucement
		if (!input.isKeyDown(Input.KEY_LCONTROL)) {
			if (player.getSaut()) {
					player.setVelocity(player.getVelX(), player.getVelY() * 0.99f);
			}
		}
		//met a jour le monde
		monde.update(container,game,delta);
				
		// Parcours la liste des items ramassable, si le joueur est en contact avec, il le ramasse
		for (int i=0;i<itemsRamassable.size();i++) {
			if(itemsRamassable.get(i) != null) {
				int tileX = (int)(itemsRamassable.get(i).getX() / 32);
				int tileY = (int)(itemsRamassable.get(i).getY() / 32);
				float pickupWidth = itemsRamassable.get(i).getWidth() / 32;
				float pickupHeight = itemsRamassable.get(i).getHeight() / 32;
				int playerPosX = (int)(player.getX() / 32);
				int playerPosY = (int)(player.getY() / 32);
				
				if (playerPosX >= tileX && playerPosX < (tileX+pickupWidth) &&
						playerPosY >= tileY && playerPosY < (tileY+pickupHeight)) {
					player.pickupItem( itemsRamassable.get(i));
					monde.supprimer(itemsRamassable.get(i));
					itemsRamassable.remove(i);
				}
			}
		}
		
		// calcule la zone affichee par la camera
		cameraX = player.getX() - 400;
		cameraY = player.getY() - 300;
	}

}
