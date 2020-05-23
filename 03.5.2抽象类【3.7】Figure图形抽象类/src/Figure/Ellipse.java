package Figure;
import mypackage.Point;
public class Ellipse extends Rectangle{
	public Ellipse(Point point,int length,int width) {
		super(point,length,width);
		this.shape="椭圆";
	}
	public Ellipse(Point point1,Point point2) {
		super(point1,point2);
		this.shape="椭圆";
	}
	public Ellipse(int x1,int y1,int x2,int y2) {
		super(x1,y1,x2,y2);
		this.shape="椭圆";
	}
	public Ellipse() {
		super();
		this.shape="椭圆";
	}
	public double perimeter() {
		return 2*Math.PI*(this.width/2)+4*(this.length/2-this.width/2) ;//2πb+4(a-b) 
	}
	public double area() {
		return Math.PI*(length/2)*(width/2);//πab
	}
	public String toString() {
		return "这个椭圆位于一个矩形内,"+super.toString();
	}
	public static void main(String[] args) {
		Ellipse ellipse=new Ellipse( new Point(100,100),100,50);
		System.out.println(ellipse.toString());
	}
	

}
