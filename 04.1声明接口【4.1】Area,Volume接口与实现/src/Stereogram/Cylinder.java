package Stereogram;

import Figure.ClosedFigure;

public class Cylinder implements Area,Volume{
	protected ClosedFigure cfigure;
	protected double height;
	public Cylinder(ClosedFigure cfiggure,double height) {
		this.cfigure=cfiggure;
		this.height=height;
	}
	public double area() {
		return cfigure.perimeter()*this.height+2*cfigure.area();
	}
	public double volume() {
		return cfigure.area()*this.height;
	}
	public String toString() {
		return this.getClass().getName()+"µ×ÃæÊÇ"+this.cfigure.toString()+";¸ß"+this.height;
	}

}
