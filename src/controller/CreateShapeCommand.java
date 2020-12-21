package controller;

import model.interfaces.IShapeListSubject;
import model.ShapeInfo;
import model.ShapeFactory;
import model.interfaces.IApplicationState;
import view.interfaces.IDrawShape;

import java.util.ArrayList;

//Added in Check-In 1
/* When shapes are altered in any way (aka operations), having them as a Command
* class is way to go. Lecture notes point out you can undo/redo operations easily in
* command classes. From Lecture 3*/
public class CreateShapeCommand implements ICommand, IUndoable {
    ShapeFactory shapeFactory = new ShapeFactory();
    private final IApplicationState appState;
    private ShapeInfo shapeInfo;
    private final IShapeListSubject shapeList;
    private IDrawShape shape;

    //Prof said to use shapeList as a parameter
    //All commands should have appState, shapeList. ShapeInfo too?
    public CreateShapeCommand(IApplicationState appState, IShapeListSubject shapeList, ShapeInfo shapeInfo) {
        this.appState = appState;
        this.shapeList = shapeList;
        this.shapeInfo = shapeInfo;
    }

    //do what here? -> Figured it out Check-In 2
    @Override
    public void run() {
        shapeInfo = appState.getCurrentShapeInfo();
        shape = shapeFactory.createShape(shapeInfo);
        this.shapeList.add(shape);
        CommandHistory.add(this);
    }
    //need to override undo/redo
    @Override
    public void undo() {
        shapeList.remove(shape);
    }

    @Override
    public void redo() {
        shapeList.add(shape);
    }


}
