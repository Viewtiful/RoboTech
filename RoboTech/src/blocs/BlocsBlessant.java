package blocs;

import org.newdawn.slick.Image;

import personnages.Robot;

/**
 * 
 * Blocs qui blessent un joueur, comme les Pics ou la lave
 * 
 * @author Equipe RoboTech
 */
public class BlocsBlessant extends BlocsStatiques {

	/**
	 * la valeur des d�g�ts inflig� par le contact du blocs avec le Robot
	 */
	private int valeur;

	/**
	 * 
	 * @param box_image
	 *            Image du bloc
	 * @param Width
	 *            Largeur du bloc
	 * @param Height
	 *            hauteur du bloc
	 * @param x
	 *            position horizontale
	 * @param y
	 *            position vertciale
	 * @param valeur
	 *            valeur des d�g�ts
	 */
	public BlocsBlessant(Image box_image, float Width, float Height, Point org,
			int valeur) {
		super(box_image, Width, Height, org);
		this.valeur = valeur;
	}

	
	
	public int get_valeur() {
		return valeur;
	}

	public void set_valeur(int valeur) {
		this.valeur = valeur;
	}

	/**
	 * {@inheritDoc} <br/>
	 * <b>Comportement :</b><br />
	 * Ici on ne rajoute que le Robot perd de la vie si il est en contact avec
	 * ce Blocs
	 */
	public void collision_action(Robot player) {
		player.setVie(player.getVie() - valeur);
	}

}
