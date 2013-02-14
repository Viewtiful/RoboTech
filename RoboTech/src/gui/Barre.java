package gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import personnages.Robot;

/**
 * 
 * Barre abstraite de constante vitales
 * @author Equipe RoboTech
 *
 */
public abstract class Barre {

	/**
	 * La barre représentant la constante
	 */
	private Rectangle barre;
	/**
	 * Couleur de la barre
	 */
	private Color couleur;
	/**
	 * Largeur de la barre
	 */
	private float width;
	/**
	 * hauteur de la barre
	 */
	private float height;
	/**
	 * Nom de la barre/constante vitales à afficher
	 */
	private String barName;
	
	public String getBarName() {
		return barName;
	}

	public void setBarName(String barName) {
		this.barName = barName;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public Rectangle getBarre() {
		return barre;
	}

	public void setBarre(Rectangle barre) {
		this.barre = barre;
	}

	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

	/**
	 * Constructeur d'une barre
	 * @param x abscisse
	 * @param y ordonnée
	 * @param width largeur de la barre
	 * @param height hauteur de la barre
	 * @param couleur couleur de la barre
	 * @param barName nom de la barre à afficher
	 */
	public Barre(float x, float y, float width, float height, Color couleur,String barName) {
		this.barre = new Rectangle(x, y, width, height);
		this.couleur = couleur;
		setWidth(width);
		setHeight(height);
		setBarName(barName);
	}

	/**
	 * Affiche la barre
	 * @param g Objet Graphique
	 * @param player Joueur robot
	 */
	public void render(Graphics g, Robot player) {
		float pourcentage =(float) length(player)/(float) player.getMax();  
		barre.setWidth(pourcentage * (float)getWidth());
		g.setColor(couleur);
		g.fill(barre);
		g.drawString(getBarName(), barre.getX(), barre.getY()-15);
	}

	/**
	 * Permet de redessiner la barre en fonction de x,y
	 * @param x translation en abscisse
	 * @param y translation en ordonnée
	 */
	public void update(float x, float y) {
		barre.setX(x);
		barre.setY(y+15);
	}

	/**
	 * longueur de la barre en fonction de la constante vitales que représente la barre
	 * @param player Robot à surveiller
	 * @return la longeur de la barre
	 */
	public abstract int length(Robot player);

}
