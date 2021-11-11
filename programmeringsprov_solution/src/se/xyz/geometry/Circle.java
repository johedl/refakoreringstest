package se.xyz.geometry;

public class Circle extends AbstractFigure implements Figure {
	
	private double r;
	
	private int posx;
	
	private int posy;
	
	public Circle(double r, int posx, int posy) {
		this.r = r;
		this.posx = posx;
		this.posy = posy;
	}

	@Override
	public double calculateArea() {
		return r * r * 3.14d;
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
