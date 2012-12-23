package jeu;

import interfaces.Drawable;
import items.Items;

import java.util.ArrayList;
import java.util.Iterator;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.StaticBody;
import net.phys2d.raw.World;
import net.phys2d.raw.shapes.Box;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import personnages.Personnage;
import weapon.Balle;


public class Monde implements Drawable{
	
	private World world;
	private TiledMap map;
	private ArrayList<Rectangle> obstacles;
	protected ArrayList<Balle> balles;
	
	//liste des personnages
	protected ArrayList<Personnage> personnages;
	
	//liste des items
	protected ArrayList<Items> items;
	
	//liste des items ramassable
	protected ArrayList<Items> itemsRamassable;
	
	public Monde() {
		//initialise les variables de la classe
		world = null;
		map = null;
		obstacles = new ArrayList<Rectangle>();
		personnages = new ArrayList<Personnage>();
		itemsRamassable = new ArrayList<Items>();
		balles = new ArrayList<Balle>();
		items = new ArrayList<Items>();
	}
	
	/**
	 * Fonction qui permet d'initialiser le monde
	 * @throws SlickException
	 */
	public void initialisationMonde() throws SlickException {
		//monde soumis a la physique
		world = new World(new Vector2f(0, 20), 20);
		//chargement de la map (TiledMap)
		map = new TiledMap("res/map.tmx");
		//genere les plateformes (obstacles) du niveau
		generatePlateformes();
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		//affiche la map
		map.render(0, 0);
		
		//affiche les plateformes (obstacles) du niveau
		for (Rectangle obstacle : obstacles)
            g.draw(obstacle); 
		
		//affiche les personnages sur le niveau
		for (int i=0;i<personnages.size();i++) {
			personnages.get(i).render(gc,sbg,g);
		}
		
		for (int i=0;i<items.size();i++) {
			items.get(i).render(gc,sbg,g);
		}
		
		for (int i=0;i<itemsRamassable.size();i++) {
			itemsRamassable.get(i).render(gc,sbg,g);
		}
		
		Iterator<Balle> it = balles.iterator();
		while(it.hasNext())
			it.next().render(gc, sbg, g);
		
	}
	
	public World getWorld() {
		return world;
	}
	
	/**
	 * Fonction qui génére les plateformes (obstacles) du niveau
	 */
	public  void generatePlateformes() {
		
		int largeurMap = map.getWidth();   //recupere la largeur de la map
		int hauteurMap = map.getHeight();  //recupere la hauteur de la map
		int largeurTile = map.getTileWidth();   //recupere la largeur d'un tile (block de la map)
		int hauteurTile = map.getTileHeight();  //recupere la hauteur d'un tile (block de la map)

		//on parcours ligne par ligne
		for (int y = 0; y < hauteurMap; y++) {

			for (int x = 0; x < largeurMap; x++) {

				int largeurPlateformeDessine = 0;  //contient la largeur de la plateforme sur la ligne a dessiner
				int XdepartPlateformeDessine = 0;  //contient le debut de la plateforme sur la ligne

				//si c'est un bloc qui represente une plateforme
				if (map.getTileImage(x, y, map.getLayerIndex("tiles")) != null) {
					XdepartPlateformeDessine = x;  //conserve la coordonnee X du debut de la plateforme
					largeurPlateformeDessine += largeurTile;  //rajoute la taille du tile, a la longueur de la plateforme

					//on teste les prochains tile sur la ligne x, pour savoir s'il appartienne a la meme plateforme a construire
					while ((x + 1) < largeurMap
							&& map.getTileImage(x + 1, y, map
									.getLayerIndex("tiles")) != null) {
						largeurPlateformeDessine += largeurTile;  //a chaque tile rajouter pour construire la plateforme, on rajoute sa taille a la longueur de la plateforme totale
						x++;
					}

					//une fois qu'on a construit notre plateforme, on cree un corps statique sur la map qui represente physiquement la plateforme
					Body body = new StaticBody("StaticBody_" + x + "_" + y,
							new Box(largeurPlateformeDessine, hauteurTile));
					body.setFriction(1f);
					body.setRestitution(1f);
					//positionne la plateforme a sa place sur le niveau
					body.setPosition((XdepartPlateformeDessine * largeurTile + (largeurPlateformeDessine / 2)),
							(y * hauteurTile + (hauteurTile / 2)));
					//rajoute la plateforme au monded physique
					world.add(body);
					
					//dessine les contours de la plateforme sur le niveau, pour vérifier les collisions
					Rectangle obstacle = new Rectangle(XdepartPlateformeDessine * largeurTile, y * hauteurTile, largeurPlateformeDessine, hauteurTile );
					//ajoute le contour de la plateforme a la liste des obstacles
					obstacles.add(obstacle);
				}
			}
		}
	
	}
	
	/**
	 * Fonction qui permet d'ajouter de nouveau personnage au niveau physique
	 * @param pers
	 */
	public void addPersonnages(Personnage pers) {
		world.add(pers.getBody());
		pers.setWorld(world);
		personnages.add(pers);
	}
	
	public void addItemsRamassable(Items item) {
		world.add(item.getBody());
		item.setWorld(world);
		itemsRamassable.add(item);
	}
	
	public void addBalles(Balle balle) {
		world.add(balle.getBody());
		balle.setWorld(world);
		balles.add(balle);
	}
	
	public void addItems(Items caisse) {
		world.add(caisse.getBody());
		caisse.setWorld(world);
		items.add(caisse);
	}

	public void supprimer(Items potionVie) {
		for (int i=0;i<itemsRamassable.size();i++) {
			if(itemsRamassable.get(i).getNom().equals(potionVie.getNom())) {
				world.remove(itemsRamassable.get(i).getBody());
				itemsRamassable.remove(i);
			}
		}
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		Iterator<Personnage> it = personnages.iterator();
		while(it.hasNext())
			it.next().init(container, game);
		
		Iterator<Items> it2 = items.iterator();
		while(it2.hasNext())
			it2.next().init(container, game);
		
		Iterator<Items> it3 = itemsRamassable.iterator();
		while(it3.hasNext())
			it3.next().init(container, game);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		boolean first = true;
			
			//Temps total pour la mise a jour du monde physique
			int tempsTotalMiseAjour = delta;
			//Temps pour la mise a jour du monde physique
			int tempsMiseAjour = 5;
			Iterator<Personnage> it = personnages.iterator();
			//fait entre 2 et 3 tours de boucle pour mettre a jour le monde physique, on fait en premier une preUpdate des personnages, puis une update de ceux-ci
			
			while (tempsTotalMiseAjour > tempsMiseAjour) {
				world.step(tempsMiseAjour * 0.01f);  //mise a jour du monde physique
				tempsTotalMiseAjour -= tempsMiseAjour;
				if (first) {
					first = false;
					for (int i=0;i<personnages.size();i++) {
						personnages.get(i).preUpdate(delta);
					}
				}
				for (int i=0;i<personnages.size();i++) {
					personnages.get(i).update(container,game,tempsMiseAjour);
				}
			}
			for (int i=0;i<balles.size();i++) {
			{
				balles.get(i).update(container, game, delta);
				if(balles.get(i).collision(personnages)) {
					world.remove(balles.get(i).getBody());
					balles.remove(i);
				}
			}

			
	}
	
	
	}

	public void supprimer(Personnage personnage) {
			world.remove(personnage.getBody());
			for (int i=0;i<personnages.size();i++) 
			{
				if(personnages.get(i).getBody() == personnage.getBody()) {
					personnages.remove(i);
				}
			}
//			personnages.remove(i);
		
	}
		
}
