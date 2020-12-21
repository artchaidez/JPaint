package controller;

import model.interfaces.IShapeListSubject;
import model.ShapeInfo;
import model.interfaces.IApplicationState;
import view.interfaces.IDrawShape;
import java.util.ArrayList;

/* Check-In 2. When shapes are altered in any way (aka operations), having them as a Command
 * class is way to go. Lecture notes point out you can undo/redo operations easily in
 * command classes.*/
//the movement is pretty bad, come back to fix it -> Prof says its ok it is imprecise
/* I do select shapes and move them, cannot deselect any? -> Figured it out in SelectShapeCommand */
public class MoveShapeCommand implements ICommand, IUndoable {
    private final IApplicationState appState;
    private final IShapeListSubject selectedShapeList;
    private final ShapeInfo shapeInfo;
    /*Get shape and save it as oldShape. NewShape will be a new shape with the updated
    * coordinates, then remove oldShape. Need to get info of oldShape*/
    private IDrawShape oldShape;
    private IDrawShape newShape;
    private ArrayList<IDrawShape> movedShapesArray;

    public MoveShapeCommand(IApplicationState appState, IShapeListSubject selectedShapeList, ShapeInfo shapeInfo) {
        this.appState = appState;
        this.selectedShapeList = selectedShapeList;
        this.shapeInfo = shapeInfo;
    }

    @Override
    public void run() {
        movedShapesArray = new ArrayList<IDrawShape>();

        /* Prof instructions say moving the shape must be done be dragging*/
        int width = appState.getEndPoint().getX() - appState.getStartPoint().getX();
        int height = appState.getEndPoint().getY() - appState.getStartPoint().getY();

        //Triangles bug: seems to be in DrawTriangle.selectShapeCollision method -> works better
        for (IDrawShape collidedShape : selectedShapeList.selectedShapesArray()) {
            oldShape = collidedShape;
            movedShapesArray.add(oldShape);
            selectedShapeList.remove(oldShape);

            //use calculation from above move newShapes
            for (IDrawShape tempShape : movedShapesArray) {
                tempShape.addX(width);
                tempShape.addY(height);
                newShape = tempShape;
                selectedShapeList.add(newShape);
            }

        }
        CommandHistory.add(this);

    }

    /* For both undo and redo, I tried variations of what was done in run. Somewhat works. */
    //Changing my redo to simply do run() works well.
    @Override
    public void undo() {

        int width = appState.getEndPoint().getX() - appState.getStartPoint().getX();
        int height = appState.getEndPoint().getY() - appState.getStartPoint().getY();

            for (IDrawShape tempShape : movedShapesArray) {
                tempShape.addX(-width);
                tempShape.addY(-height);
                newShape = tempShape;
                selectedShapeList.add(newShape);
            }
    }

    //Works to a degree -> Works better just doing run();
    @Override
    public void redo() {
        run();
    }
}
