package controller;

/* Tried refactoring into controller.interfaces and interfaces folders, gave me errors
* I could not resolve. Import does not seem to want to work.*/
public interface ICommand {
    void run();
}