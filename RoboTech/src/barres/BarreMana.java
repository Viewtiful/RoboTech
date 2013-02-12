package barres;

import org.newdawn.slick.Color;

import personnages.Robot;

public class BarreMana extends Barre {

	public BarreMana(float x, float y, float height, float width,
			String barre_name) {
		super(x, y, height, width, Color.cyan, barre_name);
	}

	@Override
	public int length(Robot player) {
		return player.getMana();
	}

}
