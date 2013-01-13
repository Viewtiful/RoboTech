package jeu;

import interfaces.SlickAdapter;
import items.Items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.StaticBody;
import net.phys2d.raw.World;
import net.phys2d.raw.shapes.Box;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import blocs.Blocs;
import blocs.BlocsBlessant;
import blocs.Plateforme;

import personnages.Personnage;
import personnages.Robot;
import weapon.Balle;

/*
 * Repr�sente le niveau o� le Robot �volue
 */
public class Monde implements SlickAdapter {

	// Repr�sente le monde physique
	private World world;

	// Repr�sente le niveau
	private TiledMap niveau;

	// Les Obstacles
	private ArrayList<Rectangle> obstacles;

	// Les Balles pr�sentes dans le niveau
	protected ArrayList<Balle> balles;

	/*
	 * Les personnages �voluant dans le niveau La structure nous permet un gain
	 * de performance dans la recherche de donn�e
	 */
	protected HashMap<Body, Personnage> personnages;

	// Les Items
	protected ArrayList<Items> items;

	// Les Items qui peuvent �tre rammaser
	protected ArrayList<Items> itemsRamassable;

	protected ArrayList<Blocs> interaction;
	// Plateforme test
	public Plateforme p;

	// Le robot contr�l� par le Joueur
	Robot player;

	Animation courir;

	private BlocsBlessant bb;

	//
	/**
	 * Constructeur de la classe Monde
	 */
	public Monde() {
		// initialise les variables de la classe
		world = null;
		niveau = null;
		obstacles = new ArrayList<Rectangle>();
		personnages = new HashMap<Body, Personnage>();
		itemsRamassable = new ArrayList<Items>();
		balles = new ArrayList<Balle>();
		items = new ArrayList<Items>();
		interaction = new ArrayList<Blocs>();
		float[] Point_x = { 500f, 800f, 800f, 500f, 500f };
		float[] Point_y = { 500f, 500f, 550f, 550f, 500f };
		Image i = null;
		try {
			i = new Image("res/caisse2.png");
		} catch (SlickException e) {
			System.out.println("SlickException");
		}
		p = new Plateforme(Point_x, Point_y, i, i.getHeight(), i.getWidth());
		try {
			i = new Image("res/pics.png");
		} catch (SlickException e) {
			System.out.println("SlickException");
		}
		bb = new BlocsBlessant(i, i.getWidth(), i.getHeight(), 800, 570, 20);
		interaction.add(p);
		interaction.add(bb);
		SpriteSheet hero = null;

	}

	public void setPlayer(Robot player) {
		this.player = player;
	}

	/**
	 * Fonction qui permet d'initialiser le niveau
	 * 
	 * @throws SlickException
	 */
	public void initialisationMonde() throws SlickException {
		// monde soumis a la physique
		world = new World(new Vector2f(0, 20), 20);
		// chargement de la map (TiledMap)
		niveau = new TiledMap("res/map.tmx");
		// genere les plateformes (obstacles) du niveau
		generatePlateformes();
		world.add(p.getBody());
		world.add(bb.getBody());

	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		// affiche la map
		niveau.render(0, 0);

		// affiche les plateformes (obstacles) du niveau
		for (Rectangle obstacle : obstacles)
			g.draw(obstacle);
		Iterator<Blocs> it4 = interaction.iterator();
		while (it4.hasNext())
			it4.next().render(g);
		// affiche les personnages sur le niveau
		Iterator<Personnage> it = personnages.values().iterator();
		while (it.hasNext())
			it.next().render(gc, sbg, g);

		// affiche les items non ramassable sur le niveau
		Iterator<Items> it2 = items.iterator();
		while (it2.hasNext())
			it2.next().render(gc, sbg, g);

		// affiche les items ramassables sur le niveau
		it2 = itemsRamassable.iterator();
		while (it2.hasNext())
			it2.next().render(gc, sbg, g);

		// affiche les balles tirees sur le niveau
		Iterator<Balle> it3 = balles.iterator();
		while (it3.hasNext())
			it3.next().render(gc, sbg, g);

		courir.draw(800, 800);
	}

