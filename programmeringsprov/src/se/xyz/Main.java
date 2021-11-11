package se.xyz;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import se.kfm.geometry.Circle;
import se.kfm.geometry.Rectangle;
import se.kfm.geometry.Square;
import se.kfm.geometry.Triangle;
import se.kfm.persistance.PersistanceWriteException;

public class Main {
	public static void main(String[] args) throws IOException, Exception {

		ArrayList<Object> l = new ArrayList<Object>();
		int count = 0;
		// Bygg upp data
		for (int i = 0; i < 10; i++) {
			Random r = new Random();
			double b = r.nextDouble() * 20; // Bredd
			double h = r.nextDouble() * 20; // Höjd
			int posx = r.nextInt(20); // Xposition
			int posy = r.nextInt(20); // YPosition

			
			if (count == 0) {
				l.add(new Circle(b / 2, posx, posy));
			} else if (count == 1) {
				l.add(new Rectangle(b, h, posx, posy));
			} else if (count == 2) {
				l.add(new Square(b, posx, posy));
			} else if (count == 3) {
				l.add(new Triangle(b, h, posx, posy));
			}
			
			if(count == 3) {
				count = 0;
			} else {
				count++;
			}
		}

		// Rensa bort geometriska figurer som är inom 1 enhets avstånd
		for (Object o : l) {
			for (Object o2 : l) {
				if (!o.equals(o2) && distance(o, o2) <= 1) {
					l.remove(o);
				}
			}
		}

		// TODO: Sortera efter area
		//Collections.sort(l);
		
		// Skriv ut resultatet till en XML
		writeResult(l);
	}

	public static double distance(Object o, Object o2) {
		double x1 = 0.0d;
		double x2 = 0.0d;
		double y1 = 0.0d;
		double y2 = 0.0d;

		if (o instanceof Circle) {
			Circle c = (Circle) o;
			x1 = c.getXCoordinate();
			y1 = c.getYCoordinate();
		} else if (o instanceof Rectangle) {
			Rectangle r = (Rectangle) o;
			x1 = r.getXCoordinate();
			y1 = r.getYCoordinate();
		} else if (o instanceof Square) {
			Square s = (Square) o;
			x1 = s.getXCoordinate();
			y1 = s.getYCoordinate();
		} else if (o instanceof Triangle) {
			Triangle t = (Triangle) o;
			x1 = t.getXCoordinate();
			y1 = t.getYCoordinate();
		}

		if (o2 instanceof Circle) {
			Circle c = (Circle) o2;
			x2 = c.getXCoordinate();
			y2 = c.getYCoordinate();
		} else if (o2 instanceof Rectangle) {
			Rectangle r = (Rectangle) o2;
			x2 = r.getXCoordinate();
			y2 = r.getYCoordinate();
		} else if (o2 instanceof Square) {
			Square s = (Square) o2;
			x2 = s.getXCoordinate();
			y2 = s.getYCoordinate();
		} else if (o2 instanceof Triangle) {
			Triangle t = (Triangle) o2;
			x2 = t.getXCoordinate();
			y2 = t.getYCoordinate();
		}

		double d = distance(x1, y1, x2, y2);
		return d;
	}

	public static double distance(double x1, double y1, double x2, double y2) {
		double x = Math.pow(x1 - x2, 2);
		double y = Math.pow(y1 - y2, 2);
		return Math.sqrt(x + y);
	}

	public static void writeResult(ArrayList<Object> l)
			throws PersistanceWriteException {
		BufferedOutputStream b = new BufferedOutputStream(System.out);

		try {
			b.write("<geometricfigures>".getBytes());

			for (Object o2 : l) {
				if (o2 instanceof Circle) {
					Circle c = (Circle) o2;
					writeGeoInfo("Circle", c.getXCoordinate(), c.getYCoordinate(), c.calculateArea(), b);
				} else if (o2 instanceof Rectangle) {
					Rectangle r = (Rectangle) o2;
					writeGeoInfo("Rectangle", r.getXCoordinate(), r.getYCoordinate(), r.calculateArea(), b);
				} else if (o2 instanceof Square) {
					Square s = (Square) o2;
					writeGeoInfo("Square", s.getXCoordinate(), s.getYCoordinate(), s.calculateArea(), b);
				} else if (o2 instanceof Triangle) {
					Triangle t = (Triangle) o2;
					writeGeoInfo("Triangle", t.getXCoordinate(),t.getYCoordinate(), t.calculateArea(), b);
				}
			}

			b.write("</geometricfigures>".getBytes());

			b.flush();
			b.close();
		} catch (Exception e) {
			System.err.println("Unable to persist the geometric figures");
			throw new PersistanceWriteException("Unable to persist the geometric figures");
		}
	}

	public static void writeGeoInfo(String t, int x, int y, double a,
			BufferedOutputStream b) throws Exception {

		try {
			b.write("<figure>".getBytes());

			b.write(String.format("<type>%s</type>", t).getBytes());
			b.write(String.format("<area>%f</area>", a).getBytes());
			b.write(String.format("<xcoordinate>%s</xcoordinate>", x).getBytes());
			b.write(String.format("<ycoordinate>%s</ycoordinate>", y).getBytes());

			b.write("</figure>".getBytes());
		} catch (IOException e) {
			System.err.println("Unable to write figure data");
			throw new PersistanceWriteException("Unable to write figure data");
		}
	}
}
