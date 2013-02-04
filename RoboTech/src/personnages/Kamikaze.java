package personnages;

import jeu.Monde;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Kamikaze extends Ennemis{

	private Robot player;
	private float rayon_detection;
	private Image rendu;
	private boolean detected  = false;
	private int deplacement = 0;
	public Kamikaze(float x, float y, float masse, float tailleBlockPerso,
			Monde monde,float rayon_detection,Image im) {
		super(x, y, masse, tailleBlockPerso, monde);
		player = monde.getPlayer();
		this.rayon_detection = rayon_detection;
		rendu = im;
		
	}
	
	@Override
	public void render(Graphics g) {
		rendu.drawCentered(getX(), getY());
		
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		render(g);
		
	}

	@Override
	public void toucher(int value) {
		// TODO Auto-generated method stub
		
	}

	public void update(GameContainer container, StateBasedGame game, int delta)
	{
		float x1_rayon = getX() + rayon_detection;
		float x2_rayon = getX() - rayon_detection;
		float x_player = player.getX();
		if(x_player <= x1_rayon && x_player >=x2_rayon && !detected)
		{
			detected = true;
			if(x_player <= getX())
				deplacement = -1;
			else
				deplacement = 1;
		}
		if(detected)
			setX(getX()+deplacement);
		
		
			
	}
	
	
}
