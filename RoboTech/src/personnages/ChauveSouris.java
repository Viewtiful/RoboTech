package personnages;

import jeu.Monde;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.XMLPackedSheet;
import org.newdawn.slick.state.StateBasedGame;

public class ChauveSouris extends Ennemis {
	// l'image qui contient le sprite du robot
	private Image image;
	private XMLPackedSheet sheet;
	private int i;
	private int animationStep;

	public ChauveSouris(float x, float y, float masse, float tailleBlockPerso,
			Monde monde) throws SlickException {
		super(x, y, masse, tailleBlockPerso, monde);
		sheet = new XMLPackedSheet("res/bat.png",
				"res/bat.xml");
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		image = sheet.getSprite("bat_00.png");		
	}

	public void render(Graphics g) {
		i++;
		if (i >= 6) {
			animationStep++;
			animationStep %= 3;
			image = sheet.getSprite("bat_0" + animationStep + ".png");			
			if (getDirectionDroite()) {
				image = image.getFlippedCopy(true, false);
			}
			i = 0;
		}
		
		// dessine l'image de l'ennemi en le centrant
		image.drawCentered(getX(), getY());
		// texte de debug
		g.drawString("sol?" + auSol() + " " + getX() + "," + getY() + " "
				+ velx, getX(), getY() - 50);
		g.drawString("Vie : " + getVie(), getX(), getY() - 70);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		render(g);
	}

	@Override
	public void toucher(int value) {
		setVie(getVie() - value);

	}

}
