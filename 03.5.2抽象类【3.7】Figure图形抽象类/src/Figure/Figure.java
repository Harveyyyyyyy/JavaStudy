package Figure;
import mypackage.Point;
public abstract class Figure {
	public Point point1;
	protected Figure(Point point1) {
		this.point1=point1;
	}
	
//	protected Figure(Point point1) {
		
//	}
	
	public  Figure() {
	  this(new Point(0,0));
	}
	public String toString() {
		return this.point1==null?"":this.point1.toString();
	}
//	public abstract void draw(java.awt.Graphics g);
//	public abstract void revolve(int angle);
//	public abstract void zoom(int percentage);
	

}
