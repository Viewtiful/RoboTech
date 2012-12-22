package item_joueurs;

import items.Items;
import net.phys2d.raw.Body;
import net.phys2d.raw.shapes.Box;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Potion extends Items {
	/** The image to display for the crate */
	private Image image;
	/** The width of the crate */
	private float width;
	/** The height of the crate */
	private float height;
	private String nom;

	public Potion(float x, float y, float width, float height, float mass, String nom) throws SlickException {
		this.width = width;
		this.height = height;
		this.nom = nom;
		
		image = new Image("res/" +  this.nom + ".png");
		body = new Body(new Box(width,height), mass);
		body.setPosition(x,y);
		body.setFriction(0.1f);
	}

	public Body getBody() {
		return body;
	}

	public void preUpdate(int delta) {
	}

	public void render(Graphics g) {
		image.draw(getX()-5,getY()-7);
	}
	
	public void setPickedUp(boolean beingPickedUp) {
		if (beingPickedUp) {
			float opacity = 0f;
			image.setAlpha(opacity);
		}
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}
	
	@Override
	public String getNom() {
		return nom;
	}
}
