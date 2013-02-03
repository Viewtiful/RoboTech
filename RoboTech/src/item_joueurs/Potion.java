package item_joueurs;

import net.phys2d.raw.Body;
import net.phys2d.raw.shapes.Box;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import personnages.Robot;

/**
 * 
 * @author Antoine Toutes les potions de mani�re g�n�rale
 */
public abstract class Potion extends ItemsRamassable {
	/** L'image de la potion */
	protected Image image;
	/** La largeur de la potion */
	private float width;
	/** La hauteur de la potion */
	private float height;

	/** La masse de la potion */
	private float masse;

	/** La valeur que regénère la potion */
	protected int valeur;

	/**
	 * Constructeur de la classe Potion
	 * 
	 * @param x
	 *            : position en x de la potion
	 * @param y
	 *            : position en y de la potion
	 * @param width
	 *            : largeur de la potion
	 * @param height
	 *            : hauteur de la potion
	 * @param mass
	 *            : masse de la potion
	 * @param player
	 *            : passe en parametre le joueur pour savoir quand il a ramasser
	 *            la potion ....
	 * @param valeur
	 *            : la valeur que modifie la potion
	 */
	public Potion(float x, float y, float width, float height, float mass,
			Robot player, int valeur) {
		this.width = width;
		this.height = height;
		this.masse = mass;

		// Le corps physique de la potion
		body = new Body(new Box(width, height), mass);
		body.setPosition(x, y);
		body.setFriction(0.1f);
		set_used(false);
		set_player(player);
		this.valeur = valeur;
	}

	/**
	 * Retourne le corps physique de la potion
	 */
	public Body getBody() {
		return body;
	}

	public void preUpdate(int delta) {
	}

	/**
	 * Si la potion a ete ramasse, on la fait disparaitre
	 */
	public void setPickedUp(boolean beingPickedUp) {
		if (beingPickedUp) {
			float opacity = 0f;
			image.setAlpha(opacity);
		}
	}

	/**
	 * Récupére la largeur de la potion
	 */
	public float getWidth() {
		return width;
	}

	/**
	 * Récupére la hauteur de la potion
	 */
	public float getHeight() {
		return height;
	}

	/**
	 * Intervale régulière, on vérifie que la potion n'a pas été ramassé,
	 * si c'est le cas, on la donne au joueur et on l'a fait disparaître du
	 * monde
	 */

	public void PickUpItem() {
		int tileX = (int) (getX() / 32);
		int tileY = (int) (getY() / 32);
		float pickupWidth = getWidth() / 32;
		float pickupHeight = getHeight() / 32;
		int playerPosX = (int) (player.getX() / 32);
		int playerPosY = (int) (player.getY() / 32);

		if (playerPosX >= tileX && playerPosX < (tileX + pickupWidth)
				&& playerPosY >= tileY && playerPosY < (tileY + pickupHeight)) {
			used = effect(player);
			if (used)
				world.remove(getBody());
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		PickUpItem();

	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	public void render(Graphics g) {
		image.draw(getX() - 5, getY() - 7);
	}

	/**
	 * {@inheritDoc}
	 */
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		render(g);
	}

	/**
	 * Effet de la potion sur le robot
	 * 
	 * @param player
	 */
	public abstract boolean effect(Robot player);
}
