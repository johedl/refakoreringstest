
package se.xyz;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import se.kfm.geometry.*;
import se.kfm.persistance.PersistanceWriteException;

public class Main {
    public static void main(String[] args) throws PersistanceWriteException {

        List<Figure> listOfFigures = createFigures();

        // Rensa bort geometriska figurer som �r inom 1 enhets avst�nd
        filterByDistance(listOfFigures);

        sortListByArea(listOfFigures);

        // Skriv ut resultatet till en XML
        writeResult(listOfFigures);
    }

    private static void sortListByArea(List<Figure> listOfFigures) {
        Collections.sort(listOfFigures, new Comparator<Figure>() {
            public int compare(Figure f1, Figure f2) {
                return Double.compare(f1.calculateArea(), f2.calculateArea());
            }
        });
    }

    private static void filterByDistance(List<Figure> listOfFigures) {
        Iterator<Figure> figureIterator = listOfFigures.iterator();
        while (figureIterator.hasNext()) {
            Figure figure = figureIterator.next();
            Iterator<Figure> figureIterator2 = listOfFigures.iterator();
            while (figureIterator2.hasNext()) {
                Figure otherFigure = figureIterator2.next();
                if (!figure.equals(otherFigure)) {
                    double distance = figure.getDistanceTo(otherFigure);
                    if (distance <= 1) {
                        listOfFigures.remove(figure);
                    }
                }
            }
        }
    }

    private static List<Figure> createFigures() {
        List<Figure> listOfFigures = new CopyOnWriteArrayList<Figure>();
        // Bygg upp data
        for (int i = 0; i < 10; i++) {
            Random r = new Random();
            double b = r.nextDouble() * 20; // Bredd
            double h = r.nextDouble() * 20; // H�jd
            int posx = r.nextInt(20); // Xposition
            int posy = r.nextInt(20); // YPosition

            int remainder = i % 4;

            if (remainder == 0) {
                listOfFigures.add(new Circle(b / 2, posx, posy));
            } else if (remainder == 1) {
                listOfFigures.add(new Rectangle(b, h, posx, posy));
            } else if (remainder == 2) {
                listOfFigures.add(new Square(b, posx, posy));
            } else if (remainder == 3) {
                listOfFigures.add(new Triangle(b, h, posx, posy));
            }
        }
        return listOfFigures;
    }

    public static void writeResult(List<Figure> listOfFigures)
            throws PersistanceWriteException {
        BufferedOutputStream b = new BufferedOutputStream(System.out);

        try {
            b.write("<geometricfigures>".getBytes());

            for (Figure figure : listOfFigures) {
                writeGeoInfo(figure.getClass().getSimpleName(), figure.getXCoordinate(), figure.getYCoordinate(), figure.calculateArea(), b);
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