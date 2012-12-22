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



public class Robot extends Personnage implements Drawable{
	//l'image qui contient le sprite du robot
	private Image image;
	RobotHandlers handlers;
	/**
	 * Create a new Alien actor read to add to the world
	 * 
	 * @param x The x position of the alien
	 * @param y The y position of the alien
	 * @param mass The mass of the alien
	 * @param size The size of the alien (collision size)
	 * @throws SlickException Indicates a failure to load resources for this alien
	 */
	public Robot(float x, float y, float mass, float size)  {
		super(x, y, mass, size);
		handlers = new RobotHandlers();
	
	}

	/**
	 * @see org.newdawn.penguin.Actor#render(org.newdawn.slick.Graphics)
	 */

	@Override
	public void pickupItem(Items item) {
		item.setPickedUp(true);
		if(item.getNom().equals("potionVie")) {
			ajouterVie();			
		}
		if(item.getNom().equals("potionMana")) {
			ajouterMana();			
		}
		if(item.getNom().equals("potionEnergie")) {
			ajouterEnergie();			
		}

		
	}

	private void ajouterEnergie() {
		setEnergie(getEnergie()+1);
		
	}

	private void ajouterMana() {
		setMana(getMana()+1);
		
	}

	private void ajouterVie() {
		setVie(getVie()+1);
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		image = new Image("res/robotx.png");
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
	throws SlickException {
		//dessine l'image du robot en le centrant
		image.drawCentered(getX(), getY());
		
		//texte de debug
		g.drawString("sol?"+auSol()+"facingRight?"+ getDirectionDroite() + " " +getX()+","+getY()+" "+velx, getX(), getY()-50);
		g.drawString("Vie : " + getVie() + ", Mana : " + getMana() + ", Energie : " + getEnergie(),getX(), getY()-70  );
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game,int delta) throws SlickException
	{
		Input input = container.getInput();
		super.update(container, game, delta);
		handlers.handle(input, this);
	}
	
}
