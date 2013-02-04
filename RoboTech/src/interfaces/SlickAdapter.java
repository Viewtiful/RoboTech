package interfaces;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Equipe RoboTech <i>Cette classe permet d'adapter le code du moteur du jeu,
 * avec un moteur graphique</i>
 */
public interface SlickAdapter {

	/**
	 * Permet d'initialiser un composant graphique
	 * 
	 * @param container
	 *            Conteneur du jeu
	 * @see org.newdawn.Slick.GameContainer
	 * @param game
	 *            �tat du jeu
	 * @see org.newdawn.Slick.StateBasedGame
	 * 
	 * @param g
	 *            Objet Graphique
	 * @see org.newdawn.Slick.Graphics
	 * 
	 * @throws SlickException
	 * @see org.newdawn.Slick.SlickException
	 */

	public abstract void init(GameContainer container, StateBasedGame game)
			throws SlickException;

	/**
	 * Permet de dessiner un composant graphique
	 * 
	 * @param container
	 *            Conteneur du jeu
	 * @see org.newdawn.Slick.GameContainer
	 * @param game
	 *            �tat du jeu
	 * @see org.newdawn.Slick.StateBasedGame
	 * @param g
	 *            Objet Graphique
	 * @see org.newdawn.Slick.Graphics
	 */
	public abstract void render(GameContainer container, StateBasedGame game,
			Graphics g) throws SlickException;

	/**
	 * Permet de mettre � jour un composant graphique
	 * 
	 * @param container
	 *            Conteneur du jeu
	 * @see org.newdawn.Slick.GameContainer
	 * @param game
	 *            �tat du jeu
	 * @see org.newdawn.Slick.StateBasedGame
	 * @param delta
	 *            Entier
	 * 
	 * @throws SlickException
	 * @see org.newdawn.Slick.SlickException
	 */
	public abstract void update(GameContainer container, StateBasedGame game,
			int delta) throws SlickException;
}
