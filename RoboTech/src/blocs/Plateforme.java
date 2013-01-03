package blocs;

import org.newdawn.slick.GameContainer;
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

	public Plateforme(float Point_x[], float Point_y[], Image box_image,
			float width, float height, float x, float y) {
		super(box_image, height, width, x, y);
		this.Point_x = Point_x;
		this.Point_y = Point_y;
		pointd = 0;
		taille = Point_x.length;
		initialise();
	}

	@Override
	public void signal() {
		set_signal(true);

	}

	public float get_epsilon_x()
	{
		return epsilon_x;
	}
	
	public float get_epsilon_y()
	{
		return epsilon_y;
	}
	public void initialise() {
		epsilon_x = -(Point_x[1] - Point_x[0]) / 100;
		epsilon_y = -(Point_y[1] - Point_y[0]) / 100;
		
	}

	public void deplacement() {

		if(get_x()== Point_x[(pointd+1)%taille] && get_y()== Point_y[(pointd+1)%taille])
		{
			epsilon_x = -epsilon_x;
			epsilon_y = -epsilon_y;
			pointd = (pointd+1)%taille;
		}
		
		else if(get_x()== Point_x[pointd] && get_y()== Point_y[pointd])
		{
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
		deplacement();

	}

}
