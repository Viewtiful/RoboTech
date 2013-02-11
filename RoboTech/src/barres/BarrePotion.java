package barres;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import personnages.Robot;

public class BarrePotion extends Barre {

	public BarrePotion(float x, float y, float width, float height,
			String barName) {
		super(x, y, width, height, Color.magenta, barName);

	}

	public void render(Graphics g, Robot player) {
		if (player.getPotionVitesse()) {
			float calcul = (float) ((float) length(player) / (float) 10.0)
					* (float) getWidth();
			getBarre().setWidth(calcul);
			g.setColor(getCouleur());
			g.fill(getBarre());
			g.drawString(getBarName(), getBarre().getX(),
					getBarre().getY() - 15);
		}
	}

	@Override
	public int length(Robot player) {
		return 10 - player.getEstomperEffetPotion();
	}

}
