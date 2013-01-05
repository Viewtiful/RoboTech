package blocs;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Plateforme extends BlocsDynamiques {

	float Point_x[];
	float Point_y[];
	int pointd;
	int taille;
	float epsilon_x;
	float epsilon_y;
	boolean on_reverse = true;
	boolean reverse = false;

	public Plateforme(float Point_x[], float Point_y[], Image box_image,
			float width, float height, float x, float y) {
		super(box_image, height, width, x, y);
		this.Point_x = Point_x;
		this.Point_y = Point_y;
		pointd = 0;
		taille = Point_x.length;
		initialise(0, 1);
	}

	@Override
	public void signal() {
		set_signal(true);

	}

	public float get_epsilon_x() {
		return epsilon_x;
	}

	public float get_epsilon_y() {
		return epsilon_y;
	}

	public void next_point() {
		if (reverse)
			pointd = (pointd - 1) % taille;
		else
			pointd = (pointd + 1) % taille;
	}

	public void initialise(int point_current, int next_point) {
		assert (point_current > -1 && point_current < taille);
		assert (next_point > -1 && next_point < taille);
		assert (((point_current + 1) == next_point) || ((point_current - 1) == next_point));
		epsilon_x = (Point_x[next_point] - Point_x[point_current]) / 100;
		epsilon_y = (Point_y[next_point] - Point_y[point_current]) / 100;

	}

	public float distance(float x1, float x2) {
		return Math.abs(x2 - x1);
	}

	public void aller() {
		float eps = (float) 1e-01;
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

	public void deplacement() {

		if (get_x() == Point_x[(pointd + 1) % taille]
				&& get_y() == Point_y[(pointd + 1) % taille]) {
			epsilon_x = -epsilon_x;
			epsilon_y = -epsilon_y;
			pointd = (pointd + 1) % taille;
		}

		else if (get_x() == Point_x[pointd] && get_y() == Point_y[pointd]) {
			epsilon_x = -epsilon_x;
			epsilon_y = -epsilon_y;
		}

		set_x(get_x() + epsilon_x);
		set_y(get_y() + epsilon_y);
		getBody().setPosition(get_x(), get_y());

	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {

		System.out.println("Vitesse_x = " + (epsilon_x * 60));
		System.out.println("Vitesse_y = " + (epsilon_y * 60));
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

	public void render_spec(Graphics g) {
		g.setColor(Color.gray);
		for (int i = 0; i < taille - 1; i++)
			g.drawLine(Point_x[i], Point_y[i], Point_x[i + 1], Point_y[i + 1]);
		g.setColor(Color.white);
	}
}
