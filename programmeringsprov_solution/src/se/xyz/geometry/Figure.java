package se.xyz.geometry;

public interface Figure {

    double calculateArea();

    int getXCoordinate();

    int getYCoordinate();

    double getDistanceTo(Figure otherFigure);

}
