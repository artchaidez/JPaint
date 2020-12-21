package controller;

//Added in Check-In 1
//Needed for CommandHistory, in controller folder
    public interface IUndoable {
        void undo();
        void redo();
    }

