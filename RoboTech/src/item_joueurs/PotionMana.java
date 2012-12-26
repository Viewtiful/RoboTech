package item_joueurs;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import personnages.Personnage;
import personnages.Robot;

public class PotionMana extends Potion {

	public PotionMana(float x, float y, float width, float height, float mass,
			Robot player, int value) {
		super(x, y, width, height, mass, player, value);
	}

	public PotionMana(float x, float y, float width, float height, float mass,
			Robot player) {
		super(x, y, width, height, mass, player, 1);
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		image = new Image("res/potionMana.png");

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		image.draw(getX() - 5, getY() - 7);
	}


	@Override
	public void effect(Robot player) {
		player.ajouterMana(value);

	}

}
