package Figure;
import mypackage.Point;
public class Rectangle extends ClosedFigure {
	//���εĳ��ȺͿ��
	protected int length, width;  
//	public Point point2;
	//�����ֱ�ָ���������Ͻǵ㡢���ȺͿ��
    public Rectangle(Point point1, int length, int width) {
    	super("����", point1);
 //   	this.point1=point1;
    	this.length=length;
    	this.width=width;
    }
  //����ָ���������Ͻǵ�����½ǵ�����
    public Rectangle(Point point1, Point point2) {
 //     this.point1=point1;
 //    this.point2=point2;	
    	this(point1,Math.abs(point2.x-point1.x),Math.abs(point2.y-point1.y));
      
    }
  //���췽�����أ�����Ϊ(x1,y1)��(x2,y2)
    public Rectangle(int x1,int y1, int x2,int y2) {
 //   	point1.x=x1;
  //  	point1.y=y1;
  //  	point2.x=x2;
  //  	point2.y=y2;
    	this(new Point(x1,y1),Math.abs(x2-x1),Math.abs(y2-y1));
    }
    public Rectangle() {
    }
  //�������췽��
    public Rectangle(Rectangle rec) {
    	
    	this(new Point(rec.point1),rec.length,rec.width);
    }
    //���������ַ�����������λ�á����ȡ��������
    public String toString() {
    	return "�þ��ε����Ͻ�λ��Ϊ"+point1.toString()+",����Ϊ"+this.length+",���Ϊ"+this.width
    			+",���Ϊ:"+this.area()+",�ܳ�Ϊ:"+this.perimeter();
    }
	public double perimeter() {
		return 2*(length+width);
	}
	public double area() {
		return length*width;
	}
	public static void main(String[] args) {
		Rectangle rec=new Rectangle(new Point(100,100),100,50);
		Rectangle rec2=new Rectangle(rec);
		System.out.println(rec.toString());
		System.out.println(rec2.toString());
		rec2.point1.x=200;
		rec2.point1.y=200;
		rec2.length=200;
		System.out.println(rec.toString());
		System.out.println(rec2.toString());
		System.out.println(rec.compareTo(rec2));
		//������
		ClosedFigure tri=new Triangle(new Point(0,0), new Point(3,0), new Point(3,3));
		System.out.println(tri.compareTo(rec2));
		System.out.println(rec2.compareTo(tri));

	}

}
