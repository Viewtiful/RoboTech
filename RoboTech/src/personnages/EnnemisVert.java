package personnages;

import items.Items;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;



public class EnnemisVert extends Ennemis {
	//l'image qui contient le sprite du robot
	private Image image;
	
	public EnnemisVert(float x, float y, float masse, float tailleBlockPerso)  {
		super(x, y, masse, tailleBlockPerso);
	}

	
	@Override
	public void pickupItem(Items potionVie) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
		image = new Image("res/ennemiVert.png");
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		//dessine l'image de l'ennemi en le centrant
		image.drawCentered(getX(), getY());
		
		//texte de debug
		g.drawString("sol?"+auSol()+" "+getX()+","+getY()+" "+velx, getX(), getY()-50);
		g.drawString("Vie : " + getVie(),getX(), getY()-70  );
	}


	@Override
	public void toucher() {
		setVie(getVie() - 1);
		
	}

}
