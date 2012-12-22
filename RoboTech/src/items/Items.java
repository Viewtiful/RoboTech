package items;

import org.newdawn.slick.Graphics;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.World;


public abstract class Items {

	protected Body body;
	private World world;
	
	public void setVelocity(float x, float y) {
		Vector2f vec = new Vector2f(body.getVelocity());
		vec.scale(-1);
		body.adjustVelocity(vec);
		body.adjustVelocity(new Vector2f(x,y));
	}

	public void setX(float x) {
		body.setPosition(x, getY());
	}

	public void setY(float y) {
		body.setPosition(getX(), y);
	}
	
	public void setPosition(float x, float y) {
		body.setPosition(x,y);
	}
	
	public float getX() {
		return body.getPosition().getX();
	}

	public float getY() {
		return body.getPosition().getY();
	}

	public float getVelX() {
		return body.getVelocity().getX();
	}

	public float getVelY() {
		return body.getVelocity().getY();
	}

	public Body getBody() {
		return body;
	}

	public void setWorld(World world) {
		this.world = world;
		
	}

	public abstract void render(Graphics g);

	public abstract float getWidth();

	public abstract float getHeight();
	
	public abstract void setPickedUp(boolean b);
	
	public abstract String getNom();
}