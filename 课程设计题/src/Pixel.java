
import java.awt.*;

public class Pixel extends Point{
	private Color color;
	public Pixel(Point point,Color color) {
		super(point);
		this.color=color;
	}
	public Pixel() {
		this(new Point(),Color.BLACK);
	}
	public String toString() {
		return "����"+super.toString()+"����ɫ"+this.color.toString();	
	}

}
