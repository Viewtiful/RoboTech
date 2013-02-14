package gui;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import personnages.Robot;

/**
 * Barre affichant le niveau d'énergie
 * @author Equipe RoboTech
 *
 */
public class BarreEnergie extends Barre{

	/**
	 * 
	 * @param x abscisse de la barre
	 * @param y ordonnée de la barre
	 * @param height hauteur de la barre
	 * @param width largeur de la barre
	 * @param barre_name nom de la barre à afficher
	 */
	public BarreEnergie(float x, float y, float height, float width,String barre_name) {
		super(x, y, height, width,Color.yellow,barre_name);
	
	}
	
	@Override
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
	/**
	 * {@inheritDoc}
	 */
	public int length(Robot player) {
		return player.getEnergie();
	}

	

}
