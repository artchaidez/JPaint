package controller;

import model.ShapeFactory;
import model.ShapeInfo;
import model.interfaces.IApplicationState;
import model.interfaces.IShapeListSubject;
import view.interfaces.IDrawShape;

import java.util.ArrayList;

//created in Check-In 4
/* My thought process is use the SelectMouseAdapter. After dragging on bunch of shapes,
* right click it will group command and put it as one big shape. */
public class GroupShapeCommand implements ICommand, IUndoable {
    ShapeFactory shapeFactory = new ShapeFactory();
    private final IApplicationState appState;
    private ShapeInfo shapeInfo;
    private final IShapeListSubject shapeList;
    private IDrawShape newShape;
    private ArrayList<IDrawShape> groupedShapes;


    //Prof said to use shapeList as a parameter
    //All commands should have appState, shapeList. ShapeInfo too?
    public GroupShapeCommand(IApplicationState appState, IShapeListSubject shapeList, ShapeInfo shapeInfo) {
        this.appState = appState;
        this.shapeList = shapeList;
        this.shapeInfo = shapeInfo;
    }

    @Override
    public void run() {
        groupedShapes = new ArrayList<>();
        int leftMostPoint = 0;
        int rightMostPoint = 0;

        for (IDrawShape shape : shapeList.selectedShapesArray()) {
            appState.getCalculatedStart();
            shape.getShapeInfo().getCalculatedStart();
            //create left and right bound
            //not sure how to use this, Prof mentioned this
            leftMostPoint = Math.min(shape.getShapeInfo().getCalculatedStart().getX(), leftMostPoint );
            rightMostPoint = Math.min(shape.getShapeInfo().getCalculatedEnd().getX(), rightMostPoint );

            //add shape to new group
            groupedShapes.add(shape);
            //remove shape
            this.shapeList.remove(shape);

        }

        //iterate and put them back into main list? I can't put an array into shape
        for (IDrawShape shape : groupedShapes) {
            this.shapeList.add(shape);
        }
    }

    public void addChild (IDrawShape shape) { groupedShapes.add(shape);}

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}
