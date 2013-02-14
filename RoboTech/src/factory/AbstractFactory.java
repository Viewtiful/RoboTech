package factory;

import net.phys2d.raw.World;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import blocs.Point;
/**
 * Fabrique abstraites de composant du jeu
 * @author Equipe RoboTech
 *
 */
public abstract class AbstractFactory {

	/**
	 * Monde physique
	 */
	protected World world;
	/**
	 * Carte a construire
	 */
	protected TiledMap map;

	/**
	 * 
	 * @param world Monde Physique
	 * @param map Carte a construire
	 */
	public AbstractFactory(World world, TiledMap map) {
		this.world = world;
		this.map = map;
	}

	/**
	 * Recupere la valeur de la propriete
	 * @param i Abscisse dans la carte
	 * @param j ordonnee dans la carte
	 * @param Tag nom de la propriete
	 * @return la valeur de la propriete ou NOT_FOUND si non trouve
	 */
	public String recupererPropriete(int i, int j, String Tag) {
		return map.getObjectProperty(i, j, Tag, "NOT_FOUND");
	}

	/**
	 * Renvoie la position d'un objet de la carte
	 * @param i Abscisse dans la carte
	 * @param j Abscisse dans la carte
	 * @return Position de l'objet en format Point
	 */
	public Point getPosition(int i, int j) {
		return new Point(map.getObjectX(i, j), map.getObjectY(i, j));
	}

	/**
	 * Renvoie l'image chargee
	 * @param i Abscisse dans la carte
	 * @param j Ordonnee dans la carte
	 * @param Tag nom de la propriete
	 * @return Image 
	 */
	public Image getImage(int i, int j, String Tag) {
		String Image = recupererPropriete(i, j, Tag);
		assert (!Image.equals("NOT_FOUND"));
		Image image_box = null;
		try {
			image_box = new Image(Image);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		return image_box;
	}

	/**
	 * Retourne la largeur de l'objet
	 * @param i Abscisse dans la carte
	 * @param j Ordonnee dans la carte
	 * @return La largeur ou -1 en cas d'erreur
	 */
	public int getWidth(int i, int j) {
		return map.getObjectWidth(i, j);
	}

	/**
	 * Retourne la hauteur de l'objet
	 * @param i Abscisse dans la carte
	 * @param j Ordonnee dans la carte
	 * @return La hauteur ou -1 en cas d'erreur
	 */
	public int getHeight(int i, int j) {
		return map.getObjectHeight(i, j);
	}

	/**
	 * Retourne les dimension de l'objet
	 * @param i Abscisse dans la carte
	 * @param j Ordonnee dans la carte
	 * @return les dimension sous forme de Vecteur
	 */
	public Point getDimension(int i, int j) {
		return new Point(getWidth(i, j), getHeight(i, j));
	}

	/**
	 * 
	 * Renvoie le non d'un objet
	 * @param i Abscisse dans la carte
	 * @param j Ordonnee dans la carte
	 * @return Nom
	 */
	public String getName(int i, int j) {
		return map.getObjectName(i, j);
	}

	@Override
	public String toString() {
		return "Liste de Production" + "\n";
	}
}
