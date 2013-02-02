package blocs;

public class Point{
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
	
	public Point distance(Point o1)
	{
		float x1 = Math.abs(get_x()-o1.get_x());
		float y1 = Math.abs(get_y()-o1.get_y());
		return new Point(x1,y1);
	}

	public boolean near(Point o1,Point eps)
	{
		Point distance = distance(o1);
		assert(distance!=eps);
		boolean b1 = distance.get_x() < eps.get_x();
		boolean b2 = distance.get_y() < eps.get_y();
		return (b1 && b2);
	}
	
	public void add(Point o1)
	{
		set_x(o1.get_x()+get_x());
		set_y(o1.get_y()+get_y());
	}
	
	public void add(float x,float y)
	{
		set_x(get_x()+x);
		set_y(get_y()+y);
	}
	
	@Override
	public String toString()
	{
		String res = "";
		res = "Abscisse = "+get_x()+"Ordonnée = "+get_y()+"\n";
		return res;
	}
}
