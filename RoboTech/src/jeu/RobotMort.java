package jeu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class RobotMort extends BasicGameState {
	private int ID = -1;
	private Image[] itemMort;
	private boolean[] interieurBouton;
	private int[][] positionItemOption;
	private int sourisX;
	private int sourisY;

	public RobotMort(int ID) {
		this.ID = ID;
	}

	@Override
	public int getID() {
		return ID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		itemMort = new Image[1];
		itemMort[0] = new Image("res/retourOption.png");

		interieurBouton = new boolean[itemMort.length];
		positionItemOption = new int[itemMort.length][2];
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		// retour menu principal
		positionItemOption[0][0] = 300;
		positionItemOption[0][1] = 280;
		itemMort[0].draw(positionItemOption[0][0], positionItemOption[0][1]);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();

		sourisX = input.getMouseX();
		sourisY = input.getMouseY();

		// parcours les items des options, pour voir si on est dessus
		for (int i = 0; i < itemMort.length; i++) {
			interieurBouton[i] = false;

			if ((sourisX >= positionItemOption[i][0] && sourisX <= positionItemOption[i][0]
					+ itemMort[i].getWidth())
					&& (sourisY >= positionItemOption[i][1] && sourisY <= positionItemOption[i][1]
							+ itemMort[i].getHeight())) {
				itemMort[i].setAlpha(0.65f);
				interieurBouton[i] = true;
			} else
				itemMort[i].setAlpha(1.f);
		}

		// Retour menu principal
		if (interieurBouton[0] && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			sbg.enterState(RoboTech.MENUETAT);
		}
	}
}
