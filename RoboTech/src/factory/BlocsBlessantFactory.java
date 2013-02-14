package factory;

import java.util.ArrayList;
import java.util.Collection;
import net.phys2d.raw.World;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import blocs.BlocsBlessant;
import blocs.Point;

/**
 * Fabrique de blocBlessant, blocs qui blessent le joueur
 * @author Equipe RoboTech
 *
 */
public class BlocsBlessantFactory extends AbstractFactory {

	/**
	 * BlocsBlessant en cours de creation
	 *
	 */
	private ArrayList<BlocsBlessant> chaine;

	
	/**
	 * Cree une fabrique
	 * @param world Monde Physique
	 * @param map Carte du niveau
	 */
	public BlocsBlessantFactory(World world, TiledMap map) {
		super(world, map);
		chaine = new ArrayList<BlocsBlessant>();
	}


	/**
	 * Retour la totalité des blocBlessant produit
	 * @return l'ensemble des blocsBlessant produit
	 */
	public Collection<BlocsBlessant> getProduit() {
		return chaine;
	}

	/**
	 * Cree un nouveau BlocBlessant et l'integre dans l'environnement de jeu
	 * @param i Abscisse de l'objet dans la carte
	 * @param j Ordonnee de l'objet dans la carte
	 */
	public void createBlocsBlessant(int i, int j) throws SlickException {

		int last_size = chaine.size();

		Point p = getPosition(i, j);
		assert(p!=null);
		
		Point dim = getDimension(i, j);
		assert(dim!=null);
		
		p.add(dim.getX() / 2, dim.getY() / 2);
		String image = recupererPropriete(i,j,"Image");
		Image im;
		if(!image.equals("NOT_FOUND"))
			im = new Image(image);
		else
			im = new Image("res/pics.png");
		
		BlocsBlessant b = new BlocsBlessant(im, dim.getX(), dim.getY(), p, 1);
		chaine.add(b);
		assert(chaine.size()==last_size+1);
		world.add(b.getBody());
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
