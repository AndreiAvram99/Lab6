package shape;

import frame.MainFrame;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * RegularShape Class
 * @author avram
 */

public class RegularShape {

    final MainFrame FRAME;
    private int centerX;
    private int centerY;
    private int radius;
    private ShapeType shapeType;
    private Polygon polygon = new Polygon();
    private Ellipse2D ellipse = new Ellipse2D.Double();
    private int nbOfEdges = 0;

    public RegularShape(MainFrame frame, int x0, int y0, int radius) {
        FRAME = frame;
        ShapeType shapeType = ShapeType.POLYGON;
        this.centerX = x0;
        this.centerY = y0;
        this.radius = radius;
    }

    public Polygon getRegularPolygon(int nbOfEdges){
        polygon.reset();
        double alpha = 2 * Math.PI / nbOfEdges;
        for (int i = 0; i < nbOfEdges; i++) {
            double x = centerX + radius * Math.cos(alpha * i);
            double y = centerY + radius * Math.sin(alpha * i);
            polygon.addPoint((int) x, (int) y);
        }
        this.nbOfEdges = nbOfEdges;
        shapeType = ShapeType.POLYGON;
        return polygon;
    }

    public Ellipse2D getRegularEllipse(){
        ellipse.setFrame(centerX, centerY, radius, radius);
        shapeType = ShapeType.CIRCLE;
        return ellipse;
    }

    public int getNbOfEdges() {
        return nbOfEdges;
    }

    public boolean ellipseContainPoints(int x, int y){
        return ellipse.contains(x, y);
    }

    public boolean polygonContainPoints(int x, int y){
        return polygon.contains(x, y);
    }

    public ShapeType getShapeType() {
        return shapeType;
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public int getRadius() {
        return radius;
    }
}
