package barres;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import personnages.Robot;

public class BarreEnergie extends Barre{

	public BarreEnergie(float x, float y, float height, float width,String barre_name) {
		super(x, y, height, width,Color.yellow,barre_name);
	
	}

	public void render(Graphics g, Robot player) {
		getBarre().setWidth((float) length(player) * (float)getWidth()
				/ (float) player.getMax());
		if(player.getPlusEnergie())
			g.setColor(Color.green);
		else
			g.setColor(getCouleur());
		g.fill(getBarre());
		g.drawString(getBarName(), getBarre().getX(), getBarre().getY()-15);
	}
	
	@Override
	public int length(Robot player) {
		return player.getEnergie();
	}

	

}
