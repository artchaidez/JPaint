package view.gui;

import controller.Point;
import model.interfaces.IApplicationState;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import controller.MoveShapeCommand;
import model.interfaces.IShapeListSubject;
import model.ShapeInfo;

//Made in Check-In 2
public class MoveMouseAdapter extends MouseAdapter {
    private final IApplicationState appState;
    private final IShapeListSubject shapeList;
    private final ShapeInfo shapeInfo;

    public MoveMouseAdapter(IApplicationState appState, IShapeListSubject shapeList, ShapeInfo shapeInfo) {
        this.appState = appState;
        this.shapeList = shapeList;
        this.shapeInfo = shapeInfo;
    }

    //Only need mousePressed and MouseReleased
    @Override
    public void mousePressed(MouseEvent e) {
        Point startPoint = new Point(e.getX(), e.getY());
        appState.clickedStartPoint(startPoint);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Point endPoint = new Point(e.getX(), e.getY());
        //Use Command to move shape
        appState.clickedEndPoint(endPoint);
        MoveShapeCommand newMove = new MoveShapeCommand(appState, shapeList, shapeInfo);
        newMove.run();
    }
}
