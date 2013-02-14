package gui;


import org.newdawn.slick.Color;

import personnages.Robot;

public class BarreVie extends Barre{

	/**
	 * 
	 * @param x abscisse de la barre
	 * @param y ordonnée de la barre
	 * @param height hauteur de la barre
	 * @param width largeur de la barre
	 * @param barre_name nom de la barre à afficher
	 */
	public BarreVie(float x, float y, float height, float width,String barre_name) {
		super(x, y, height, width, Color.red,barre_name);

	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public int length(Robot player) {
		
		return player.getVie();
	}

	

	
}
