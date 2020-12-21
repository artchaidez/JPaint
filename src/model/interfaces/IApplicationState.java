package model.interfaces;

import controller.Point;
import model.*;
import view.interfaces.IMouseAdapterObserver;

public interface IApplicationState {
    void setActiveShape();

    void setActivePrimaryColor();

    void setActiveSecondaryColor();

    void setActiveShadingType();

    void setActiveStartAndEndPointMode();

    ShapeType getActiveShapeType();

    ShapeColor getActivePrimaryColor();

    ShapeColor getActiveSecondaryColor();

    ShapeShadingType getActiveShapeShadingType();

    //Everything above was given by Prof

    //Check-In 1
    void clickedStartPoint(Point startPoint);

    void clickedEndPoint(Point endPoint);

    /*Need variables to retrieve points in certain situations */
    Point getStartPoint();

    Point getEndPoint();

    //needed it for check-in 1, implemented in Check-In 2
    Point getCalculatedStart();

    Point getCalculatedEnd();

    void setHeight(int height);

    /*Check-In 2. */

    MouseMode getCurrentStartEndPoints();

    ShapeInfo getCurrentShapeInfo();

    //ApplicationState needs register and notify observer
    void registerObserver(IMouseAdapterObserver o);

    void notifyObservers();

}
