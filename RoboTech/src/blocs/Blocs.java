package blocs;

import net.phys2d.raw.Body;
import net.phys2d.raw.StaticBody;
import net.phys2d.raw.shapes.Box;

import org.newdawn.slick.Image;

import interfaces.Drawable;

public abstract class Blocs implements Drawable {

	/*
	 * Cette class représente la notion de blocs élementaire
	 * Elle regroupe la partie physique du blocs gérée par Phys2D
	 * Et aussi la partie interface de Slick2d
	 */
	
	/*
	 * Image du Blocs
	 */
	Image box_image;
	/*
	 * Largeur du Bloc
	 */
	private float Width;
	/*
	 * Hauteur du Bloc
	 */
	private float Height;
	/*
	 * Corps Physique du Bloc
	 */
	Body Body;

	/*
	 * Abscisse du centre Bloc
	 */
	private float x;
	/*
	 * Ordonnée du centre du Bloc
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

	/*
	 * @param box_image
	 * @param
	 * @param
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

}
