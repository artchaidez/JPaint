package model;

import model.interfaces.IShapeListObserver;
import model.interfaces.IShapeListSubject;
import view.interfaces.IDrawShape;

import java.util.ArrayList;

/*Check-In 2. Classmates and Prof keep talking about shapeList for part 1, did not understand
 * what they meant. Use arrays. Prof said on D2L to use an Observer? */

public class ShapeList implements IShapeListSubject {
    private final ArrayList<IDrawShape> canvasArray;    //renamed shapeArray to canvasArray
    private final ArrayList<IShapeListObserver> observers;
    private final ArrayList<IDrawShape> selectedShapesArray;
    private final ArrayList<IDrawShape> clipBoard;

    public ShapeList() {
        canvasArray = new ArrayList<>();
        selectedShapesArray = new ArrayList<>();
        observers = new ArrayList<>();
        clipBoard = new ArrayList<>();
    }

    @Override
    public void register(IShapeListObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObserver() {
        for (IShapeListObserver newObserver : observers) {
            newObserver.update();
        }
    }

    public void add(IDrawShape shapes) {
        canvasArray.add(shapes);
        notifyObserver();
    }

    public void remove(IDrawShape shape) {
        canvasArray.remove(shape);
        notifyObserver();
    }

    public ArrayList<IDrawShape> CanvasArray() {
        return canvasArray;
    }

    public void addSelectedArray(IDrawShape shapes) {
        selectedShapesArray.add(shapes);
    }

    public void clearSelectedArray() {
        selectedShapesArray.clear();
    }

    public ArrayList<IDrawShape> selectedShapesArray() {
        return selectedShapesArray;
    }

    //Check-In 3. Needed for Copy+Paste Command
    @Override
    public void createClipBoardShape(IDrawShape shape) { clipBoard.add(shape); }

    @Override
    public ArrayList<IDrawShape> getClipBoard() {
        return clipBoard;
    }


}
