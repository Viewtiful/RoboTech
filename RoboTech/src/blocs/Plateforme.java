package blocs;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import personnages.Robot;

/**
 * <i>Represente une plateforme mouvante</i>
 * 
 * @author Equipe RoboTech
 * 
 */
public class Plateforme extends BlocsDynamiques {

	/**
	 * Les points de sa trajectoire
	 */
	ArrayList<Point> trajectoire;

	/**
	 * Le dernier point atteint (initialis� � 0 par le constructeur
	 */
	int pointd;
	/**
	 * Nombre de point de la Trajectoire
	 */
	int taille;

	/**
	 * Le pas de translation horizontal �l�mentaire pour animer la
	 * plateforme
	 */
	float epsilon_x;

	/**
	 * Le pas de translation vertical �l�mentaire pour animer la plateforme
	 */
	float epsilon_y;
	/**
	 * La plateforme peut faire un d�placement aller-retour ou boucle
	 */
	boolean onReverse = false;
	/**
	 * En marche arri�re
	 */
	boolean reverse = false;
	/**
	 * la vitesse de d�pacement de la plateforme
	 */
	private float vitesse;

	/**
	 * marge d'erreur
	 */
	Point eps;

	/**
	 * Blocs contenus
	 */
	Blocs c = null;
	/**
	 * Affichage de la tajectoire des plateformes
	 */
	boolean trajectoireDraw = true;

	boolean inited = false;

	/**
	 * 
	 * @param Point_x
	 *            Abscisses des points � suivre
	 * @param Point_y
	 *            Ordonn�e des points � suivre
	 * @param box_image
	 *            Image du rendu de la plateforme
	 * @param width
	 *            Largeur de la plateforme
	 * @param height
	 *            hauteur de la plateforme
	 */

	public Plateforme(float Point_x[], float Point_y[], Image box_image,
			float width, float height) {
		super(box_image, height, width, new Point(Point_x[0], Point_y[0]));
		trajectoire = new ArrayList<Point>();
		setTrajectoire(Point_x, Point_y);
		pointd = 0;
		taille = trajectoire.size();
		vitesse = 1;
		eps = new Point((float) 1e-01, (float) 1e-01);
		initialise(0, 1);
	}

	public Plateforme(Point org, Image box_image, float width, float height) {
		super(box_image, width, height, org);
		trajectoire = new ArrayList<Point>();
		pointd = 0;
		vitesse = 1;
		eps = new Point((float) 1e-01, (float) 1e-01);
	}

	public Plateforme(Point org, Blocs c) {
		super(c.getImage(), c.getWidth(), c.getHeight(),org);
		trajectoire = new ArrayList<Point>();
		pointd = 0;
		vitesse = 1;
		eps = new Point((float) 1e-01, (float) 1e-01);
		setBody(c.getBody());
		this.c = c;
	}

	

	public void setTrajectoire(float Point_x[], float Point_y[]) {
		assert (Point_x.length == Point_y.length);
		assert (Point_x.length > 0);

		for (int i = 0; i < Point_x.length; i++)
			trajectoire.add(new Point(Point_x[i], Point_y[i]));
	}

	public ArrayList<Point> getTrajectoire() {
		return trajectoire;
	}

	public void addPoint(float x, float y) {
		int last_size = trajectoire.size();
		trajectoire.add(new Point(x, y));
		assert (last_size + 1 == trajectoire.size());
	}

	public float getEpsilonX() {
		return epsilon_x;
	}

	public float getEpsilonY() {
		return epsilon_y;
	}

	public float getVitesse() {
		return vitesse;
	}

	public void setVitesse(float vitesse) {
		this.vitesse = vitesse;
	}

	public void setReverse(boolean reverse) {
		onReverse = reverse;
	}

	public void setTrajectoireDraw(boolean draw) {
		this.trajectoireDraw = draw;
	}

	/**
	 * Calcule le pas �l�mentaire de translation horizontal et vertical
	 * 
	 * @param point_current
	 *            point origine
	 * @param next_point
	 *            point destination
	 */
	public void initialise(int point_current, int next_point) {
		float n = vitesse;
		assert (n > (float) 1.0e-02); // n diff�r�nt de 0

		Point current = trajectoire.get(point_current);
		Point next = trajectoire.get(next_point);
		epsilon_x = (next.getX() - current.getX()) * (n / 100);
		epsilon_y = (next.getY() - current.getY()) * (n / 100);

	}

