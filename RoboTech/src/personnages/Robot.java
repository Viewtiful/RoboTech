package personnages;

import jeu.Monde;

import listener.RobotHandlers;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import blocs.Plateforme;

import weapon.Balle;
import weapon.BalleRobot;

public class Robot extends Personnage {
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
	 **/
	public Robot(float x, float y, float mass, float size, Monde monde)
			throws SlickException {
		super(x, y, mass, size, monde);
		
		image = new Image("res/robotBleu.png");
		handlers = new RobotHandlers();

	}
	
	public void setImage(String robot) throws SlickException {
		image = new Image("res/"+ robot + ".png");
	}

	public Image getImage()
	{
		return image;
	}
	public void set_monde(Monde monde) {
		this.monde = monde;
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

	public void render(Graphics g) {
		// dessine l'image du robot en le centrant
		image.drawCentered(getX(), getY());

		// texte de debug
		g.drawString("sol?" + auSol() + "facingRight?" + getDirectionDroite()
				+ " " + getX() + "," + getY() + " " + velx, getX(), getY() - 50);
		g.drawString("Vie : " + getVie() + ", Mana : " + getMana()
				+ ", Energie : " + getEnergie(), getX(), getY() - 70);

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		render(g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		Input input = container.getInput();
		super.update(container, game, delta);
		handlers.handle(input, this);
	
	}



	@Override
	public void toucher(int value) {
		setVie(getVie() - value);

	}

	public void modifierVitesseX(int valeurModVitesseX) {
		setVitesseX(valeurModVitesseX);

	}

	public void modifierVitesseY(int valeurModVitesseY) {
		setVitesseY(valeurModVitesseY);

	}

	public Balle tirer() throws SlickException {
		if (getMana() > 0) {
			setMana(getMana() - 1);
			Balle balle = new BalleRobot(getX(), getY(), getDirectionDroite(),
					0.01f, 1);
			balle.applyForce(10000, 0);
			return balle;
		}
		return null;
	}

}
