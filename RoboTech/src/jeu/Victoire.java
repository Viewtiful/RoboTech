package jeu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Victoire extends BasicGameState {
	private int ID = -1;
	private Image[] itemVictoire;
	private boolean[] interieurBouton;
	private int[][] positionItemOption;
	private int sourisX;
	private int sourisY;

	public Victoire(int ID) {
		this.ID = ID;
	}

	@Override
	public int getID() {
		return ID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		itemVictoire = new Image[2];
		itemVictoire[0] = new Image("res/retourOption.png");
		itemVictoire[1] = new Image("res/victoire.png");

		interieurBouton = new boolean[itemVictoire.length];
		positionItemOption = new int[itemVictoire.length][2];
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		// retour menu principal
		positionItemOption[0][0] = 300;
		positionItemOption[0][1] = 280;
		itemVictoire[0]
				.draw(positionItemOption[0][0], positionItemOption[0][1]);
		itemVictoire[1].draw(300, 150);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();

		sourisX = input.getMouseX();
		sourisY = input.getMouseY();

		// parcours les items des options, pour voir si on est dessus
		for (int i = 0; i < itemVictoire.length; i++) {
			interieurBouton[i] = false;

			if ((sourisX >= positionItemOption[i][0] && sourisX <= positionItemOption[i][0]
					+ itemVictoire[i].getWidth())
					&& (sourisY >= positionItemOption[i][1] && sourisY <= positionItemOption[i][1]
							+ itemVictoire[i].getHeight())) {
				itemVictoire[i].setAlpha(0.65f);
				interieurBouton[i] = true;
			}
			else
				itemVictoire[i].setAlpha(1.f);
		}

		// Retour menu principal
		if (interieurBouton[0] && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			sbg.enterState(RoboTech.MENUETAT);
		}
	}
}
