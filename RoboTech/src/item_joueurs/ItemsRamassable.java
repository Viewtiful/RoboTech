package item_joueurs;

import personnages.Personnage;
import personnages.Robot;
import items.Items;

public abstract class ItemsRamassable extends Items {

	protected Robot player;

	public abstract void setPickedUp(boolean b);

	/* Getters */

	public boolean getUsed() {
		return used;
	}

	public Personnage getPlayer() {
		return player;
	}

	/* Setters */

	public void setUsed(boolean used) {
		this.used = used;
	}

	public void setPlayer(Robot player) {
		this.player = player;
	}
	
	public void pickUpItem() {
		float eps = (float) 1e-01;
		float n = player.getTaille();
		if((Math.abs(player.getY() - getY()) - (getHeight()/2 + player.getTaille()/2)) <eps){
			float x_gauche = player.getX() - n / 2;
			float x_droite = player.getX() + n / 2;

			float p_gauche = getX() - getWidth() / 2;
			float p_droite = getX() + getWidth() / 2;

			if ((x_droite <= p_droite && x_droite >= p_gauche)
					|| (x_gauche <= p_droite && x_gauche >= p_gauche)) {
				used = effect(player);
				if (used)
					world.remove(getBody());
			}
			
		}
	}
	/**
	 * Effet de l'itemRamassable sur le robot
	 * 
	 * @param player
	 */
	public abstract boolean effect(Robot player);
}
