package Line;
import mypackage.*;
public class Line {
	public Point point1,point2;
    public Line(Point point1,Point point2){
        this.point1=point1;
        this.point2=point2;
    }
    public double length(){
        int a=point1.x-point2.x,b=point1.y-point2.y;
        return Math.sqrt(a*a+b*b);
    }
    public String toString(){
        return"一条直线，起点"+point1.toString()+",终点"+point2.toString()+",长度"+String.format("%1.2f",length());
    }
    public static void main(String[] args) {
        Point point1=new Point(),point2=new Point(40,30);
        System.out.println(new Line(point1,point2).toString());
    }
}


