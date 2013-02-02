package factory;

public class Point {
	private float x;
	private float y;

	public Point(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void set_x(float x) {
		this.x = x;
	}

	public void set_y(float y) {
		this.y = y;
	}

	public float get_x() {
		return x;
	}

	public float get_y() {
		return y;
	}
}
