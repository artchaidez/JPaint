package controller;


//Needed for Check-In 1. Couldn't get it to work until check-in 2
public class UndoCommand implements ICommand {

    //Command classes will use undo. When called, put it into CommandHistory
    @Override
    public void run() {
        CommandHistory.undo();
    }
}
