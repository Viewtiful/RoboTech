package jeu;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Classe qui initialise le container du jeu ainsi que les différents états du
 * jeu
 * 
 * @author Equipe RoboTech
 * 
 */
public class RoboTech extends StateBasedGame {
	/**
	 * Etat permettant d'accéder au menu principal du jeu
	 */
	public static final int MENUETAT = 0;
	/**
	 * Etat permettant d'accéder aux options du jeu
	 */
	public static final int OPTIONETAT = 1;
	/**
	 * Etat permettant d'accéder au jeu
	 */
	public static final int JEUETAT = 2;
	/**
	 * Etat accéder lorsque le robot meurt pour revenir au menu principal
	 */
	public static final int MORTETAT = 3;
	/**
	 * Etat accéder lorsque le robot tue le boss et fini le jeu
	 */
	public static final int VICTOIRETAT = 4;
	/**
	 * Etat accéder au niveau suivant
	 */
	public static final int NIVSUIVANTETAT = 5;

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
		// lance l'état lorsque le robot meurt
		addState(new RobotMort(MORTETAT));
		// lance l'état victoire lorsque le robot a tué le boss final
		addState(new Victoire(VICTOIRETAT));
		// lance l'état niveau suivant lorsque le robot tue le boss
		addState(new NiveauSuivant(NIVSUIVANTETAT));

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
