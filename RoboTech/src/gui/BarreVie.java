package gui;


import org.newdawn.slick.Color;

import personnages.Robot;

public class BarreVie extends Barre{

	public BarreVie(float x, float y, float height, float width,String barre_name) {
		super(x, y, height, width, Color.red,barre_name);

	}

	@Override
	public int length(Robot player) {
		
		return player.getVie();
	}

	

	
}
