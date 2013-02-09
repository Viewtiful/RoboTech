package factory;

import net.phys2d.raw.World;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import blocs.Point;

public abstract class AbstractFactory {

	protected World world;
	protected TiledMap map;

	public AbstractFactory(World world, TiledMap map) {
		this.world = world;
		this.map = map;
	}

	public String recupererPropriete(int i, int j, String Tag) {
		return map.getObjectProperty(i, j, Tag, "NOT_FOUND");
	}

	public Point getPosition(int i, int j) {
		return new Point(map.getObjectX(i, j), map.getObjectY(i, j));
	}

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

	public int getWidth(int i, int j) {
		return map.getObjectWidth(i, j);
	}

	public int getHeight(int i, int j) {
		return map.getObjectHeight(i, j);
	}

	public Point getDimension(int i, int j) {
		return new Point(getWidth(i, j), getHeight(i, j));
	}

	public String getName(int i, int j) {
		return map.getObjectName(i, j);
	}

	@Override
	public String toString() {
		return "Liste de Production" + "\n";
	}
}
