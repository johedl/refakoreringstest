package se.xyz.geometry;

public class Circle {
	
	private double r;
	
	private int posx;
	
	private int posy;
	
	public Circle(double r, int posx, int posy) {
		this.r = r;
		this.posx = posx;
		this.posy = posy;
	}
	
	public double calculateArea() {
		return r * r * 3.14d;
	}
	
	public int getXCoordinate() {
		return posx;
	}
	
	public int getYCoordinate() {
		return posy;
	}
}
