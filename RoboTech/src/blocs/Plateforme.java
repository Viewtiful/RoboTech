package blocs;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * <i>Represente une plateforme mouvante
 * @author Antoine
 *
 */
public class Plateforme extends BlocsDynamiques {

	/**
	 * Les abscisses des points de sa trajectoire
	 */
	float Point_x[];
	/**
	 * Les ordonnées des points de sa trajectoire
	 */
	float Point_y[];
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
	 * 
	 * @param Point_x Abscisses des points à suivre
	 * @param Point_y Ordonnée des points à suivre
	 * @param box_image Image du rendu de la plateforme
	 * @param width Largeur de la plateforme
	 * @param height hauteur de la plateforme
	 */
	
	public Plateforme(float Point_x[], float Point_y[], Image box_image,
			float width, float height) {
		super(box_image, height, width, Point_x[0], Point_y[0]);
		this.Point_x = Point_x;
		this.Point_y = Point_y;
		pointd = 0;
		taille = Point_x.length;
		initialise(0, 1);
	}

	
	public float get_epsilon_x() {
		return epsilon_x;
	}

	public float get_epsilon_y() {
		return epsilon_y;
	}

	/**
	 * Calcule le pas élémentaire de translation horizontal et vertical
	 * @param point_current point origine
	 * @param next_point point destination
	 */
	public void initialise(int point_current, int next_point) {
		assert (point_current > -1 && point_current < taille);
		assert (next_point > -1 && next_point < taille);
		assert (((point_current + 1) == next_point) || ((point_current - 1) == next_point));
		epsilon_x = (Point_x[next_point] - Point_x[point_current]) / 100;
		epsilon_y = (Point_y[next_point] - Point_y[point_current]) / 100;

	}
	/**
	 * Permet de calculer la distance entre deux valeur
	 * @param x1 Valeur x1
	 * @param x2 valeur x2
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
		if (distance(get_x(), Point_x[(pointd + 1) % taille]) < eps
				&& distance(get_y(), Point_y[(pointd + 1) % taille]) < eps) {
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
	/**
	 * Réalise le chemin retour de la plateforme
	 */
	public void retour() {
		float eps = (float) 1e-01;
		if (distance(get_x(), Point_x[(pointd - 1) % taille]) < eps
				&& distance(get_y(), Point_y[(pointd - 1) % taille]) < eps) {
			pointd = (pointd - 1) % taille;
			if (pointd == 0)
				return;
			initialise(pointd, (pointd - 1) % taille);
		}
		set_x(get_x() + epsilon_x);
		set_y(get_y() + epsilon_y);
		getBody().setPosition(get_x(), get_y());

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
		// TODO Auto-generated method stub

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
	 * {@inheritDoc}
	 * <br/>
	 * <b>Comportement :</b><br />
	 * Ici on ne rajoute que le dessin de la  trajectoire que la plateforme va suivre.
	 */
	public void render_spec(Graphics g) {
		g.setColor(Color.gray);
		for (int i = 0; i < taille - 1; i++)
			g.drawLine(Point_x[i], Point_y[i], Point_x[i + 1], Point_y[i + 1]);
		g.setColor(Color.white);
	}
}
