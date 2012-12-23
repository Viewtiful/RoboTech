package weapon;

import java.util.ArrayList;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.CollisionEvent;
import net.phys2d.raw.World;
import net.phys2d.raw.shapes.Box;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import personnages.Personnage;

import interfaces.Drawable;
import items.Items;

public class Balle implements Drawable{

	private float x;
	private float y;
	private Body body;
	private float masse;
	private World world;
	Image skin;
	private boolean directionDroite;
	private float velx;
	
	public Balle(float x,float y, Boolean directionDroite, float masse) throws SlickException
	{
		this.directionDroite = directionDroite;
		if(this.directionDroite)
			this.x = x + 30;
		else 
			this.x = x - 30;		
		this.y = y;
		System.out.println("y" + this.y);
		skin = new Image("res/bullet.png");
		this.masse = masse;
		//cree le corps du personnage pour le monde physique
		this.body = new Body(new Box(10, 10), masse);
		this.body.setUserData(this);
		this.body.setRestitution(0);
		this.body.setFriction(0f);
		this.body.setMaxVelocity(50, 50);
		this.body.setRotatable(false);
		this.setPosition(this.x,this.y);

		
	}
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		skin = new Image("res/bullet.png");
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		skin.drawCentered(getX(), getY());
		
	}
	
	public Body getBody() {
		return body;
	}
	
	public float getX() {
		return body.getPosition().getX();
	}

	public float getY() {
		return body.getPosition().getY();
	}
	
	public void applyForce(float x, float y) {
		if(directionDroite)
			body.addForce(new Vector2f(x,0));
		else
			body.addForce(new Vector2f(-x,0));
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		velx = getVelX();
		//si une collision est apparu sur l'axe des X, on met la velocite sur l'axe des X a 0
		if ((getVelX() > 0) && (!directionDroite)) {
			velx = 0;
		}
		if ((getVelX() < 0) && (directionDroite)) {
			velx = 0;
		}
		body.setGravityEffected(false);
		//velocite constante pendant les appels successifs de update
		setVelocity(velx, getVelY());
}

	public boolean collision(ArrayList<Personnage> personnages) {
		if (world == null) {
			return false;
		}
		
		// collision avec le perso est apparu?
		CollisionEvent[] events = world.getContacts(body);
		for (int i=0;i<events.length;i++) {

			//regarde qu'elle corps est rentre en collision avec quelque chose
			if (events[i].getNormal().getX() < -0.5) {
				// corps B est rentre en collision, est ce notre perso? si oui, retourne on est au sol
				if (events[i].getBodyB() == body) {
//					System.out.println("1" + events[i].getPoint()+","+events[i].getNormal());
					System.out.println("Collision");
					for (int e=0;e<personnages.size();e++) {
						if(events[i].getBodyA() == personnages.get(e).getBody()) {
							System.out.println("toucher ennemi");
							personnages.get(e).toucher();
						}
					}
					return true;
				}
			}
			// corps B est rentre en collision, est ce notre perso? si oui, retourne on est au sol
			if (events[i].getNormal().getX() > 0.5) {
				if (events[i].getBodyB() == body) {
					System.out.println("Collision2");
					for (int e=0;e<personnages.size();e++) {
						if(events[i].getBodyA() == personnages.get(e).getBody()) {
							System.out.println("toucher ennemi");
							personnages.get(e).toucher();
						}
					}
					return true;
				}
			}
		}
		
		return false;
	}
	
	
	public float getWidth() {
		return 10;
	}
	
	public float getHeight() {
		return 10;
	}
	
	public void setVelocity(float x, float y) {
		Vector2f vec = new Vector2f(body.getVelocity());
		vec.scale(-1);
		body.adjustVelocity(vec);
		body.adjustVelocity(new Vector2f(x,y));
	}
	
	public float getVelX() {
		return body.getVelocity().getX();
	}
	
	public float getVelY() {
		return body.getVelocity().getY();
	}
	
	public void setWorld(World world) {
		this.world = world;
	}
	
	public void setPosition(float x, float y) {
		body.setPosition(x,y);
	}

}
