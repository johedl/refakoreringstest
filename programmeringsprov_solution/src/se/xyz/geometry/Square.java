package se.xyz.geometry;

public class Square extends AbstractFigure implements Figure {
	
	private double x;
	
	private int posx;
	
	private int posy;
	
	public Square(double x, int posx, int posy) {
		this.x = x;
		this.posx = posx;
		this.posy = posy;
	}

	@Override
	public double calculateArea() {
		return x * x;
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