	/**
	 * Retourne le monde physique du niveau
	 * 
	 * @return
	 */
	public World getWorld() {
		return world;
	}

	/**
	 * Fonction qui génére les plateformes (obstacles) du niveau
	 */
	public void generatePlateformes() {

		int largeurMap = niveau.getWidth(); // recupere la largeur de la map
		int hauteurMap = niveau.getHeight(); // recupere la hauteur de la map
		int largeurTile = niveau.getTileWidth(); // recupere la largeur d'un
													// tile
													// (block de la map)
		int hauteurTile = niveau.getTileHeight(); // recupere la hauteur d'un
													// tile
													// (block de la map)

		// on parcours ligne par ligne
		for (int y = 0; y < hauteurMap; y++) {

			for (int x = 0; x < largeurMap; x++) {

				int largeurPlateformeDessine = 0; // contient la largeur de la
													// plateforme sur la ligne a
													// dessiner
				int XdepartPlateformeDessine = 0; // contient le debut de la
													// plateforme sur la ligne

				// si c'est un bloc qui represente une plateforme
				if (niveau.getTileImage(x, y,
						niveau.getLayerIndex("BlocsStatiques")) != null) {
					XdepartPlateformeDessine = x; // conserve la coordonnee X du
													// debut de la plateforme
					largeurPlateformeDessine += largeurTile; // rajoute la
																// taille du
																// tile, a la
																// longueur de
																// la plateforme

					// on teste les prochains tile sur la ligne x, pour savoir
					// s'il appartienne a la meme plateforme a construire
					while ((x + 1) < largeurMap
							&& niveau.getTileImage(x + 1, y,
									niveau.getLayerIndex("BlocsStatiques")) != null) {
						largeurPlateformeDessine += largeurTile; // a chaque
																	// tile
																	// rajouter
																	// pour
																	// construire
																	// la
																	// plateforme,
																	// on
																	// rajoute
																	// sa taille
																	// a la
																	// longueur
																	// de la
																	// plateforme
																	// totale
						x++;
					}

					// une fois qu'on a construit notre plateforme, on cree un
					// corps statique sur la map qui represente physiquement la
					// plateforme
					Body body = new StaticBody("StaticBody_" + x + "_" + y,
							new Box(largeurPlateformeDessine, hauteurTile));
					body.setFriction(1f);
					body.setRestitution(1f);
					// positionne la plateforme a sa place sur le niveau
					body.setPosition(
							(XdepartPlateformeDessine * largeurTile + (largeurPlateformeDessine / 2)),
							(y * hauteurTile + (hauteurTile / 2)));
					// rajoute la plateforme au monded physique
					world.add(body);

					// dessine les contours de la plateforme sur le niveau, pour
					// vérifier les collisions
					Rectangle obstacle = new Rectangle(XdepartPlateformeDessine
							* largeurTile, y * hauteurTile,
							largeurPlateformeDessine, hauteurTile);
					// ajoute le contour de la plateforme a la liste des
					// obstacles
					obstacles.add(obstacle);
				}
			}
		}

	}

	/**
	 * Fonction qui permet d'ajouter de nouveau personnage au niveau physique
	 * 
	 * @param pers
	 *            à ajouter au niveau physique
	 */
	public void addPersonnages(Personnage pers) {
		world.add(pers.getBody());
		pers.setWorld(world);
		personnages.put(pers.getBody(), pers);
	}

	/**
	 * Fonction qui permet d'ajouter de nouveau items ramassable au niveau
	 * physique
	 * 
	 * @param item
	 *            ramassable à ajouter au niveau
	 */
	public void addItemsRamassable(Items item) {
		world.add(item.getBody());
		item.setWorld(world);
		itemsRamassable.add(item);
	}

	/**
	 * Fonction qui permet d'ajouter de nouvelle balles au niveau physique
	 * 
	 * @param balle
	 *            à ajouter au niveau
	 */
	public void addBalles(Balle balle) {
		world.add(balle.getBody());
		balle.setWorld(world);
		balles.add(balle);
	}

