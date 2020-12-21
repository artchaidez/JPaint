package controller;

import model.ShapeInfo;
import model.interfaces.IApplicationState;
import model.interfaces.IShapeListSubject;
import view.interfaces.IDrawShape;

//added check-in 3
public class PasteShapeCommand implements  ICommand, IUndoable{
    private IApplicationState appState;
    private IShapeListSubject shapeList;
    private ShapeInfo shapeInfo;
    private IDrawShape newShape;

    public PasteShapeCommand(IApplicationState appState, IShapeListSubject shapeList, ShapeInfo shapeInfo) {
        this.appState = appState;
        this.shapeList = shapeList;
        this.shapeInfo = shapeInfo;
    }

    //need to iterate through Clipboard, get shapes, then CreateShapeCommand
    public void run() {

        for (IDrawShape copiedShape : shapeList.getClipBoard() ) {
            newShape = copiedShape;
            //offset the shape, per Prof instructions
            newShape.addX(100);
            newShape.addY(100);

            CreateShapeCommand shape = new CreateShapeCommand(appState, shapeList, newShape.getShapeInfo());

            shapeList.add(shape.shapeFactory.createShape(newShape.getShapeInfo()));

        }
        CommandHistory.add(this);
    }


    @Override
    public void undo() {
        shapeList.remove(newShape);
    }

    //using run() here does not work, worked in MoveShapeCommand/
    @Override
    public void redo() {
        shapeList.add((newShape));
    }
}
