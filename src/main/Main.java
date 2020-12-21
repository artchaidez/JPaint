package main;

import controller.IJPaintController;
import controller.JPaintController;
import view.gui.MouseObserver;
import model.ShapeInfo;
import model.ShapeList;
import model.interfaces.IApplicationState;
import model.persistence.ApplicationState;
import view.gui.*;
import view.interfaces.IGuiWindow;
import view.interfaces.IUiModule;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        /*Suggestions from Prof: Pass PaintCanvas into constructors. Use Graphics2D only when you
         * are about to draw. Look  below "new GuiWindow(paintCanvas)" */
        ShapeList shapeList = new ShapeList();
        ShapeInfo shapeInfo = new ShapeInfo();
        //May not need to modify PaintCanvas. Only one instance

        PaintCanvas paintCanvas = new PaintCanvas(shapeList);
        //Should not need to modify GuiWindow and Gui
        //Classmate said they made MouseAdapter in GuiWindow and works?
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);

        //Only one instance of applicationState and Controller
        IApplicationState appState = new ApplicationState(uiModule);
        IJPaintController controller = new JPaintController(uiModule, appState, shapeList, shapeInfo);

        MouseObserver mouseObserver = new MouseObserver(appState, paintCanvas, shapeList, shapeInfo);
        mouseObserver.run();
        controller.setup();

    }
}
