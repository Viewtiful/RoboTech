package jeu;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class RoboTech extends StateBasedGame {
	public static final int MENUETAT = 0;
	public static final int OPTIONETAT = 1;
	public static final int JEUETAT = 2;
	public static final int MORTETAT = 3;

	// Cree le jeu
	public RoboTech() {
		super("RoboTech");
	}

	public void initStatesList(GameContainer container) throws SlickException {

		// 60 fps
		container.setTargetFrameRate(60);
		container.setVSync(true);

		// lance le menu
		addState(new Menu(MENUETAT));
		// lance les options
		addState(new Options(OPTIONETAT));
		// lance le niveau du jeu
		addState(new RoboTechJeu(JEUETAT));

		addState(new RobotMort(MORTETAT));
	}

	/**
	 * Point d'entree du jeu
	 * 
	 * @param argv
	 * @throws SlickException
	 */
	public static void main(String[] argv) throws SlickException {
		AppGameContainer container = new AppGameContainer(new RoboTech(), 800,
				500, false);
		container.start();
	}
}
