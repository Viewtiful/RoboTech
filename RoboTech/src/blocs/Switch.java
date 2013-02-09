package blocs;

import java.util.ArrayList;
import java.util.Iterator;

import net.phys2d.math.ROVector2f;

import org.newdawn.slick.Image;

import personnages.Robot;

public class Switch extends BlocsStatiques {

	private ArrayList<Blocs> receiver;

	Image switchOff;

	public Switch(Image box_image, float Width, float Height, Point org,
			Image switch_off) {
		super(box_image, Width, Height, org);
		receiver = new ArrayList<Blocs>();
		this.switchOff = switch_off;
	}

	@Override
	public void collisionAction(Robot player) {
		if (getOnBloc()) {
			System.out.println("Collision Bouton");
			Iterator<Blocs> it = receiver.iterator();
			while (it.hasNext())
				it.next().setSignal(true);
			setImage(switchOff);
		}
	}

	public void add(Blocs b) {
		receiver.add(b);
	}

	@Override
	public String toString() {
		String res = super.toString();
		res = res + "Nombre de recepteur = " + receiver.size();
		res = res + "Liste de Recepteur " + "\n";
		for (int i = 0; i < receiver.size(); i++)
			res = res + "[" + i + "]" + receiver.get(i).toString();
		return res;
	}

}
