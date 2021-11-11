package se.xyz.geometry;

public class Square {
	
	private double x;
	
	private int posx;
	
	private int posy;
	
	public Square(double x, int posx, int posy) {
		this.x = x;
		this.posx = posx;
		this.posy = posy;
	}

	public double calculateArea() {
		return x * x;
	}
	
	public int getXCoordinate() {
		return posx;
	}
	
	public int getYCoordinate() {
		return posy;
	}
}
