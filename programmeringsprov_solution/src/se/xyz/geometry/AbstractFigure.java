package se.xyz.geometry;

public abstract class AbstractFigure {

    protected double calculateDistance(double x1, double y1, double x2, double y2) {
        double x = Math.pow(x1 - x2, 2);
        double y = Math.pow(y1 - y2, 2);
        return Math.sqrt(x + y);
    }
}
