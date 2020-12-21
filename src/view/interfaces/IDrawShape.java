package view.interfaces;

import controller.Point;
import model.ShapeInfo;

import java.awt.*;

//Made in Check-In 1
//When making classes to draw Rectangle and other, they can use these methods.
public interface IDrawShape {
    //Prof said only use graphics2d when making shapes.
    /*Do it in a class drawRectangle? Cannot do much more here, as different shapes
     * are going to work way different.*/
    void paint(Graphics g);

    boolean selectShapeCollision(Point ePoint);

    Point getStartPoint();

    //these two add methods allows move and paste shape commands to work
    void addX(int dx);

    void addY(int dy);

    //needed for pasteShapeCommand.
    ShapeInfo getShapeInfo();


}