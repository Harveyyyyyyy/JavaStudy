package Stereogram;

public class Globe implements Solid{
	public double R;
	public Globe(double R) {
		this.R=R;
	}
	public double area() {
		return 4*Math.PI*R*R;
	}
	public double volume() {
		return (4*Math.PI*R*R*R)/3;
	}
	public static void main(String[] args) {
		Globe globe=new Globe(1);
		Area ar=globe;
		Volume vo=globe;
		System.out.println("表面积"+ar.area());
		System.out.println("体积"+vo.volume());
	}
}
