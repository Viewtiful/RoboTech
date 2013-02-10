package jeu;

import org.newdawn.slick.Color;

import personnages.Robot;

public class BarreEnergie extends Barre{

	public BarreEnergie(float x, float y, float height, float width,Color couleur,String barre_name) {
		super(x, y, height, width,couleur,barre_name);
		System.out.println("Height "+getHeight());
		System.out.println("Width "+getWidth());
	
	}

	@Override
	public int length(Robot player) {
		return player.getEnergie();
	}

	

}
