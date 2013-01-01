package blocs;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import net.phys2d.raw.Body;

public class BlocsStatiques extends Blocs{

	public BlocsStatiques(Image box_image, float width, float height,Body body,float x,float y)
	{
		super(box_image,width,height,body,x,y);
	}
	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
