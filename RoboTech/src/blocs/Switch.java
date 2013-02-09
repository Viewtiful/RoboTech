package blocs;

import java.util.ArrayList;
import java.util.Iterator;
import org.newdawn.slick.Image;
import personnages.Robot;
/**
 * Cette classe repr�sente un bouton
 * @author Equipe RoboTech
 *
 */
public class Switch extends BlocsStatiques {

	/**
	 * Liste des �l�ment du jeu influenc� par le comportement de ce bouton
	 */
	private ArrayList<Blocs> receiver;

	public ArrayList<Blocs> getReceiver() {
		return receiver;
	}

	/**
	 * Image du bouton en position off
	 */
	Image switchOff;

	/**
	 * 
	 * @param box_image Image du bouton lorque il est activ�
	 * @param Width Largeur du Bouton
	 * @param Height Hauteur du Bouton
	 * @param org Position du Bouton
	 * @param switch_off Image du Bouton inactiv�
	 */
	public Switch(Image box_image, float Width, float Height, Point org,
			Image switch_off) {
		super(box_image, Width, Height, org);
		receiver = new ArrayList<Blocs>();
		this.switchOff = switch_off;
	}

	@Override
	/**
	 * {@inheritDoc} <br/>
	 * <b>Comportement :</b><br />
	 * Ici on signale au abonn� que quelquechose s'est pass�
	 */
	public void collisionAction(Robot player) {
		if (getOnBloc()) {
			Iterator<Blocs> it = receiver.iterator();
			while (it.hasNext())
				it.next().setSignal(true);
			setImage(switchOff);
		}
	}

	/**
	 * Ajoute des recepteurs
	 * @param b un recepteur
	 */
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
