package view.gui;

import controller.Point;
import controller.ColorSingleton;
import model.ShapeInfo;
import model.ShapeShadingType;
import view.interfaces.IDrawShape;

import java.awt.*;

//Check-In 1. Couldn't get it to work. Works in Check-In 2
public class DrawRectangle implements IDrawShape {

    private final ShapeInfo shapeInfo;
    private final ShapeShadingType shapeShadingType;
    private final Color primaryColor;
    private final Color secondaryColor;
    private final int width;
    private final int height;
    private final Point calculatedStart;
    private final Point calculatedEnd;
    private final Point startPoint;
    private final Point endPoint;

    public DrawRectangle(ShapeInfo shapeInfo) {
        this.shapeInfo = shapeInfo;
        this.shapeShadingType = shapeInfo.getShadingType();
        //changed in check-in 4
        this.primaryColor = ColorSingleton.getInstanceColor(shapeInfo.getPrimaryColor());
        this.secondaryColor = ColorSingleton.getInstanceColor(shapeInfo.getSecondaryColor());
        this.width = shapeInfo.getWidth();
        this.height = shapeInfo.getHeight();
        this.calculatedStart = shapeInfo.getCalculatedStart();
        this.calculatedEnd = shapeInfo.getCalculatedEnd();
        this.startPoint = shapeInfo.getStartPoint();
        this.endPoint = shapeInfo.getEndPoint();
    }

    /* Prof recommended having all three shading types in each DrawShape, using some if or switch
     * statements*/
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        /* Need to check if Shading type is outline, filled in, or outline and filled in.
         * Then, get colors, setStroke and draw. */
        switch (shapeShadingType.toString()) {
            case "OUTLINE":
                g.setColor(primaryColor);
                g2.setStroke(new BasicStroke(5));
                g.drawRect(calculatedStart.getX(), calculatedStart.getY(), width, height);
                break;
            case "FILLED_IN":
                g.setColor(secondaryColor);
                g2.setStroke(new BasicStroke(5));
                g.fillRect(calculatedStart.getX(), calculatedStart.getY(), width, height);
                break;
            case "OUTLINE_AND_FILLED_IN":
                g.setColor(primaryColor);
                g2.setStroke(new BasicStroke(5));
                g.drawRect(calculatedStart.getX(), calculatedStart.getY(), width, height);
                /* not working right. Entire shape is colored as secondaryColor ->
                 * figured it out. g.setColor.secondaryColor must come after drawRect*/
                g.setColor(secondaryColor);
                g.fillRect(calculatedStart.getX(), calculatedStart.getY(), width, height);
                break;
        }

    }

    //figured how selectShapeCommand works
    public boolean selectShapeCollision(Point ePoint) {
        return (ePoint.getX() + shapeInfo.getWidth()  >calculatedStart.getX()  &&
                ePoint.getY() + shapeInfo.getHeight() > calculatedStart.getY()  &&
                ePoint.getX() > calculatedStart.getX() + shapeInfo.getWidth() &&
                ePoint.getY() > calculatedStart.getY() + shapeInfo.getHeight());
    }

    public Point getStartPoint() {
        return startPoint;
    }

    @Override
    public void addX(int dx) {
        calculatedStart.setX(calculatedStart.getX() + dx);
        calculatedEnd.setX(calculatedEnd.getX() + dx);
    }

    @Override
    public void addY(int dy) {
        calculatedStart.setY(calculatedStart.getY() + dy);
        calculatedEnd.setY(calculatedEnd.getY() + dy);
    }

    public ShapeInfo getShapeInfo() {
        return shapeInfo;
    }
}

