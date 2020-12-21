package view.gui;

import controller.Point;
import controller.ColorSingleton;
import model.ShapeInfo;
import model.ShapeShadingType;
import view.interfaces.IDrawShape;

import java.awt.*;

//Check-In 2
/* Issue of SelectShapeCommand may not be working because points work different here
 * compared to the other shapes?*/
public class DrawTriangle implements IDrawShape {

    private final ShapeInfo shapeInfo;
    private final ShapeShadingType shapeShadingType;
    private final Color primaryColor;
    private final Color secondaryColor;
    //Did not name these points correctly!
    private final Point calculatedStart;
    private final Point calculatedEnd;
    private final Point startPoint;
    //use an array for point x and point y
    private final int[] x = new int[3];
    private final int[] y = new int[3];
    private  final Point endPoint;
    private final int width;
    private final int height;

    public DrawTriangle(ShapeInfo shapeInfo) {
        this.shapeInfo = shapeInfo;
        this.shapeShadingType = shapeInfo.getShadingType();
        //changed in check-in 4
        this.primaryColor = ColorSingleton.getInstanceColor(shapeInfo.getPrimaryColor());
        this.secondaryColor = ColorSingleton.getInstanceColor(shapeInfo.getSecondaryColor());
        this.calculatedStart = shapeInfo.getCalculatedStart();
        this.calculatedEnd = shapeInfo.getCalculatedEnd();
        this.startPoint = shapeInfo.getStartPoint();
        this.endPoint = shapeInfo.getEndPoint();

        /* Did not follow exact instructions of Prof/ Classmate. Works now.*/
        x[0] = shapeInfo.getStartPoint().getX();
        x[1] = shapeInfo.getEndPoint().getX();
        x[2] = shapeInfo.getStartPoint().getX();
        y[0] = shapeInfo.getStartPoint().getY();
        y[1] = shapeInfo.getEndPoint().getY();
        y[2] = shapeInfo.getEndPoint().getY();

        this.width = shapeInfo.getWidth();
        this.height = shapeInfo.getHeight();
    }

    /* Prof recommended having all three shading types in each DrawShape, using some if or switch
     * statements*/
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        switch (shapeShadingType.toString()) {
            case "OUTLINE":
                g.setColor(primaryColor);
                g2.setStroke(new BasicStroke(5));
                g.drawPolygon(x, y, 3);
                break;
            case "FILLED_IN":
                g.setColor(secondaryColor);
                g2.setStroke(new BasicStroke(5));
                g.fillPolygon(x, y, 3);
                break;
            case "OUTLINE_AND_FILLED_IN":
                g.setColor(primaryColor);
                g2.setStroke(new BasicStroke(5));
                g.drawPolygon(x, y, 3);
                /* not working right. Entire shape is colored as secondaryColor ->
                 * figured it out. g.setColor.secondaryColor must come after drawPolygon*/
                g.setColor(secondaryColor);
                g.fillPolygon(x, y, 3);
                break;
        }

    }

    /* You must slash across the hypotenuse when selecting a triangle, as if making an “X”.
    * This works better than what is commented out above */
    @Override
    public boolean selectShapeCollision(Point ePoint) {
      return ( x[0] < ePoint.getX()   + shapeInfo.getWidth() &&
              y[0] < ePoint.getY() + shapeInfo.getHeight()&&
              x[1]   + shapeInfo.getWidth()> ePoint.getX() &&
              y[1] + shapeInfo.getHeight() > ePoint.getY());
    }

    public Point getStartPoint() {
        return startPoint;
    }

    @Override
    public void addX(int dx) {
        this.x[0] = calculatedStart.getX() + dx;
        this.x[1] = calculatedEnd.getX() + dx;
        this.x[2] = calculatedStart.getX() + dx;
    }

    @Override
    public void addY(int dy) {
        this.y[0] = calculatedStart.getY() + dy;
        this.y[1] = calculatedEnd.getY() + dy;
        this.y[2] = calculatedEnd.getY() + dy;
    }

    public ShapeInfo getShapeInfo() {
        return shapeInfo;
    }
}


