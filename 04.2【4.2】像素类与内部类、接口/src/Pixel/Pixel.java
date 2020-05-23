package Pixel;
import mypackage.*;
public class Pixel extends Point{
	private Color color;
	public static interface ColorConstant{
		public static final int BLACK=0xff000000;
		public static final int RED=0xffff0000;
		public static final int GREEN=0xff00ff00;
		public static final int BLUE=0xff0000ff;
		public static final int WHITE=0xffffffff;
	}
	public static class Color implements ColorConstant{
		private int value;
		public Color(int rgb) {
			this.value=0xff000000|rgb;
		}
		public Color(int red,int green,int blue) {
			this.value=0xff000000|((red&0xff)<<16)|((green&0xff)<<8)|blue&0xff;
		}
		public Color() {
			this(BLACK);
		}
		public int getRed() {
			return (this.value>>16)&0xff;
		}
		public int getGreen() {
			return (this.value>>8)&0xff;
		}
		public int getBlue() {
			return this.value&0xff;
		}
		public String toString() {
			return "[r="+getRed()+",g="+getGreen()+",b="+getBlue()+"]";
		}
	}
	public Pixel(Point p,int colorvalue) {
		super(p);
		this.color=new Color(colorvalue);
	}
	public Pixel() {
		this(new Point(),ColorConstant.BLACK);
	}
	public String toString() {
		return "ÏñËØ"+super.toString()+"£¬ÑÕÉ«"+this.color.toString();	
	}
	public static void main(String[] args) {
		System.out.println(new Pixel().toString());
		Point point=new Point(100,100);
		Pixel pixel=new Pixel(point,Pixel.ColorConstant.RED);
		System.out.println(pixel.toString());
	}

}
