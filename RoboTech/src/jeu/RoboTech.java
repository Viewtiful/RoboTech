package jeu;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class RoboTech extends StateBasedGame {
	
	//Cree le jeu
	public RoboTech() {
		super("RoboTech");
	}
	
	public void initStatesList(GameContainer container) throws SlickException {
		
		//60 fps
		container.setTargetFrameRate(60);
		container.setVSync(true);
		
		//lance le niveau du jeu
		addState(new RoboTechJeu());
	}

	/**
	 * Point d'entree du jeu
	 * @param argv
	 * @throws SlickException
	 */
	public static void main(String[] argv) throws SlickException {
		AppGameContainer container = new AppGameContainer(new RoboTech(), 800, 500, false);
		container.start();
	}
}
