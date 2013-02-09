package blocs;
/**
 * 
 * @author Equipe RoboTech
 * Un point du Plan
 */
public class Point {
	/**
	 * Abscisse réelle
	 */
	private float x;
	/** 
	 * Ordonnée réelle
	 */
	private float y;

	/**
	 * 
	 * @param x Abscisse du point à créer
	 * @param y Ordonnée du point à crer
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
	 * @param o1 Point à comparer
	 * @return vecteur distance
	 */
	public Point distance(Point o1) {
		float x1 = Math.abs(getX() - o1.getX());
		float y1 = Math.abs(getY() - o1.getY());
		return new Point(x1, y1);
	}

	/**
	 * Permet de savoir la relative proximité de deux points
	 * @param o1 Point à comparer
	 * @param eps écart acceptables
	 * @return vrai si deux points sont proches à un eps près
	 */
	public boolean near(Point o1, Point eps) {
		Point distance = distance(o1);
		assert (distance != eps);
		boolean b1 = distance.getX() < eps.getX();
		boolean b2 = distance.getY() < eps.getY();
		return (b1 && b2);
	}

	/**
	 * Ajoute un point à un autre
	 * @param o1 Le deuxième opérande
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
	 * @param b Extrémité du segment
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
