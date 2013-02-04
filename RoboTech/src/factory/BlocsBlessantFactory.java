package factory;

import java.util.Collection;
import java.util.HashMap;

import net.phys2d.raw.World;

import org.newdawn.slick.tiled.TiledMap;

import blocs.BlocsBlessant;

public class BlocsBlessantFactory extends AbstractFactory {

	private HashMap<String, BlocsBlessant> chaine;

	public BlocsBlessantFactory(World world, TiledMap map) {
		super(world, map);
	}

	private BlocsBlessant get_produit(String name) {
		return chaine.get(name);
	}

	public Collection<BlocsBlessant> get_produit() {
		return chaine.values();
	}
}
