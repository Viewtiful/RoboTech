package blocs;

import org.newdawn.slick.Image;

import personnages.Robot;

public class Capteur extends Switch{

	private boolean vertical = false;
	public Capteur(Image box_image, float Width, float Height, Point org,
			Image switch_off) {
		super(box_image, Width, Height, org, box_image);
	}

	public void collision(Robot player) {

		float x = player.getX();
		float y = player.getY();
		float debut_x = center.getX() - getWidth()/2;
		float fin_x = center.getX() + getWidth()/2;
		float debut_y = center.getY() - getHeight()/2;
		float fin_y = center.getY() + getHeight()/2;
		
			if(vertical && debut_y <=y && y>=fin_y)
				collisionAction(player);
			
			if(!vertical && debut_x <=x && x>=fin_x)
				collisionAction(player);
			
		}
	}
