package item_joueurs;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import personnages.Robot;

/**
 * 
 * @author Equipe RoboTech Potion restaurant de la Mana
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
	 * Constructeur de la classe PotionMana avec valeur de regen à 1 par
	 * défaut
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
	public PotionMana(float x, float y, float width, float height, float mass,
			Robot player) {
		super(x, y, width, height, mass, player, 1);
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
		if (player.getMana() < 5) {
			player.ajouterMana(valeur);
			return true;
		} else
			return false;

	}

}
