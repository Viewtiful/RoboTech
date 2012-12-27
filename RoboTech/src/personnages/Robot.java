package personnages;

import listener.RobotHandlers;
import interfaces.Drawable;
import items.Items;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Robot extends Personnage implements Drawable {
	// l'image qui contient le sprite du robot
	private Image image;
	RobotHandlers handlers;

	/**
	 * Create a new Alien actor read to add to the world
	 * 
	 * @param x
	 *            The x position of the alien
	 * @param y
	 *            The y position of the alien
	 * @param mass
	 *            The mass of the alien
	 * @param size
	 *            The size of the alien (collision size)
	 * @throws SlickException
	 *             Indicates a failure to load resources for this alien
	 */
	public Robot(float x, float y, float mass, float size)
			throws SlickException {
		super(x, y, mass, size);
		image = new Image("res/robotx.png");
		handlers = new RobotHandlers();

	}

	/**
	 * @see org.newdawn.penguin.Actor#render(org.newdawn.slick.Graphics)
	 */

	public void ajouterEnergie(int value) {
		setEnergie(getEnergie() + value);

	}

	public void ajouterMana(int value) {
		setMana(getMana() + value);

	}

	public void ajouterVie(int value) {
		setVie(getVie() + value);
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		// dessine l'image du robot en le centrant
		image.drawCentered(getX(), getY());

		// texte de debug
		g.drawString("sol?" + auSol() + "facingRight?" + getDirectionDroite()
				+ " " + getX() + "," + getY() + " " + velx, getX(), getY() - 50);
		g.drawString("Vie : " + getVie() + ", Mana : " + getMana()
				+ ", Energie : " + getEnergie(), getX(), getY() - 70);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		Input input = container.getInput();
		super.update(container, game, delta);
		handlers.handle(input, this);

	}

	@Override
	public void toucher() {
		setVie(getVie() - 1);

	}

	public void modifierVitesseX(int valeurModVitesseX) {
		setVitesseX(valeurModVitesseX);
		
	}

	public void modifierVitesseY(int valeurModVitesseY) {
		setVitesseY(valeurModVitesseY);
		
	}

}
