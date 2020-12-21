package controller;

/*Added in Check-In 1. Purpose is to redo Jpaint action
 * and document it in CommandHistory*/
//Needed for Check-In 1. Couldn't get it to work until check-in 2
public class RedoCommand implements ICommand {
    //Command classes will use redo. When called, put it into CommandHistory
    @Override
    public void run() {
        CommandHistory.redo();
    }
}
