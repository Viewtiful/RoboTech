package item_joueurs;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import personnages.Robot;

/**
 * 
 * @author Antoine Potion restaurant la Vie
 */
public class PotionVie extends Potion {

	/**
	 * Constructeur de la classe potionVie
	 * 
	 * @param x
	 *            : position en x de la potion
	 * @param y
	 *            : position en y de la potion
	 * @param width
	 *            : largeur de la potion
	 * @param height
	 *            : hauteur de la potion
	 * @param mass
	 *            : masse de la potion
	 * @param player
	 *            : passe en parametre le joueur pour savoir quand il a ramasser
	 *            la potion ....
	 * @param valeurRegen
	 *            : la valeur que regénère la potion
	 */
	public PotionVie(float x, float y, float width, float height, float mass,
			Robot player, int valeurRegen) {
		super(x, y, width, height, mass, player, valeurRegen);
	}

	/**
	 * Constructeur de la classe PotionVie avec valeur de regen à 1 par défaut
	 * 
	 * @param x
	 *            : position en x de la potion
	 * @param y
	 *            : position en y de la potion
	 * @param width
	 *            : largeur de la potion
	 * @param height
	 *            : hauteur de la potion
	 * @param mass
	 *            : masse de la potion
	 * @param player
	 *            : passe en parametre le joueur pour savoir quand il a ramasser
	 *            la potion ....
	 */
	public PotionVie(float x, float y, float width, float height, float mass,
			Robot player) {
		super(x, y, width, height, mass, player, 1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		image = new Image("res/potionVie.png");

	}

	/**
	 * {@inheritDoc} <br/>
	 * <b>Comportement :</b><br />
	 * Ici on restaure la vie du Robot
	 */
	@Override
	public boolean effect(Robot player) {
		if(player.getVie() < 5) {
			player.ajouterVie(valeur);
			return true;	
		}
		else
			return false;
	}

}
