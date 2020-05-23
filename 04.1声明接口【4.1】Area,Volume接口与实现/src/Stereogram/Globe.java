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
		System.out.println(globe.area());
		System.out.println(globe.volume());
		Area ar=globe;
		Volume vo=globe;
		System.out.println(ar.area());
		System.out.println(vo.volume());
	}
}
