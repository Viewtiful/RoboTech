package blocs;

import java.util.ArrayList;
import java.util.Iterator;

import net.phys2d.math.ROVector2f;

import org.newdawn.slick.Image;

import personnages.Robot;

public class Switch extends BlocsStatiques {

	private ArrayList<Blocs> receiver;

	Image switch_off;

	public Switch(Image box_image, float Width, float Height, Point org,
			Image switch_off) {
		super(box_image, Width, Height, org);
		receiver = new ArrayList<Blocs>();
		this.switch_off = switch_off;
	}

	@Override
	public void collision_action(Robot player) {
		if(get_on_bloc())
		{
			Iterator<Blocs> it = receiver.iterator();
			while (it.hasNext())
				it.next().set_signal(true);
			set_image(switch_off);
		}
	}

	public void add(Blocs b) {
		receiver.add(b);
	}

}
