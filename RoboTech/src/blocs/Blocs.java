package blocs;

import net.phys2d.raw.Body;

import org.newdawn.slick.Image;

import interfaces.Drawable;

public abstract class Blocs implements Drawable {

	Image box_image;
	private float Width;
	private float Height;
	Body Body;

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
}
