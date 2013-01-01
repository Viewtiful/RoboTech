package blocs;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import net.phys2d.raw.Body;

public class Plateforme extends BlocsDynamiques{

	float Point_x[];
	float Point_y[];
	int pointd;
	int taille;
	float epsilon_x;
	float epsilon_y;
	public Plateforme(float Point_x[],float Point_y[],Image box_image,float width,float height,Body body,float x,float y)
	{
		super(box_image, height, width, body,x,y);
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

	public void initialise()
	{
		epsilon_x = (Point_x[0] - Point_x[1])/10;
		epsilon_y = (Point_y[0] - Point_y[1])/10;
	
	}
	public void deplacement()
	{
		
		if(get_x()>=Point_x[(pointd+1)%taille] && get_y()>=Point_y[(pointd+1)%taille])
		{
			pointd = (pointd+1)%taille;
			epsilon_x = (Point_x[(pointd+1)%taille] - Point_x[(pointd)%taille])/10;
			epsilon_y = (Point_y[(pointd+1)%taille] - Point_y[(pointd)%taille])/10;
		}
		else
		{
			set_x(get_x()+epsilon_x);
			set_y(get_y()+epsilon_y);
		}
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
