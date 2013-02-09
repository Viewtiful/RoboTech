package blocs;
/**
 * 
 * @author Equipe RoboTech
 * Un point du Plan
 */
public class Point {
	/**
	 * Abscisse r�elle
	 */
	private float x;
	/** 
	 * Ordonn�e r�elle
	 */
	private float y;

	/**
	 * 
	 * @param x Abscisse du point � cr�er
	 * @param y Ordonn�e du point � crer
	 */
	public Point(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	/**
	 * Renvoie l'ecart entre deux point (en valeur Absoulue)
	 * @param o1 Point � comparer
	 * @return vecteur distance
	 */
	public Point distance(Point o1) {
		float x1 = Math.abs(getX() - o1.getX());
		float y1 = Math.abs(getY() - o1.getY());
		return new Point(x1, y1);
	}

	/**
	 * Permet de savoir la relative proximit� de deux points
	 * @param o1 Point � comparer
	 * @param eps �cart acceptables
	 * @return vrai si deux points sont proches � un eps pr�s
	 */
	public boolean near(Point o1, Point eps) {
		Point distance = distance(o1);
		assert (distance != eps);
		boolean b1 = distance.getX() < eps.getX();
		boolean b2 = distance.getY() < eps.getY();
		return (b1 && b2);
	}

	/**
	 * Ajoute un point � un autre
	 * @param o1 Le deuxi�me op�rande
	 */
	public void add(Point o1) {
		setX(o1.getX() + getX());
		setY(o1.getY() + getY());
	}

	/**
	 * Effectue une translation
	 * @param x Translation horizontale
	 * @param y Translation Verticale
	 */
	public void add(float x, float y) {
		setX(getX() + x);
		setY(getY() + y);
	}

	/**
	 * Fonction permettant de calculer la longeuru d'un segment
	 * @param b Extr�mit� du segment
	 * @return Distance
	 */
	public float pythagore(Point b) {
		float diff1 = b.getX() - getX();
		float diff2 = b.getY() - getY();
		diff1 = diff1 * diff1;
		diff2 = diff2 * diff2;
		return (float) Math.sqrt(diff1 + diff2);
	}

	@Override
	public String toString() {
		String res = "";
		res = "X = " + getX() + "Y = " + getY() + "\n";
		return res;
	}
}
