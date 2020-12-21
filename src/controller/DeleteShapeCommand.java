package controller;

import model.ShapeInfo;
import model.interfaces.IApplicationState;
import model.interfaces.IShapeListSubject;
import view.interfaces.IDrawShape;

//added check-in 3
public class DeleteShapeCommand implements ICommand, IUndoable{
    //IntelliJ suggests make ShapeInfo and IApp into local variables but decided not to
    private final ShapeInfo shapeInfo;
    private final IShapeListSubject shapeList;
    private final IApplicationState appState;

    public DeleteShapeCommand (IApplicationState appState, IShapeListSubject shapeList, ShapeInfo shapeInfo) {
        this.shapeInfo = shapeInfo;
        this.shapeList = shapeList;
        this.appState = appState;
    }

    //works now. Code in JPaintController was incorrect
    @Override
    public void run() {
        //shapes were put into an array in selectShapeCommand, can easily remove this way
        for (IDrawShape shape : shapeList.selectedShapesArray()) {
            shapeList.remove(shape);
            shapeList.notifyObserver();
        }
        CommandHistory.add(this);
    }

    //undo and redo uses the same array as run() uses
    @Override
    public void undo() {
        for (IDrawShape shape : shapeList.selectedShapesArray()) {
            shapeList.add(shape);
        }
    }

    //trying using run(), does not work
    @Override
    public void redo() {
        for (IDrawShape shape : shapeList.selectedShapesArray()) {
            shapeList.remove(shape);
        }
    }
}
