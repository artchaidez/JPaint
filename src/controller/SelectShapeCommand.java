package controller;

import view.interfaces.IDrawShape;
import model.interfaces.IApplicationState;
import model.ShapeInfo;
import model.interfaces.IShapeListSubject;

import java.awt.*;

/*Check-In 2. When shapes are altered in any way (aka operations), having them as a Command
* class is way to go. Lecture notes point out you can undo/redo operations easily in
* command classes.*/
public class SelectShapeCommand implements ICommand {
    private final IShapeListSubject selectedShapeList;
    private final IApplicationState appState;
    //Start off with false, If its the right shape, change it to true in loop below
    Boolean isShapeSelected = false;
    private final ShapeInfo shapeInfo;
    private IDrawShape selectedShape;

    public SelectShapeCommand(IApplicationState appState, IShapeListSubject selectedShapeList, ShapeInfo shapeInfo) {
        this.selectedShapeList = selectedShapeList;
        this.appState = appState;
        this.shapeInfo = shapeInfo;
    }

    @Override
    public void run() {

        /* Plan was to use shape.containsStart(appState.getCalculatedStart())
        * and shape.containsEnd(appState.getCalculatedEnd()) to be the "dragged" collision
        * square. Did not work. Began experimenting and
        * shape.containsStart(appState.getCalculatedEnd()), works with rectangle + ellipse. ->
        * Refactored by changing method name containsStart to selectShapeCollision. Only using one
        * method now. Deleted containsEnd. */
        /* You must slash across the hypotenuse when selecting a triangle, as if making an
        * “X”. For Rectangles+ Ellipses: You have to slash when selecting,
        * as / or \. Cannot drag left+right, up+down. Overall, very inconsistent
        * to select any shape. */

        /* Prof said to iterate through all shapes. */
        for (IDrawShape shape : selectedShapeList.CanvasArray()) {
            if (shape.selectShapeCollision(appState.getCalculatedEnd()) ){
                isShapeSelected = true;
                selectedShape = shape;
                selectedShapeList.addSelectedArray(selectedShape);

                //Trying to figure out when and what is selected
                System.out.println("Shape selected: " + selectedShapeList.selectedShapesArray());
                
                break;
            } else {
                isShapeSelected = false;
            }

        }

        /* If there was no shape in collision, clear everything. Prof said we cannot "stack"
        * multiple click and drags. -> seems to work most of the time*/
        if (!isShapeSelected) {
            selectedShapeList.clearSelectedArray();
            //Check-In 3. To deselect shapes
            selectedShapeList.getClipBoard().clear();
        }
    }
}