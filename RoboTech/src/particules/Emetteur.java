package particules;

import items.Caisse;
import items.Items;

import java.util.ArrayList;

import jeu.Monde;

public class Emetteur {

	Monde monde;
	
	public Emetteur(Monde monde)
	{
		this.monde = monde;
	}
	public void emettre(int n)
	{
		for(int i = 0;i<n;i++)
		monde.addItems(new Caisse(250, 250, 10, 10, 1));
		
	}
}
