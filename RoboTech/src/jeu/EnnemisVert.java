package jeu;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class EnnemisVert extends Ennemis {
	//l'image qui contient le sprite du robot
	private Image image;
	
	public EnnemisVert(float x, float y, float masse, float tailleBlockPerso) throws SlickException {
		super(x, y, masse, tailleBlockPerso);
	
		//charge l'image du robot
		image = new Image("res/ennemiVert.png");
	}

	public void render(Graphics g) {
		//dessine l'image de l'ennemi en le centrant
		image.drawCentered(getX(), getY());
		
		//texte de debug
		g.drawString("sol?"+auSol()+" "+getX()+","+getY()+" "+velx, getX(), getY()-50);
	}
	
	public void update(int delta) {
		super.update(delta);
	}

	@Override
	public void pickupItem(Items potionVie) {
		// TODO Auto-generated method stub
		
	}

}
