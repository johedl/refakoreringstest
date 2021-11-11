package se.xyz.geometry;

public class Rectangle extends AbstractFigure implements Figure {
	
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

	@Override
	public double calculateArea() {
			return x * y;
	}

	@Override
	public int getXCoordinate() {
		return posx;
	}

	@Override
	public int getYCoordinate() {
		return posy;
	}

	@Override
	public double getDistanceTo(Figure otherFigure) {
		return super.calculateDistance(getXCoordinate(), getYCoordinate(), otherFigure.getXCoordinate(), otherFigure.getYCoordinate());
	}
}
