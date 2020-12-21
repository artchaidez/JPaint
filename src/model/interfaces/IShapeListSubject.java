package model.interfaces;

import view.interfaces.IDrawShape;

import java.util.ArrayList;

/*Check-In 2. Pattern from Lecture 4*/

public interface IShapeListSubject {
    /* add and remove used in command classes under controller folder*/
    void add(IDrawShape shape);

    //check-in 4. Prof suggested to remove shape from main list
    void remove(IDrawShape shape);

    /* renamed getShapesArray to CanvasArray. Confusion with getSelectedShapesArray
    renamed from getSelectedShapesArray to selectedShapesArray
    this is the array where shapes are put into and made on PaintCanvas */
    ArrayList<IDrawShape> CanvasArray();

    //register and notify needed under Observer Pattern
    void register(IShapeListObserver paintCanvas);

    void notifyObserver();

    //Using two arrays, one for selectShape
    //this array is for SelectedShapeCommand.
    void addSelectedArray(IDrawShape shapes);

    //renamed from getSelectedShapesArray to selectedShapesArray
    ArrayList<IDrawShape> selectedShapesArray();

    void clearSelectedArray();

    //Check-In 3. Needed for Copy+Paste Command
    /*This Array gets its shapes from selectedShapesArray in selectShapeCommand*/
    void createClipBoardShape(IDrawShape shape);

    ArrayList<IDrawShape> getClipBoard() ;


}
