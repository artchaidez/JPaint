package view.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import controller.CreateShapeCommand;
import controller.Point;
import model.ShapeColor;
import model.interfaces.IShapeListSubject;
import model.ShapeInfo;
import model.interfaces.IApplicationState;

//Added for Check-In 1
/* Prof said we need MouseAdapter, not MouseListener. He said to put this in the controller folder,
* but works just fine here. */
public class DrawMouseAdapter extends MouseAdapter {
    private final IApplicationState appState;
    private final IShapeListSubject shapeList;
    private final ShapeInfo shapeInfo;
    ArrayList<ShapeColor> shapeColor = new ArrayList();

    //Make a Class that makes shapes? -> Separated each shape as its own class
    public DrawMouseAdapter(IApplicationState appState, IShapeListSubject shapeList, ShapeInfo shapeInfo) {
        this.appState = appState;
        this.shapeList = shapeList;
        this.shapeInfo = shapeInfo;
    }

    //Prof said to use Adapter because we only need 2 methods
    //need to override them with what we need
    //Need to get colors? -> took classmates suggestion to do this in mousePressed

    //set x and y where mouse was pressed
    @Override
    public void mousePressed(MouseEvent e) {
        Point startPoint = new Point(e.getX(), e.getY());
        appState.clickedStartPoint(startPoint);
        ShapeColor primaryColor = appState.getActivePrimaryColor();
        shapeColor.add(primaryColor);
        ShapeColor secondaryColor = appState.getActiveSecondaryColor();
        shapeColor.add(secondaryColor);
    }

    //set x and y where mouse was released
    @Override
    public void mouseReleased(MouseEvent e) {
        Point endPoint = new Point(e.getX(), e.getY());
        appState.clickedEndPoint(endPoint);
        appState.getCurrentStartEndPoints();
        CreateShapeCommand newShape = new CreateShapeCommand(appState, shapeList, shapeInfo);
        newShape.run();
    }
}
