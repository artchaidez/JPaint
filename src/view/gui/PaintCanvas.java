package view.gui;

import model.interfaces.IShapeListObserver;
import model.interfaces.IShapeListSubject;
import view.interfaces.IDrawShape;

import javax.swing.JComponent;
import java.awt.*;


public class PaintCanvas extends JComponent implements IShapeListObserver {
    //Added in Check-In 2
    private final IShapeListSubject shapeList;

    public PaintCanvas(IShapeListSubject shapeList) {
        this.shapeList = shapeList;
        shapeList.register(this);
    }

    @Override
    public void update() {
        repaint();
    }

    @Override
    public void paintComponent(Graphics draw) {
        for (IDrawShape shape : shapeList.CanvasArray()) {
            shape.paint(draw);
        }
    }

    public void graphics2D(Graphics draw) {
        for (IDrawShape shape: shapeList.CanvasArray()) {
            shape.paint((draw));
        }
    }

    //Given by Prof
    public Graphics2D getGraphics2D() {
        return (Graphics2D) getGraphics();
    }

}
