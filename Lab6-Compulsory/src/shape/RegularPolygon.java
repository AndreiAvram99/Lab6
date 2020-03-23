package shape;

import java.awt.*;

/**
 * RegularPolygonClass
 * @author avram
 */

public class RegularPolygon extends Polygon {
    public RegularPolygon(int x0, int y0, int radius, int nbOfEdges) {
        double alpha = 2 * Math.PI / nbOfEdges;
        for (int i = 0; i < nbOfEdges; i++) {
            double x = x0 + radius * Math.cos(alpha * i);
            double y = y0 + radius * Math.sin(alpha * i);
            this.addPoint((int) x, (int) y);
        }
    }
}
