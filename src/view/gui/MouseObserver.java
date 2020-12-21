package view.gui;

import model.ShapeInfo;
import model.MouseMode;
import model.interfaces.IApplicationState;
import model.interfaces.IShapeListSubject;
import view.interfaces.IMouseAdapterObserver;
import javax.swing.*;
import java.awt.event.MouseListener;

//Check-In 2
public class MouseObserver extends JFrame implements IMouseAdapterObserver {
    private final IApplicationState appState;
    private final PaintCanvas paintCanvas;
    private final IShapeListSubject shapeList;
    private final ShapeInfo shapeInfo;

    public MouseObserver(IApplicationState appState, PaintCanvas paintCanvas, IShapeListSubject shapeList, ShapeInfo shapeInfo) {
        this.appState = appState;
        this.paintCanvas = paintCanvas;
        this.shapeList = shapeList;
        this.shapeInfo = shapeInfo;
        appState.registerObserver(this);
    }

    public void run() {
        MouseListener[] listenToMouse = paintCanvas.getMouseListeners();
        for (MouseListener mouseListener : listenToMouse) {
            paintCanvas.removeMouseListener(mouseListener);
        }
        MouseMode mouseMode = appState.getCurrentStartEndPoints();

        if (mouseMode.equals(MouseMode.DRAW)) {
            paintCanvas.addMouseListener(new DrawMouseAdapter(appState, shapeList, shapeInfo));
        } else if (mouseMode.equals(MouseMode.SELECT)) {
            paintCanvas.addMouseListener(new SelectMouseAdapter(appState, shapeList, shapeInfo));
        } else if (mouseMode.equals(MouseMode.MOVE)) {
            paintCanvas.addMouseListener(new MoveMouseAdapter(appState, shapeList, shapeInfo));
        }
    }
}
