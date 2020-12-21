package model;

import view.gui.DrawEllipse;
import view.gui.DrawRectangle;
import view.gui.DrawTriangle;
import view.interfaces.IDrawShape;

/* From week 3 lecture. Not a design Pattern according to lecture notes? -> It is.
* This is an abstract factory, put it into the final check-in and report.*/
/* Check-In 2*/
public class ShapeFactory {

    public IDrawShape createShape(ShapeInfo shapeInfo) {
        IDrawShape shape = null;
        ShapeType shapeType = shapeInfo.getShapeType();
        //maybe there is a better way to do this? Re-read about factories

        switch (shapeType.toString()) {
            case "RECTANGLE":
                shape = new DrawRectangle(shapeInfo);
                break;
            case "TRIANGLE":
                shape = new DrawTriangle(shapeInfo);
                break;
            case "ELLIPSE":
                shape = new DrawEllipse(shapeInfo);
                break;
        }

        return shape;
    }
}