	/**
	 * Fonction qui permet d'ajouter de nouveau items non ramassable au niveau
	 * physique
	 * 
	 * @param item
	 *            à ajouter au niveau
	 */
	public void addItems(Items item) {
		world.add(item.getBody());
		item.setWorld(world);
		items.add(item);
	}

	/**
	 * 
	 * Initialise les différents élements du niveau
	 * 
	 * @param container
	 * @param game
	 * @param delta
	 * @throws SlickException
	 */
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {

		Iterator<Personnage> it = personnages.values().iterator();
		while (it.hasNext())
			it.next().init(container, game);

		Iterator<Items> it2 = items.iterator();
		while (it2.hasNext())
			it2.next().init(container, game);

		Iterator<Items> it3 = itemsRamassable.iterator();
		while (it3.hasNext())
			it3.next().init(container, game);

	}

	/**
	 * Met à jour les items ramassés du niveau, vérification à chaque tour
	 * 
	 * @param container
	 * @param game
	 * @param delta
	 * @throws SlickException
	 */
	public void update_item(GameContainer container, StateBasedGame game,
			int delta) throws SlickException {
		Items current;
		Iterator<Items> it = itemsRamassable.iterator();

		while (it.hasNext()) {
			current = it.next();
			current.update(container, game, delta);
			if (current.get_used())
				it.remove();
		}

	}

	/**
	 * Met à jour les personnages du niveau qui sont mort, vérification à
	 * chaque tour
	 * 
	 * @param container
	 * @param game
	 * @param delta
	 */
	public void update_personnage(GameContainer container, StateBasedGame game,
			int delta) {
		Personnage current;
		Iterator<Personnage> it = personnages.values().iterator();
		while (it.hasNext()) {
			current = it.next();
			if (current.getVie() == 0) {
				world.remove(current.getBody());
				it.remove();
			}
		}
	}

	/**
	 * Met à jour les balles du niveau qui ont touchés un element du niveau
	 * pour les détruires, vérification à chaque tour
	 * 
	 * @param container
	 * @param game
	 * @param delta
	 * @throws SlickException
	 */
	public void update_balle(GameContainer container, StateBasedGame game,
			int delta) throws SlickException {
		Balle current;
		Iterator<Balle> it = balles.iterator();
		while (it.hasNext()) {
			current = it.next();
			current.update(container, game, delta);
			if (current.collision(personnages)) {
				world.remove(current.getBody());
				it.remove();
			}
		}

	}

	/**
	 * Met � jour les blocs interagissant avec le player
	 * 
	 * @param container
	 * @param game
	 * @param delta
	 */
	public void update_Blocs(GameContainer container, StateBasedGame game,
			int delta) {
		Iterator<Blocs> it = interaction.iterator();
		Blocs current;
		while (it.hasNext()) {
			current = it.next();
			current.collision(player);
			try {
				current.update(container, game, delta);
			} catch (SlickException e) {
				System.out.println("SlickException");
			}
		}
	}

	/**
	 * Met à jour le niveau à chaque tour
	 */
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		boolean first = true;

		// Temps total pour la mise a jour du monde physique
		int tempsTotalMiseAjour = delta;
		// Temps pour la mise a jour du monde physique
		int tempsMiseAjour = 5;
		Iterator<Personnage> it = personnages.values().iterator();

		// fait entre 2 et 3 tours de boucle pour mettre a jour le monde
		// physique, on fait en premier une preUpdate des personnages, puis une
		// update de ceux-ci

		while (tempsTotalMiseAjour > tempsMiseAjour) {
			world.step(tempsMiseAjour * 0.01f); // mise a jour du monde physique
			tempsTotalMiseAjour -= tempsMiseAjour;
			if (first) {
				first = false;
				while (it.hasNext())
					it.next().preUpdate(delta);
			}
			it = personnages.values().iterator();
			while (it.hasNext())
				it.next().update(container, game, tempsMiseAjour);

		}

		update_personnage(container, game, delta);
		update_balle(container, game, delta);
		update_item(container, game, delta);
		update_Blocs(container, game, delta);
	}

}
