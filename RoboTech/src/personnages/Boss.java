package personnages;

import jeu.Monde;
import jeu.RoboTech;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.XMLPackedSheet;
import org.newdawn.slick.state.StateBasedGame;

public class Boss extends Ennemis {
	// l'image qui contient le sprite du robot
	private Image image;
	private XMLPackedSheet sheet;
	private int i;
	private int animationStep;
	private boolean toucherDmg;

	public Boss(float x, float y, float masse, float tailleBlockPerso,
			Monde monde) throws SlickException {
		super(x, y, masse, tailleBlockPerso, monde);

		sheet = new XMLPackedSheet("res/Boss.png", "res/boss.xml");
		setVie(5);
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		image = sheet.getSprite("boss_00.png");
	}

	public void render(Graphics g) {
		i++;
		if (i >= 15) {
			animationStep++;
			animationStep %= 3;
			image = sheet.getSprite("boss_0" + animationStep + ".png");
			if (getDirectionDroite()) {
				image = image.getFlippedCopy(true, false);
			}
			i = 0;
		}

		if (toucherDmg) {
			image.setAlpha(0.001f);
			if (i >= 8) {
				image.setAlpha(1.f);
				toucherDmg = false;
			}
		}

		// dessine l'image de l'ennemi en le centrant
		image.drawCentered(getX(), getY());

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {

		render(g);

		if (getVie() == 0) {
			game.enterState(RoboTech.VICTOIRETAT);
			container.reinit();
		}
	}

	public void update(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
	}

	@Override
	public void toucher(int value) {
		toucherDmg = true;
		setVie(getVie() - value);
	}

}
