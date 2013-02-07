package factory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import net.phys2d.raw.World;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import blocs.BlocsBlessant;
import blocs.Point;

public class BlocsBlessantFactory extends AbstractFactory {

	private ArrayList<BlocsBlessant> chaine;

	public BlocsBlessantFactory(World world, TiledMap map) {
		super(world, map);
		chaine = new ArrayList<BlocsBlessant>();
	}

	private BlocsBlessant get_produit(int i) {
		return chaine.get(i);
	}

	public Collection<BlocsBlessant> get_produit() {
		return chaine;
	}

	public void CreateBlocsBlessant(int i, int j) throws SlickException {

		Point p = get_Position(i,j);
		Point dim = getDimension(i, j);
		p.add(dim.get_x()/2, dim.get_y()/2);
		Image im = new Image("res/pics.png");
		BlocsBlessant b = new BlocsBlessant(im, dim.get_x(), dim.get_y(), p,1);
		chaine.add(b);
		world.add(b.getBody());
	}
}
