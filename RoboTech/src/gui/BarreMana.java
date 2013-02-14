package gui;


import org.newdawn.slick.Color;

import personnages.Robot;
/**
 * Barre repr�sentant la barre de Mana
 * @author Equipe RoboTech
 *
 */
public class BarreMana extends Barre {

	/**
	 * 
	 * @param x abscisse de la barre
	 * @param y ordonn�e de la barre
	 * @param height hauteur de la barre
	 * @param width largeur de la barre
	 * @param barre_name nom de la barre � afficher
	 */
	public BarreMana(float x, float y, float height, float width,
			String barre_name) {
		super(x, y, height, width, Color.cyan, barre_name);
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public int length(Robot player) {
		return player.getMana();
	}

}
