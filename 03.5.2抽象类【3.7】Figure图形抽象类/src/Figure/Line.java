package Figure;
import mypackage.Point;
public class Line extends Figure implements Comparable<Line>{
//	public Point point1,point2;
	public Point point2;
//	public double k=((point2.y-point1.y)/(point2.x-point1.x));
	public double k;
	public Line(Point point1,Point point2) {
		super(point1);
	//	this.point1=point1;
		this.point2=point2;
	}
	public Line(int x1,int y1,int x2,int y2) {
//		super();
		this(new Point(x1,y1),new Point(x2,y2));
//		point1.x=x1;
//		point1.y=y1;
//		point2.x=x2;
//		point2.y=y2;
		
	    this.k=((point2.y-point1.y)/(point2.x-point1.x));
	}
	public double length() {
		double a=point1.x-point2.x,b=point1.y-point2.y;
		  return Math.sqrt(a*a+b*b);
	}
	public String toString() {
		 return"一条直线，起点"+point1.toString()+",终点"+point2.toString()+",长度"+String.format("%1.2f",length());
	}
	public boolean contains(Point point) {
		return (point.x-this.point1.x)/this.point2.x-this.point1.x==
				(point.y-this.point1.y)/this.point2.y-this.point1.y;
	}
	public Point intersects(Line line) {
		int x,y;
		x=(int) (this.k*this.point1.x-line.k*line.point1.x-this.point1.y+line.point1.y);
		y=(int) (this.k*x-this.k*this.point1.x+this.point1.y);
		Point p=new Point(x,y);
		return p;
	}
//	public double distance(Point point) {
//		
//	}
	public int compareTo(Line line) {
		return (int)(this.length()-line.length());
	}
	
	
	public static void main(String[] args) {
		Line line=new Line(0,0,100,100);
		Line line2=new Line(0,1,1,0);
		System.out.println(line.contains(new Point(1,1)));
		System.out.println(line.intersects(line2).toString());
		System.out.println("L1:"+line.toString());
		System.out.println("L2:"+line2.toString());
		System.out.println(line.compareTo(line2));
	}
}
