package factory;

import net.phys2d.raw.World;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import blocs.Point;

public abstract class AbstractFactory {

	
	protected World world;
	protected TiledMap map;
	
	
	
	public AbstractFactory(World world,TiledMap map)
	{
		this.world = world;
		this.map = map;
	}
	
	public String recuperer_Propriete(int i,int j, String Tag)
	{
		return map.getObjectProperty(i, j, Tag, "NOT_FOUND"); 	
	}
	
	public Point get_Position(int i,int j)
	{
		return new Point(map.getObjectX(i, j),map.getObjectY(i, j));
	}
	
	public Image getImage(int i,int j,String Tag)
	{
		String Image = recuperer_Propriete(i,j,Tag);
		assert(!Image.equals("NOT_FOUND"));
		Image image_box = null;
		try {
			image_box = new Image(Image);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		return image_box;
	}
	
	public int getWidth(int i,int j)
	{
		return map.getObjectWidth(i, j);		
	}
	
	public int getHeight(int i,int j)
	{
		return map.getObjectHeight(i, j);
	}
	
	public Point getDimension(int i,int j)
	{
		return new Point(getWidth(i,j),getHeight(i,j));
	}
	public String get_name(int i,int j)
	{
		return map.getObjectName(i, j);
	}	
	
	
}

