package personnages;

import jeu.Monde;

import interfaces.Drawable;
import interfaces.SlickAdapter;
import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.CollisionEvent;
import net.phys2d.raw.World;
import net.phys2d.raw.shapes.Box;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Personnage implements Drawable, SlickAdapter {

	// le monde dans lequels le personnage est present
	private World world;

	public Monde monde;
	private boolean directionDroite;
	// retourne true, si le personnage est en train de se deplacer
	private boolean enMouvement;
	// retourne true, si le personnage est en train de tomber dans le vide
	private boolean tombe;
	// retourne true, si l'acteur est au sol
	private boolean auSol;
	// le corps physique qui represente le personnage
	private Body body;
	// le temps durant lequels le personnage a ete considere comme non sur le
	// sol
	private int tempsEnLair;
	// retourne true, si le personnage est en train de sauter
	private boolean saut;
	private int vie;
	private int mana;
	private int energie;
	private float vitesseX;
	private float vitesseY;
	private float maxVelX;
	private float maxVelY;

	// taille du block du personnage pour les collisions
	private float tailleBlockPerso;
	// la velocite sur l'axe des X
	protected float velx;

	// Tableau des balles
	public Personnage(float x, float y, float masse, float tailleBlockPerso,
			Monde monde) {
		this.tailleBlockPerso = tailleBlockPerso;
		this.auSol = false;
		this.directionDroite = true;
		this.enMouvement = false;
		this.tombe = false;
		this.tempsEnLair = 0;
		this.saut = false;
		this.vie = 5;
		this.mana = 5;
		this.energie = 5;
		this.vitesseX = 1;
		this.vitesseY = 1;
		this.maxVelX = 20;
		this.maxVelY = 70;

		// cree le corps du personnage pour le monde physique
		this.body = new Body(new Box(tailleBlockPerso, tailleBlockPerso), masse);
		this.body.setUserData(this);
		this.body.setRestitution(0);
		this.body.setFriction(0f);
		this.body.setMaxVelocity(maxVelX, maxVelY);
		this.body.setRotatable(false);
		this.setPosition(x, y);
		this.monde = monde;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public void applyForce(float x, float y) {
		body.addForce(new Vector2f(x * vitesseX, y));

		// if the force applied is up into the air the actor is
		// considered to be jumping
		if (y < 0) {
			saut = true;
		}

		// if the actor has just changed direction kill the x velocity
		// cause thats what happens in platformers
		if (x > 0) {
			if (!directionDroite) {
				setVelocity(0, getVelY());
			}
			directionDroite = true;
		}
		if (x < 0) {
			if (directionDroite) {
				setVelocity(0, getVelY());
			}
			directionDroite = false;
		}
	}

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

	public void set_coor(float x, float y) {
		body.setPosition(x, y);
	}

	public void setPosition(float x, float y) {
		body.setPosition(x, y);
	}

	public int getVie() {
		return vie;
	}

	public void setVie(int vie) {
		this.vie = vie;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public int getEnergie() {
		return energie;
	}

	public void setEnergie(int energie) {
		this.energie = energie;
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

	public void setEnMouvement(boolean moving) {
		this.enMouvement = moving;
	}

	public Body getBody() {
		return body;
	}

	public boolean getDirectionDroite() {
		return directionDroite;
	}

	public boolean tomber() {
		return tombe;
	}

	public boolean getEnMouvement() {
		return enMouvement;
	}

	public boolean getSaut() {
		return saut;
	}

	public void preUpdate(int delta) {
		// at the start of each frame kill the x velocity
		// if the actor isn't being moved
		if (!enMouvement) {
			setVelocity(0, getVelY());
		}

		// le personnage tombe s'il n'est pas au sol que la velocite sur l'axe
		// des Y et superieur a 0
		tombe = (getVelY() > 0) && (!auSol());
		velx = getVelX();
	}

	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		// update the flag for the actor being on the ground. The
		// physics engine will cause constant tiny bounces as the
		// the body tries to settle - so don't consider the body
		// to have left the ground until it's done so for some time
		boolean vrai = surLeSol(body);
		if (!vrai) {
			tempsEnLair += delta;
			if (tempsEnLair > 100) {
				auSol = false;
			}
		} else {
			tempsEnLair = 0;
			auSol = true;
		}

		// si une collision est apparu sur l'axe des X, on met la velocite sur
		// l'axe des X a 0
		if ((getVelX() > 0) && (!directionDroite)) {
			velx = 0;
		}
		if ((getVelX() < 0) && (directionDroite)) {
			velx = 0;
		}

		// velocite constante pendant les appels successifs de update
		setVelocity(velx, getVelY());

		// si on est au sol, la gravite deviens nulle
		body.setGravityEffected(!vrai);
	}

	public boolean auSol() {
		return auSol;
	}

	public boolean surLeSol(Body body) {
		if (world == null) {
			return false;
		}

		// collision avec le perso est apparu?
		CollisionEvent[] events = world.getContacts(body);

		for (int i = 0; i < events.length; i++) {
			// si le point de la collision est proche des pieds
			if (events[i].getPoint().getY() > getY() + (tailleBlockPerso / 4)) {

				// regarde qu'elle corps est rentre en collision avec quelque
				// chose
				if (events[i].getNormal().getY() < -0.5) {
					// corps B est rentre en collision, est ce notre perso? si
					// oui, retourne on est au sol
					if (events[i].getBodyB() == body) {
						// System.out.println("1" +
						// events[i].getPoint()+","+events[i].getNormal());
						return true;
					}
				}
				// corps B est rentre en collision, est ce notre perso? si oui,
				// retourne on est au sol
				if (events[i].getNormal().getY() > 0.5) {
					if (events[i].getBodyA() == body) {
						// System.out.println("2" +
						// events[i].getPoint()+","+events[i].getNormal());
						return true;
					}
				}
			}
		}

		return false;
	}

	public float getVitesseX() {
		return vitesseX;
	}

	public void setVitesseX(float vitesseX) {
		this.vitesseX = vitesseX;
		maxVelX *= vitesseX;
		body.setMaxVelocity(maxVelX, maxVelY);
	}

	public float getVitesseY() {
		return vitesseY;
	}

	public void setVitesseY(float vitesseY) {
		this.vitesseY = vitesseY;
		maxVelY *= this.vitesseY;
		body.setMaxVelocity(maxVelX, maxVelY);
	}

	public abstract void toucher(int value);

}
