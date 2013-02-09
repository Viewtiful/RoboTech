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
}
