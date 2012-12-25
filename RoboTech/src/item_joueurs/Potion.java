package item_joueurs;

import items.Items;
import net.phys2d.raw.Body;
import net.phys2d.raw.shapes.Box;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import personnages.Personnage;
import personnages.Robot;

public abstract class Potion extends ItemsRamassable {
	/** The image to display for the crate */
	protected Image image;
	/** The width of the crate */
	private float width;
	/** The height of the crate */
	private float height;

	protected int value;

	public Potion(float x, float y, float width, float height, float mass,
			Robot player, int value) {
		this.width = width;
		this.height = height;

		body = new Body(new Box(width, height), mass);
		body.setPosition(x, y);
		body.setFriction(0.1f);
		set_used(false);
		set_player(player);
		this.value = value;
	}

	public Body getBody() {
		return body;
	}

	public void preUpdate(int delta) {
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
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		int tileX = (int) (getX() / 32);
		int tileY = (int) (getY() / 32);
		float pickupWidth = getWidth() / 32;
		float pickupHeight = getHeight() / 32;
		int playerPosX = (int) (player.getX() / 32);
		int playerPosY = (int) (player.getY() / 32);

		if (playerPosX >= tileX && playerPosX < (tileX + pickupWidth)
				&& playerPosY >= tileY && playerPosY < (tileY + pickupHeight)) {
			used = true;
			effect(player);
			world.remove(getBody());

		}
	}

	public abstract void effect(Robot player);
}
