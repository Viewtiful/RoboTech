package personnages;

import jeu.Monde;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class EnnemisRouge extends Ennemis {
	// l'image qui contient le sprite du robot
	private Image image;

	public EnnemisRouge(float x, float y, float masse, float tailleBlockPerso,
			Monde monde) throws SlickException {
		super(x, y, masse, tailleBlockPerso, monde);

		// charge l'image du robot
		image = new Image("res/ennemiRouge.png");
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {

	}

	public void render(Graphics g) {
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

	public void update(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
	}

	@Override
	public void toucher(int value) {
		setVie(getVie() - value);

	}

}
