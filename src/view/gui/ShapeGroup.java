package view.gui;

import controller.Point;
import model.ShapeInfo;
import model.ShapeList;
import model.interfaces.IShapeListSubject;
import view.interfaces.IDrawShape;

import java.awt.*;
import java.util.ArrayList;

//Tried to use IDraw shape but I could not figure it out.
public class ShapeGroup implements IDrawShape {
    private ArrayList<IDrawShape> groupedShapes;
    private final IShapeListSubject shapeList;
    private final ShapeInfo shapeInfo;
    private IDrawShape groupShape;

    //need shapeList to get selectedShapes, shapeInfo to get X and Y
    public ShapeGroup(IShapeListSubject shapeList, ShapeInfo shapeInfo) {
        this.shapeInfo = shapeInfo;
        this.shapeList = shapeList;
    }

    //could not get this to work
    public void run() {

    }

    @Override
    public void paint(Graphics g) {

    }

    //Prof stated no shape Collision should happen?
    @Override
    public boolean selectShapeCollision(Point ePoint) {
        return false;
    }

    @Override
    public Point getStartPoint() {
        return null;
    }

    @Override
    public void addX(int dx) {

    }

    @Override
    public void addY(int dy) {

    }

    @Override
    public ShapeInfo getShapeInfo() {
        return null;
    }
}
