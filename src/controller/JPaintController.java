package controller;

import model.ShapeInfo;
import model.interfaces.IApplicationState;
import model.interfaces.IShapeListSubject;
import view.EventName;
import view.interfaces.IUiModule;

//Made by prof
public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final IApplicationState appState;
    private IShapeListSubject shapeList;
    private ShapeInfo shapeInfo;

    public JPaintController(IUiModule uiModule, IApplicationState appState, IShapeListSubject shapeList, ShapeInfo shapeInfo) {
        this.uiModule = uiModule;
        this.appState = appState;
        this.shapeList = shapeList;
        this.shapeInfo = shapeInfo;
    }

    @Override
    public void setup() {
        setupEvents();
    }

    private void setupEvents() {
        uiModule.addEvent(EventName.CHOOSE_SHAPE, () -> appState.setActiveShape());
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, () -> appState.setActivePrimaryColor());
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, () -> appState.setActiveSecondaryColor());
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, () -> appState.setActiveShadingType());
        uiModule.addEvent(EventName.CHOOSE_START_POINT_ENDPOINT_MODE, () -> appState.setActiveStartAndEndPointMode());
        //Need Undo/Redo. Future events should be here too
        uiModule.addEvent(EventName.UNDO, () -> new UndoCommand().run());
        uiModule.addEvent(EventName.REDO, () -> new RedoCommand().run());
        //Check-In 3
        uiModule.addEvent(EventName.COPY, () -> new CopyShapeCommand(appState, shapeList, shapeInfo).run());
        uiModule.addEvent(EventName.PASTE, () -> new PasteShapeCommand(appState,shapeList,shapeInfo).run());
        uiModule.addEvent(EventName.DELETE, () -> new DeleteShapeCommand(appState,shapeList,shapeInfo).run());
        //check-In 4
        //uiModule.addEvent(EventName.GROUP, () -> new GroupShapeCommand(appState,shapeList,shapeInfo).run());
    }
}
