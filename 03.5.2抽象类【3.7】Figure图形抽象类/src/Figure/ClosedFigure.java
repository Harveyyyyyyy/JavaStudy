package Figure;
import mypackage.Point;
//import java.awt.Polygon;
import mypackage.Point;
public abstract class ClosedFigure extends Figure implements Comparable<ClosedFigure>{
     protected String shape;
     protected ClosedFigure(String shape,Point point1) {
    	 super(point1);
    	 this.shape=shape;
     }
     protected ClosedFigure() {
    	 this("",new Point());
     }
     public abstract double perimeter();
     public abstract double area();
     public void print() {
    	 System.out.println(this.shape+this.toString()+","
    			 +String.format("周长%1.2f,面积%1.2f", this.perimeter(),this.area()));
     }
     public int compareTo(ClosedFigure cfig) {
    	 return (int)this.area()-(int)cfig.area();
     }
     public static void main(String[] args) {
 		Point point1=new Point(100,100);
 		ClosedFigure cfig=new Triangle(point1,new Point(100,130),new Point(140,130));
 		cfig.print();
 		Point[] pentagon= {point1,new Point(200,100),new Point(250,150),new Point(200,200),
 				           new Point(100,200)};
 		cfig=new Polygon(pentagon);
 		cfig.print();
 		System.out.println(cfig.compareTo(cfig));
 		}

}
