package personnages;

import jeu.Monde;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.XMLPackedSheet;
import org.newdawn.slick.state.StateBasedGame;

public class Serpent extends Ennemis {
	// l'image qui contient le sprite du robot
	private Image image;
	private XMLPackedSheet sheet;
	private int i;
	private int animationStep;
	private boolean toucherDmg;

	public Serpent(float x, float y, float masse, float tailleBlockPerso,
			Monde monde,float rayonDetection) throws SlickException {
		super(x, y, masse, tailleBlockPerso, monde,rayonDetection,2);

		sheet = new XMLPackedSheet("res/monster.png", "res/monster.xml");
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		image = sheet.getSprite("monster_00.png");
	}

	public void render(Graphics g) {
		i++;
		if (i >= 8) {
			animationStep++;
			animationStep %= 9;
			image = sheet.getSprite("monster_0" + animationStep + ".png");
			if (getDirectionDroite()) {
				image = image.getFlippedCopy(true, false);
			}
			i = 0;
		}

		if (toucherDmg) {
			image.setAlpha(0.001f);
			if (i >= 5) {
				image.setAlpha(1.f);
				toucherDmg = false;
			}
		}

		// dessine l'image de l'ennemi en le centrant
		image.drawCentered(getX(), getY());
		renderVie(g);

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {

		render(g);
	}

	@Override
	public void toucher(int value) {
		toucherDmg = true;
		setVie(getVie() - value);

	}

}
