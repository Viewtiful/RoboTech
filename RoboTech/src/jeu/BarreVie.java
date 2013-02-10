package jeu;

import org.newdawn.slick.Color;

import personnages.Robot;

public class BarreVie extends Barre{

	public BarreVie(float x, float y, float height, float width,Color couleur,String barre_name) {
		super(x, y, height, width, couleur,barre_name);

	}

	@Override
	public int length(Robot player) {
		
		return player.getVie();
	}

	

	
}
