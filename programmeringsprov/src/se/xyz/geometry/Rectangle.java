package se.xyz.geometry;

public class Rectangle {
	
	private double x;
	
	private double y;
	
	private int posx;
	
	private int posy;
	
	public Rectangle(double x, double y, int posx, int posy) {
		this.x = x;
		this.y = y;
		this.posx = posx;
		this.posy = posy;
	}

	public double calculateArea() {
			return x * y;
	}
	
	public int getXCoordinate() {
		return posx;
	}
	
	public int getYCoordinate() {
		return posy;
	}
}
