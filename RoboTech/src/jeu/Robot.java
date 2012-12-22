package jeu;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Robot extends Personnage {
	//l'image qui contient le sprite du robot
	private Image image;
	
	/**
	 * Create a new Alien actor read to add to the world
	 * 
	 * @param x The x position of the alien
	 * @param y The y position of the alien
	 * @param mass The mass of the alien
	 * @param size The size of the alien (collision size)
	 * @throws SlickException Indicates a failure to load resources for this alien
	 */
	public Robot(float x, float y, float mass, float size) throws SlickException {
		super(x, y, mass, size);
	
		//charge l'image du robot
		image = new Image("res/robotx.png");
	}

	/**
	 * @see org.newdawn.penguin.Actor#render(org.newdawn.slick.Graphics)
	 */
	public void render(Graphics g) {
		
		//dessine l'image du robot en le centrant
		image.drawCentered(getX(), getY());
		
		//texte de debug
		g.drawString("sol?"+auSol()+"facingRight?"+ getDirectionDroite() + " " +getX()+","+getY()+" "+velx, getX(), getY()-50);
		g.drawString("Vie : " + getVie() + ", Mana : " + getMana() + ", Energie : " + getEnergie(),getX(), getY()-70  );
	}

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
}
