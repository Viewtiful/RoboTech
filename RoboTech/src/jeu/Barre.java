package jeu;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import personnages.Robot;

public abstract class Barre {

	private Rectangle barre;
	private Color couleur;
	private float width;
	private float height;
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

	public Barre(float x, float y, float width, float height, Color couleur,String barName) {
		this.barre = new Rectangle(x, y, width, height);
		this.couleur = couleur;
		setWidth(width);
		setHeight(height);
		setBarName(barName);
	}

	public void render(Graphics g, Robot player) {
		barre.setWidth((float) length(player) * (float)getWidth()
				/ (float) player.getMax());
		g.setColor(couleur);
		g.fill(barre);
		g.drawString(getBarName(), barre.getX(), barre.getY()-15);
	}

	public void update(float x, float y) {
		barre.setX(x);
		barre.setY(y+15);
	}

	public abstract int length(Robot player);

}
