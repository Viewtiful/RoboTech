package item_joueurs;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import personnages.Robot;

/**
 * Potion restaurant de la Mana
 * @author Equipe RoboTech 
 */
public class PotionMana extends Potion {

	/**
	 * Constructeur de la classe potionMana
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
	public PotionMana(float x, float y, float width, float height, float mass,
			Robot player, int valeurRegen) {
		super(x, y, width, height, mass, player, valeurRegen);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		image = new Image("res/potionMana.png");

	}

	/**
	 * {@inheritDoc} <br/>
	 * <b>Comportement :</b><br />
	 * Ici on restaure le mana du Robot
	 */
	@Override
	public boolean effect(Robot player) {
		assert(player != null);
		
		if (player.getMana() < 5) {
			player.ajouterMana(valeur);
			return true;
		} else
			return false;

	}

}