	/**
	 * R�alise le chemin aller de la plateforme
	 */
	public void aller() {
		if (signal) {
			Point next = trajectoire.get((pointd + 1) % taille);
			if (center.near(next, eps)) {
				pointd = (pointd + 1) % taille;
				if (pointd == taille - 1)
					return;
				initialise(pointd, (pointd + 1) % taille);
			}
			if (pointd < taille - 1) {
				center.add(epsilon_x, epsilon_y);
				getBody().setPosition(center.getX(), center.getY());
			}
		}
	}

	/**
	 * R�alise le chemin retour de la plateforme
	 */
	public void retour() {

		if (signal) {
			Point previous = trajectoire.get((pointd - 1) % taille);
			if (center.near(previous, eps)) {
				pointd = (pointd - 1) % taille;
				if (pointd == 0)
					return;
				initialise(pointd, (pointd - 1) % taille);
			}
			center.add(epsilon_x, epsilon_y);
			getBody().setPosition(center.getX(), center.getY());
		}
	}

	/**
	 * R�alise le d�placement complet de la plateforme
	 */
	public void deplacement() {
		assert (inited);
		if (pointd == taille - 1) {
			// si on a atteint la fin de la trajectoire
			if (onReverse) {
				reverse = true;
				initialise(pointd, (pointd - 1) % taille);
				// on rebrousse chemin
			}
		} else if (pointd == 0) {
			if (onReverse) {
				reverse = false;
				initialise(pointd, (pointd + 1) % taille);
				// on active le cycle 
			}
		}
		if (!reverse)
			aller();
		else if (reverse)
			retour();
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		taille = trajectoire.size();
		initialise(0, 1);
		inited = true;

	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		assert (inited);
		deplacement();

	}

	/**
	 * {@inheritDoc} <br/>
	 * <b>Comportement :</b><br />
	 * Ici on ne rajoute que le dessin de la trajectoire que la plateforme va
	 * suivre.
	 */
	public void renderSpec(Graphics g) {
		if (trajectoireDraw) {
			g.setColor(Color.gray);
			Point current;
			Point next;
			for (int i = 0; i < taille - 1; i++) {
				current = trajectoire.get(i);
				next = trajectoire.get(i + 1);
				g.drawLine(current.getX(), current.getY(), next.getX(),
						next.getY());
			}
			g.setColor(Color.white);
		}
		
	}

	/**
	 * {@inheritDoc} <br/>
	 * <b>Comportement :</b><br />
	 * Ici on ne rajoute que le personnage suit le mouvement du blocs
	 */
	public void collisionAction(Robot player) {
		if (getOnBloc() == true && player.getEnMouvement() == false && signal) {
			player.setPosition(player.getX() + epsilon_x, player.getY()
					+ epsilon_y);
			if (c != null) {
				System.out.println("Collisionaction");
				c.setOnBloc(true);
				c.collisionAction(player);
			}
		}
	}

	@Override
	public String toString() {
		String res = super.toString();
		res = res + "Point courant = " + pointd + "\n";
		res = res + "Epsilon_x = " + epsilon_x + "\n";
		res = res + "Epsilon_y = " + epsilon_y + "\n";
		res = res + "Vitesse = " + vitesse + "\n";
		res = res + "Aller-Retour = " + onReverse + "\n";
		res = res + "Retour = " + reverse + "\n";
		res = res + "Marge d'erreur = " + eps.toString();
		if (c != null) {
			res = res + "Bloc contenu{" + "\n" + c.toString() + "}" + "\n";
		} else
			res = res + "Aucun Bloc Contenu" + "\n";
		res = res + "Nombre de point Trajectoire = " + trajectoire.size()
				+ "\n";
		res = res + "Trajectoire{" + "\n";
		for (int i = 0; i < trajectoire.size(); i++)
			res = res + "[" + i + "]" + trajectoire.get(i).toString();
		res = res + "}";

		return res;
	}
}
