package controller;

import model.interfaces.IShapeListSubject;
import model.ShapeInfo;
import model.interfaces.IApplicationState;
import view.interfaces.IDrawShape;

//added check-in 3
public class CopyShapeCommand implements ICommand {
    private final IApplicationState appState;
    IShapeListSubject selectedShapeList;
    ShapeInfo shapeInfo;

    public CopyShapeCommand(IApplicationState appState, IShapeListSubject selectedShapeList, ShapeInfo shapeInfo) {
        this.appState = appState;
        this.selectedShapeList = selectedShapeList;
        this.shapeInfo = shapeInfo;
    }

    /* Prof states to add shapes to a "ClipBoard"? I need to iterate through my shapeList, add it to
    *  clipboard. Done after that? -> Clipboard will be an array on ShapeList, be used for copy+paste
    * commands. Once a shape is pasted, put in shapelist!*/

    /*Works, but not well. Problem is most likely my SelectShapeCommand. Seems to sometimes only
    * copy first shape made? Also works very poorly with Triangles */
    //for loop line should be the same as the MoveShapeCommand
    @Override
    public void run() {
        for (IDrawShape shape : selectedShapeList.selectedShapesArray()) {
            selectedShapeList.createClipBoardShape(shape);
            /*Trying to figure out when something is copied, as there is no visual on the canvas
            * as of now*/
            System.out.println("Shape copied: " + selectedShapeList.selectedShapesArray());
        }

    }

}