package blocs;


import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.Image;

import personnages.Robot;

public class Switch extends BlocsStatiques {

	private ArrayList<Blocs> receiver;
	public Switch(Image box_image, float Width, float Height, float x, float y)
	{
		super(box_image, Width, Height, x, y);
		receiver = new ArrayList<Blocs>();
	}

	@Override
	public void collision_action(Robot player) {
		Iterator<Blocs> it = receiver.iterator();
		while(it.hasNext())
			it.next().set_signal(true);
	}
	
	public void add(Blocs b)
	{
		receiver.add(b);
	}
	

}
