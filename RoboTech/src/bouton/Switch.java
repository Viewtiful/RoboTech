package bouton;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import interfaces.Drawable;

public abstract class Switch implements Drawable {

	Image Switch_off;
	Image Switch_on;
	boolean on;
	ArrayList<Signal> effet;

	public Switch(String Switch_off, String Switch_on) {
		this.on = false;
		effet = new ArrayList<Signal>();
	}

	public void add(Signal r) {
		effet.add(r);
	}

	/*
	 * Fonction qui détermine si le bouton est actionné ou pas
	 */
	public abstract boolean activate_Switch();

	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		on = activate_Switch();
	}
}
