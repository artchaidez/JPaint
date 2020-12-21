package model.persistence;

import controller.Point;
import model.*;
import model.dialogs.DialogProvider;
import model.interfaces.IApplicationState;
import model.interfaces.IDialogProvider;
import view.interfaces.IUiModule;
import view.interfaces.IMouseAdapterObserver;

import java.util.ArrayList;

public class ApplicationState implements IApplicationState {
    //Prof gave us these
    private final IUiModule uiModule;
    private final IDialogProvider dialogProvider;
    private ShapeType activeShapeType;
    private ShapeColor activePrimaryColor;
    private ShapeColor activeSecondaryColor;
    private ShapeShadingType activeShapeShadingType;
    private MouseMode activeMouseMode;
    //Added in Check-In 1: Needed for MouseAdapter
    private Point startPoint;
    private Point endPoint;
    //Need to calculate X and Y sizes for shapes
    private Point calculatedStart;
    private Point calculatedEnd;
    private final ArrayList<IMouseAdapterObserver> mouseObservers = new ArrayList<>();

    //Check-In 2. Can easily store shape info and retrieve it when needed
    public ShapeInfo getCurrentShapeInfo() {
        ShapeInfo shapeInfo = new ShapeInfo();
        shapeInfo.setPrimaryColor(activePrimaryColor);
        shapeInfo.setSecondaryColor(activeSecondaryColor);
        shapeInfo.setShadingType(activeShapeShadingType);
        shapeInfo.setShapeType(activeShapeType);
        shapeInfo.setEndPoint(endPoint);
        shapeInfo.setStartPoint(startPoint);
        shapeInfo.setCalculatedStart();
        shapeInfo.setCalculatedEnd();
        shapeInfo.setWidth();
        shapeInfo.setHeight();
        return shapeInfo;
    }

    //Haven't changed this
    public ApplicationState(IUiModule uiModule) {
        this.uiModule = uiModule;
        this.dialogProvider = new DialogProvider(this);
        setDefaults();
    }

    //moved this up
    private void setDefaults() {
        activeShapeType = ShapeType.RECTANGLE;
        activePrimaryColor = ShapeColor.BLUE;
        activeSecondaryColor = ShapeColor.GREEN;
        activeShapeShadingType = ShapeShadingType.FILLED_IN;
        activeMouseMode = MouseMode.DRAW;
    }

    @Override
    public void setActiveShape() {
        activeShapeType = uiModule.getDialogResponse(dialogProvider.getChooseShapeDialog());
    }

    @Override
    public void setActivePrimaryColor() {
        activePrimaryColor = uiModule.getDialogResponse(dialogProvider.getChoosePrimaryColorDialog());
    }

    @Override
    public void setActiveSecondaryColor() {
        activeSecondaryColor = uiModule.getDialogResponse(dialogProvider.getChooseSecondaryColorDialog());
    }

    @Override
    public void setActiveShadingType() {
        activeShapeShadingType = uiModule.getDialogResponse(dialogProvider.getChooseShadingTypeDialog());
    }

    @Override
    public void setActiveStartAndEndPointMode() {
        activeMouseMode = uiModule.getDialogResponse(dialogProvider.getChooseStartAndEndPointModeDialog());
        notifyObservers(); //does not switch to select without this
    }

    @Override
    public ShapeType getActiveShapeType() {
        return activeShapeType;
    }

    @Override
    public ShapeColor getActivePrimaryColor() {
        return activePrimaryColor;
    }

    @Override
    public ShapeColor getActiveSecondaryColor() {
        return activeSecondaryColor;
    }

    @Override
    public ShapeShadingType getActiveShapeShadingType() {
        return activeShapeShadingType;
    }

    @Override
    public MouseMode getCurrentStartEndPoints() { return activeMouseMode; }

    //From here, things I have added

    //Added in Check-In 1: Needed for MouseAdapter
    //For both sets, need to save where mouse was clicked and released
    public void clickedStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public void clickedEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    //Only need a setHeight
    public void setHeight(int height) {
        getCalculatedStart();
        getCalculatedEnd();
    }

    //Get x+y that was set by MouseAdapter click/release
    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public Point getCalculatedStart() {
        int startX = Math.min(startPoint.getX(), endPoint.getX());
        int startY = Math.min(startPoint.getY(), endPoint.getY());
        calculatedStart = new Point(startX, startY);
        return calculatedStart;
    }

    public Point getCalculatedEnd() {
        int endX = Math.max(startPoint.getX(), endPoint.getX());
        int endY = Math.max(startPoint.getY(), endPoint.getY());
        calculatedEnd = new Point(endX, endY);
        return calculatedEnd;
    }

    @Override
    public void registerObserver(IMouseAdapterObserver o) {
        mouseObservers.add(o);
    }

    @Override
    public void notifyObservers() {
        for (IMouseAdapterObserver observer : mouseObservers) {
            observer.run();
        }
    }
}


