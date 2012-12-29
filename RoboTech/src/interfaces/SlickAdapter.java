package interfaces;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public interface SlickAdapter {

	/* Cette interface permet d'introduire la notion d'adapteur (design pattern) */
	/* D'une certaine façon on peut couper le projet en deux (interface et moteur du jeu) */
	
	public abstract void init(GameContainer container, StateBasedGame game)
			throws SlickException;

	public abstract void render(GameContainer container, StateBasedGame game,
			Graphics g) throws SlickException;

	public abstract void update(GameContainer container, StateBasedGame game,
			int delta) throws SlickException;
}
