package Figure;
import mypackage.Point;
public class Polygon extends ClosedFigure{
	private Point[] points;
	public Polygon(Point[] points) {
		super("多边形",points[0]);
		this.points=points;
	}
	public String toString() {
		String str="("+points.length+"个点"+this.points[0].toString();
		for(int i=1;i<points.length;i++)
			str+=","+this.points[i].toString();
		return str+")";
	}
	public double perimeter() {
		double perim=0;
		for(int i=0;i<points.length;i++)
			perim+=new Line(points[i],points[(i+1)%points.length]).length();
		return perim;
	}
	public double area() {
		double sum=0;
		for(int i=1;i<points.length-1;i++)
			sum+=new Triangle(points[0],points[i],points[i+1]).area();
		return sum;
	}
	

}
