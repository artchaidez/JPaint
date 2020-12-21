package view.gui;

import java.awt.event.MouseAdapter;

import controller.GroupShapeCommand;
import model.interfaces.IShapeListSubject;
import model.ShapeInfo;
import model.interfaces.IApplicationState;
import java.awt.event.MouseEvent;
import controller.Point;
import controller.SelectShapeCommand;

import javax.swing.*;

//Made in Check-In 2
public class SelectMouseAdapter extends MouseAdapter {
    private final IApplicationState appState;
    private final IShapeListSubject shapeList;
    private final ShapeInfo shapeInfo;

    public SelectMouseAdapter(IApplicationState appState, IShapeListSubject shapeList, ShapeInfo shapeInfo) {
        this.appState = appState;
        this.shapeList = shapeList;
        this.shapeInfo = shapeInfo;
    }

    //Only need mousePressed and MouseReleased
    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            Point startPoint = new Point(e.getX(), e.getY());
            appState.clickedStartPoint(startPoint);
        }
        //check-in 4
        //requires me to use else if, gives me an error that I need ;
        else if (SwingUtilities.isRightMouseButton(e)) {
            System.out.println("Right click: Grouping selected shapes.");
            //ShapeGroup group = new ShapeGroup(shapeList, shapeInfo);
            //group.run();
            //GroupShapeCommand newShape = new GroupShapeCommand(appState, shapeList, shapeInfo);
            //newShape.run();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Point endPoint = new Point(e.getX(), e.getY());
        appState.clickedEndPoint(endPoint);
        //ShapeCommand used
        appState.getCurrentStartEndPoints();
        SelectShapeCommand newSelect = new SelectShapeCommand(appState, shapeList, shapeInfo);
        newSelect.run();
    }


}
