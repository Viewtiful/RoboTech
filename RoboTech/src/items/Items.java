package items;


import slick.Adapter;
import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.World;

/**
 * Represente les Items(Objet) disponible dans le jeu
 * @author Equipe RoboTech 
 */
public abstract class Items implements Adapter{

	/**
	 * Etat d'utilisation de l'objet
	 */
	protected boolean used;

	/**
	 * Corps Physique de l'objet
	 * 
	 * @see net.phys2d.raw.Body
	 */
	protected Body body;
	/**
	 * Monde physique ou est rattache l'item
	 * 
	 * @see net.phys2d.raw.World
	 */
	protected World world;

	/**
	 * Permet d'affecter une vitesse a un corps Physique
	 * 
	 * @param x
	 *            coordonnee horizontale de la vitesse
	 * @param y
	 *            coordonnee verticale tale de la vitesse
	 * @see net.phys2d.raw.Body
	 */
	public void setVelocity(float x, float y) {
		Vector2f vec = new Vector2f(body.getVelocity());
		vec.scale(-1);
		body.adjustVelocity(vec);
		body.adjustVelocity(new Vector2f(x, y));
	}

	public void setX(float x) {
		body.setPosition(x, getY());
	}

	public void setY(float y) {
		body.setPosition(getX(), y);
	}

	public void setPosition(float x, float y) {
		body.setPosition(x, y);
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

	public abstract float getWidth();

	public abstract float getHeight();

	public abstract void setPickedUp(boolean b);

	public boolean getUsed() {
		return used;
	}
}