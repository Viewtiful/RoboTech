package blocs;

import net.phys2d.raw.Body;

import org.newdawn.slick.Image;

import interfaces.Drawable;

public abstract class Blocs implements Drawable {

	Image box_image;
	private float Width;
	private float Height;
	Body Body;

	private float x;
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
	
	public void set_x(float x)
	{
		this.x = x;
	}
	
	public float get_x()
	{
		return x;
	}
	
	public void set_y(float y)
	{
		this.y = y;
	}
	
	public float get_y()
	{
		return y;
	}
	public Blocs(Image box_image,float Width,float Height,Body Body,float x,float y)
	{
		this.box_image = box_image;
		this.Width = Width;
		this.Height = Height;
		this.Body = Body;
		this.x = x;
		this.y = y;
	}
}
