package blocs;

import net.phys2d.raw.Body;
import net.phys2d.raw.StaticBody;
import net.phys2d.raw.shapes.Box;

import org.newdawn.slick.Image;

import personnages.Robot;

import interfaces.Drawable;
import interfaces.SlickAdapter;

/**
 * <i> Elle synth�tise l'aspect visuel et physique d'un bloc</i>
 * 
 * 
 * 
 **/
public abstract class Blocs implements Drawable,SlickAdapter {

	/**
	 * Image du Blocs
	 */
	Image box_image;
	/**
	 * Largeur du Bloc
	 */
	private float Width;
	/**
	 * Hauteur du Bloc
	 */
	private float Height;
	/**
	 * Corps Physique du Bloc
	 */
	Body Body;

	/**
	 * Abscisse du centre Bloc
	 */
	private float x;
	/**
	 * Ordonn�e du centre du Bloc
	 */
	private float y;

	public void setWidth(float Width) {
		this.Width = Width;
	}

	public void setHeight(float Height) {
		this.Height = Height;
	}

	public float getWidth() {
		return Width;
	}

	public float getHeight() {
		return Height;
	}

	public Body getBody() {
		return Body;
	}

	public void set_x(float x) {
		this.x = x;
	}

	public float get_x() {
		return x;
	}

	public void set_y(float y) {
		this.y = y;
	}

	public float get_y() {
		return y;
	}

	public Image get_image() {
		return box_image;
	}

	private boolean on_bloc = false;
	
	/**
	 * @param box_image
	 *            Image du rendu du blocs
	 * @param Width
	 *            Largeur du Bloc
	 * @param Height
	 *            Hauteur du Bloc
	 * @param x
	 *            Abscisse du Bloc
	 * @param y
	 *            Ordonn�e du Bloc
	 */
	public Blocs(Image box_image, float Width, float Height, float x, float y) {
		this.box_image = box_image;
		this.Width = Width;
		this.Height = Height;
		this.Body = new StaticBody("StaticBody_" + x + "_" + y, new Box(Width,
				Height));
		this.x = x;
		this.y = y;
		Body.setPosition(x, y);
	}
	
	public void set_on_bloc(boolean value)
	{
		on_bloc = value;
	}
	
	public boolean get_on_bloc()
	{
		return on_bloc;
	}
	/**
	 * Permet de d�terminer si il y a contact avec le robot et le blocs
	 * @param player le Robot contr�l� par le joueur
	 */
	public void collision(Robot player)
	{
		float eps = (float) 1e-01;
		float n = player.get_taille();
		if (Math.abs((player.getY() + n / 2)
				- (get_y() - getHeight() / 2)) < eps) {
			float x_gauche = player.getX() - n / 2;
			float x_droite = player.getX() + n / 2;

			float p_gauche = get_x() - getWidth() / 2;
			float p_droite = get_x() + getWidth() / 2;

			if ((x_droite <= p_droite && x_droite >= p_gauche)
					|| (x_gauche <= p_droite && x_gauche >= p_gauche)) {
				on_bloc = true;
			}
			collision_action(player);
		}
	}
	/**
	 * Permet que les classes filles ont un comportement diff�rents lors d'une collision
	 * @param player le Robot contr�l� par le joueur
	 */
	public abstract void collision_action(Robot player);
	

}
