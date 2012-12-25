package item_joueurs;

import personnages.Personnage;
import personnages.Robot;
import items.Items;

public abstract class ItemsRamassable extends Items {

	protected Robot player;

	public abstract void setPickedUp(boolean b);

	/* Getters */

	public boolean get_used() {
		return used;
	}

	public Personnage get_player() {
		return player;
	}

	/* Setters */

	public void set_used(boolean used) {
		this.used = used;
	}

	public void set_player(Robot player) {
		this.player = player;
	}
}
