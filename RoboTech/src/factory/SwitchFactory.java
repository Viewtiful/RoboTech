package factory;

import java.util.Collection;
import java.util.HashMap;

import net.phys2d.raw.World;

import org.newdawn.slick.Image;
import org.newdawn.slick.tiled.TiledMap;

import blocs.Capteur;
import blocs.Point;
import blocs.Switch;

/**
 * 
 * @author Equipe RoboTech
 * Createur de Bouton
 */
public class SwitchFactory extends AbstractFactory {

	/**
	 * Bouton en cours de création
	 *
	 */
	private HashMap<String, Switch> chaine;

	/**
	 * Crée une fabrique
	 * @param world Monde Physique
	 * @param map Carte du niveau
	 */
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

	/**
	 * Crée une nouveau bouton et l'intègre dans l'environnement de jeu
	 * @param i Abscisse de l'objet dans la carte
	 * @param j Ordonnée de l'objet dans la carte
	 */
	public void createSwitch(int i, int j) {
		Point position = getPosition(i, j);
		int width = getWidth(i, j);
		int height = getHeight(i, j);
		position.add(width / 2, height / 2);
		Image image_on = getImage(i, j, "Image_On");
		Image image_off = getImage(i, j, "Image_Off");

		String name = getName(i, j);
		String type = recupererPropriete(i,j,"Type");
		Switch s;
		if(!type.equals("Capteur"))
			s = new Switch(image_off, width, height, position, image_on);
		else
		{
			Capteur c = new Capteur(image_off, width, height, position, image_on);;
			c.setVertical(false);
			s = c;
			s.getBody().setEnabled(false);
			
		}
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
