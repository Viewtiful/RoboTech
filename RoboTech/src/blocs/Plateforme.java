package blocs;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import factory.Point;

import personnages.Robot;

/**
 * <i>Represente une plateforme mouvante</i>
 * 
 * @author Antoine
 * 
 */
public class Plateforme extends BlocsDynamiques {

	

	/**
	 * Les points de sa trajectoire
	 */
	ArrayList<Point> Trajectoire;
	/**
	 * Le dernier point atteint (initialisé à 0 par le constructeur
	 */
	int pointd;
	/**
	 * Taille de la plateforme
	 */
	int taille;
	/**
	 * Le pas de translation horizontal élémentaire pour animer la plateforme
	 */
	float epsilon_x;

	/**
	 * Le pas de translation vertical élémentaire pour animer la plateforme
	 */
	float epsilon_y;
	/**
	 * La plateforme peut faire un déplacement aller-retour ou boucle
	 */
	boolean on_reverse = false;
	/**
	 * En marche arrière
	 */
	boolean reverse = false;
	/**
	 * la vitesse de dépacement de la plateforme
	 */
	private float vitesse;

	/**
	 * 
	 * @param Point_x
	 *            Abscisses des points à suivre
	 * @param Point_y
	 *            Ordonnée des points à suivre
	 * @param box_image
	 *            Image du rendu de la plateforme
	 * @param width
	 *            Largeur de la plateforme
	 * @param height
	 *            hauteur de la plateforme
	 */

	public Plateforme(float Point_x[], float Point_y[], Image box_image,
			float width, float height) {
		super(box_image, height, width, Point_x[0], Point_y[0]);
		Trajectoire = new ArrayList<Point>();
		setTrajectoire(Point_x, Point_y);
		pointd = 0;
		taille = Trajectoire.size();
		vitesse = 1;
		initialise(0, 1);
	}

	public Plateforme(float x, float y, Image box_image, float width,
			float height) {
		super(box_image, height, width, x, y);
		Trajectoire = new ArrayList<Point>();
		pointd = 0;
		vitesse = 1;
		}

	public void setTrajectoire(float Point_x[], float Point_y[]) {
		for (int i = 0; i < Point_x.length; i++)
			Trajectoire.add(new Point(Point_x[i], Point_y[i]));
	}
	
	public void addPoint(float x, float y)
	{
		Trajectoire.add(new Point(x,y));
	}

	public float get_epsilon_x() {
		return epsilon_x;
	}

	public float get_epsilon_y() {
		return epsilon_y;
	}

	public float get_vitesse() {
		return vitesse;
	}

	public void set_vitesse(float vitesse) {
		this.vitesse = vitesse;
	}

	public void set_reverse(boolean reverse)
	{
		on_reverse = reverse;
	}
	/**
	 * Calcule le pas élémentaire de translation horizontal et vertical
	 * 
	 * @param point_current
	 *            point origine
	 * @param next_point
	 *            point destination
	 */
	public void initialise(int point_current, int next_point) {
		float n = vitesse;
		Point current = Trajectoire.get(point_current);
		Point next = Trajectoire.get(next_point);
		epsilon_x = (next.get_x() - current.get_x()) * (n / 100);
		epsilon_y = (next.get_y() - current.get_y()) * (n / 100);

	}

	/**
	 * Permet de calculer la distance entre deux valeur
	 * 
	 * @param x1
	 *            Valeur x1
	 * @param x2
	 *            valeur x2
	 * @return Valeur absolue de la différence
	 */
	public float distance(float x1, float x2) {
		return Math.abs(x2 - x1);
	}

	/**
	 * Réalise le chemin aller de la plateforme
	 */
	public void aller() {
		float eps = (float) 1e-01;
		/*
		 * On s'est rendu compte que on devait utilise un epsilon pour tester
		 */
		if(signal)
		{
			Point next = Trajectoire.get((pointd + 1) % taille);
			if (distance(get_x(), next.get_x()) < eps
					&& distance(get_y(), next.get_y()) < eps) {
				pointd = (pointd + 1) % taille;
				if (pointd == taille - 1)
					return;
				initialise(pointd, (pointd + 1) % taille);
			}
			if (pointd < taille - 1) {
				set_x(get_x() + epsilon_x);
				set_y(get_y() + epsilon_y);
				getBody().setPosition(get_x(), get_y());
			}
		}
	}

	/**
	 * Réalise le chemin retour de la plateforme
	 */
	public void retour() {
		
		if(signal)
		{
			float eps = (float) 1e-01;
			Point previous = Trajectoire.get((pointd - 1) % taille);
			if (distance(get_x(), previous.get_x()) < eps
					&& distance(get_y(), previous.get_y()) < eps) {
				pointd = (pointd - 1) % taille;
				if (pointd == 0)
					return;
				initialise(pointd, (pointd - 1) % taille);
			}
			set_x(get_x() + epsilon_x);
			set_y(get_y() + epsilon_y);
			getBody().setPosition(get_x(), get_y());
		}
	}

	/**
	 * Réalise le déplacement complet de la plateforme
	 */
	public void deplacement() {

		if (pointd == taille - 1) {
			if (on_reverse) {
				reverse = true;
				initialise(pointd, (pointd - 1) % taille);
			}
		} else if (pointd == 0) {
			if (on_reverse) {
				reverse = false;
				initialise(pointd, (pointd + 1) % taille);
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
		taille = Trajectoire.size();
		System.out.println("Size = "+taille);
		System.out.println("Signal = "+signal);
		initialise(0, 1);

	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		deplacement();
	}

	/**
	 * {@inheritDoc} <br/>
	 * <b>Comportement :</b><br />
	 * Ici on ne rajoute que le dessin de la trajectoire que la plateforme va
	 * suivre.
	 */
	public void render_spec(Graphics g) {
		g.setColor(Color.gray);
		Point current;
		Point next;
		for (int i = 0; i < taille - 1; i++) {
			current = Trajectoire.get(i);
			next = Trajectoire.get(i + 1);
			g.drawLine(current.get_x(), current.get_y(), next.get_x(),
					next.get_y());
		}
		g.setColor(Color.white);
	}

	/**
	 * {@inheritDoc} <br/>
	 * <b>Comportement :</b><br />
	 * Ici on ne rajoute que le personnage suit le mouvement du blocs
	 */
	public void collision_action(Robot player) {
		if (get_on_bloc() == true && player.getEnMouvement() == false) {
			player.set_coor(player.getX() + epsilon_x, player.getY()
					+ epsilon_y);
		}
	}
}
