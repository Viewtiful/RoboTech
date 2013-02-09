package factory;

import java.util.Collection;
import java.util.HashMap;

import net.phys2d.raw.World;

import org.newdawn.slick.Image;
import org.newdawn.slick.tiled.TiledMap;

import blocs.BlocsBlessant;
import blocs.Plateforme;
import blocs.Point;
import blocs.Switch;

/**
 * 
 * @author Equipe RoboTech Createur de blocs
 */
public class PlateformeFactory extends AbstractFactory {

	private HashMap<String, Plateforme> chaine;

	private SwitchFactory s;

	public PlateformeFactory(World world, TiledMap map, SwitchFactory s) {
		super(world, map);
		chaine = new HashMap<String, Plateforme>();
		this.s = s;
	}

	public void addPosition(int i, int j) {
		String name = map.getObjectName(i, j);
		Plateforme p = getProduit(name);
		System.out.println(name);
		Point position = getPosition(i, j);
		int width = map.getObjectWidth(i, j);
		int height = map.getObjectHeight(i, j);
		p.addPoint(position.getX() + width / 2, position.getY() + height / 2);
	}

	private Plateforme getProduit(String name) {
		return chaine.get(name);
	}

	public Collection<Plateforme> getProduit() {
		return chaine.values();
	}

	public void createPlateforme(int i, int j) {
		Point position = getPosition(i, j);
		int width = getWidth(i, j);
		int height = getHeight(i, j);
		position.add(width / 2, height / 2);
		Image image_box = getImage(i, j, "Image");
		boolean signal = Boolean
				.parseBoolean(recupererPropriete(i, j, "Signal"));
		boolean reverse = Boolean.parseBoolean(recupererPropriete(i, j,
				"Reverse"));

		String name = getName(i, j);
		String type = recupererPropriete(i, j, "Type");
		System.out.println(type);
		Plateforme p = new Plateforme(position, image_box, width, height);
		// Plateforme p = new Plateforme(position,new BlocsBlessant(image_box,
		// width, height, position,
		// 4));
		//
		p.addPoint(position.getX(), position.getY());
		p.setSignal(signal);
		p.setReverse(reverse);
		world.add(p.getBody());
		chaine.put(name, p);
		String sender_name = recupererPropriete(i, j, "Sender");
		boolean draw = Boolean.parseBoolean(recupererPropriete(i, j, "draw"));
		p.setTrajectoireDraw(draw);
		if (!sender_name.equals("NOT_FOUND")) {
			Switch sender = s.get_produit(sender_name);
			sender.add(p);
		}
		System.out.println(p);

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
