package Figure;
import mypackage.Point;
public class Triangle extends ClosedFigure{
	public Point point2,point3;
	protected double a,b,c;
	public Triangle(Point p1,Point p2,Point p3) {
		super("三角形",p1);
		this.point2=p2;
		this.point3=p3;
		this.a=new Line(p1,p2).length();
		this.b=new Line(p2,p3).length();
		this.c=new Line(p3,p1).length();
	}
//	public Triangle(Point point1,double a,double b,double c)
	public String toString() {
		return "(3点坐标"+super.toString()+","+(this.point2==null?"":this.point2.toString())+
				","+(this.point3==null?"":this.point3.toString())+"，3边长"+String.format("%1.2f,%1.2f,%1.2f", this.a,this.b,this.c);
	}
	public double perimeter() {
		return a+b+c;
	}
	public double area() {
		double s=(a+b+c)/2;
		return Math.sqrt(s*(s-a)*(s-b)*(s-c));
	}

}
