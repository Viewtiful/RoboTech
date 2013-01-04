package jeu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Menu extends BasicGameState{
	private int ID = -1;
	private Image itemMenu[];
	private Image imageFond;
	private int sourisX, sourisY;
	private boolean interieurBouton[];
	private int positionItemMenu[][];

	public Menu(int ID) {
		this.ID = ID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		imageFond = new Image("res/backgroundMenu.png");	
		
		itemMenu = new Image[3];
		itemMenu[0] = new Image("res/jeuMenu.png");	
		itemMenu[1] = new Image("res/optionMenu.png");	
		itemMenu[2] = new Image("res/quitterMenu.png");	
		
		interieurBouton = new boolean[itemMenu.length];
		//premier dimension nombre item du menu, deuxieme dimension position x et y de cet item du menu
		positionItemMenu = new int[itemMenu.length][2];
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		//image de fond du menu
		imageFond.draw(0,0);
		
		//item pour demarrer le jeu
		positionItemMenu[0][0] = 50;
		positionItemMenu[0][1] = 220;
		itemMenu[0].draw(positionItemMenu[0][0], positionItemMenu[0][1]);
		
		//item pour acceder au option
		positionItemMenu[1][0] = 70;
		positionItemMenu[1][1] = 290;
		itemMenu[1].draw(positionItemMenu[1][0], positionItemMenu[1][1]);
		
		//item pour quitter le jeu
		positionItemMenu[2][0] = 75;
		positionItemMenu[2][1] = 360;
		itemMenu[2].draw(positionItemMenu[2][0], positionItemMenu[2][1]);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();

		sourisX = input.getMouseX();
		sourisY = input.getMouseY();
		

		//parcours les items du menu, pour voir si on est dessus
		for (int i = 0; i < itemMenu.length; i++) {
			interieurBouton[i] = false;

			if ((sourisX >= positionItemMenu[i][0] && sourisX <= positionItemMenu[i][0] + itemMenu[i].getWidth())
					&& (sourisY >= positionItemMenu[i][0] && sourisY <= positionItemMenu[i][1] + itemMenu[i].getHeight())) {
				interieurBouton[i] = true;
			}
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
	
	//lance le jeu
	public void lancerJeu(StateBasedGame sbg) {
		sbg.enterState(RoboTech.JEUETAT);
	}
	
	//lance le menu des options
	public void lancerOption(StateBasedGame sbg) {
		sbg.enterState(RoboTech.OPTIONETAT);
	}

	@Override
	public int getID() {
		return ID;
	}

}
