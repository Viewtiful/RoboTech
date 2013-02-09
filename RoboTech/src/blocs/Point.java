package blocs;

public class Point {
	private float x;
	private float y;

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

	public Point distance(Point o1) {
		float x1 = Math.abs(getX() - o1.getX());
		float y1 = Math.abs(getY() - o1.getY());
		return new Point(x1, y1);
	}

	public boolean near(Point o1, Point eps) {
		Point distance = distance(o1);
		assert (distance != eps);
		boolean b1 = distance.getX() < eps.getX();
		boolean b2 = distance.getY() < eps.getY();
		return (b1 && b2);
	}

	public void add(Point o1) {
		setX(o1.getX() + getX());
		setY(o1.getY() + getY());
	}

	public void add(float x, float y) {
		setX(getX() + x);
		setY(getY() + y);
	}

	@Override
	public String toString() {
		String res = "";
		res = "X = " + getX() + "Y = " + getY() + "\n";
		return res;
	}
}
