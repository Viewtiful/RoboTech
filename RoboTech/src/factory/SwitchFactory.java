package factory;

import java.util.Collection;
import java.util.HashMap;

import net.phys2d.raw.World;

import org.newdawn.slick.Image;
import org.newdawn.slick.tiled.TiledMap;

import blocs.Point;
import blocs.Switch;

public class SwitchFactory extends AbstractFactory {

	private HashMap<String, Switch> chaine;

	public SwitchFactory(World world, TiledMap map) {
		super(world, map);
		chaine = new HashMap<String, Switch>();
	}

	public Switch get_produit(String name) {
		return chaine.get(name);
	}

	public Collection<Switch> getProduit() {
		return chaine.values();
	}

	public void createSwitch(int i, int j) {
		Point position = getPosition(i, j);
		int width = getWidth(i, j);
		int height = getHeight(i, j);
		position.add(width / 2, height / 2);
		Image image_on = getImage(i, j, "Image_On");
		Image image_off = getImage(i, j, "Image_Off");

		String name = getName(i, j);
		Switch s = new Switch(image_off, width, height, position, image_on);
		world.add(s.getBody());
		chaine.put(name, s);
		System.out.println(position);
	}

	@Override
	public String toString() {
		String res = super.toString();
		res = res + "Longueur = " + chaine.size() + "\n";
		for (int i = 0; i < chaine.size(); i++)
			res = res + "[" + i + "]" + chaine.get(i).toString();
		res = res + "\n";
		return res;
	}
}
