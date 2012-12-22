package item_joueurs;

import items.Items;

public abstract class ItemsRamassable extends Items{

	public abstract void setPickedUp(boolean b);
	public abstract String getNom();	
}
