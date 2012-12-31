package personnages;

import jeu.Monde;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class EnnemisVert extends Ennemis {
	// l'image qui contient le sprite du robot
	private Image image;

	public EnnemisVert(float x, float y, float masse, float tailleBlockPerso,
			Monde monde) {
		super(x, y, masse, tailleBlockPerso, monde);
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
		image = new Image("res/ennemiVert.png");
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

	@Override
	public void toucher(int value) {
		setVie(getVie() - value);

	}

}
