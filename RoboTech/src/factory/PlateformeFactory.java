package factory;

import java.util.Collection;
import java.util.HashMap;

import net.phys2d.raw.World;

import org.newdawn.slick.Image;
import org.newdawn.slick.tiled.TiledMap;

import blocs.Blocs;
import blocs.Plateforme;
import blocs.Point;

/**
 * 
 * @author Antoine
 * Cr�ateur de blocs
 */
public class PlateformeFactory extends AbstractFactory{

	private HashMap<String, Plateforme> chaine;
	
	public PlateformeFactory(World world,TiledMap map)
	{
		super(world,map);
		chaine = new HashMap<String,Plateforme>();
	}
	
	public void AddPosition(int i,int j)
	{
		String name = map.getObjectName(i, j);
		Plateforme p = get_produit(name);
		Point position = get_Position(i,j);
		int width = map.getObjectWidth(i, j);
		int height = map.getObjectHeight(i, j);
		p.addPoint(position.get_x()+width/2, position.get_y()+height/2);
	}
	
	private Plateforme get_produit(String name) {
		return chaine.get(name);
	}

	public Collection<Plateforme> get_produit()
	{
		return chaine.values();
	}
	
	public void CreatePlateforme(int i, int j)
	{
		Point position = get_Position(i,j);
		int width = getWidth(i,j);
		int height = getHeight(i,j);
		position.add(width/2,height/2);
		Image image_box = getImage(i,j);
		boolean signal = Boolean.parseBoolean(recuperer_Propriete(i,j,"Signal"));
		boolean reverse = Boolean.parseBoolean(recuperer_Propriete(i,j,"Reverse"));
		String name = get_name(i,j);
		Plateforme p = new Plateforme(position,image_box,width,height);
		p.addPoint(position.get_x(), position.get_y());
		p.set_signal(signal);
		p.set_reverse(reverse);
		world.add(p.getBody());
		chaine.put(name,p);
	}
	
	
}
