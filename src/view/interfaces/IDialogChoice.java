package view.interfaces;

//From Prof
public interface IDialogChoice<T> {
    String getDialogTitle();

    String getDialogText();

    T[] getDialogOptions();

    T getCurrentSelection();
}
