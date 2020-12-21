package controller;

import model.ShapeColor;

import java.awt.*;
import java.util.EnumMap;

//For Check-In 4
/* I was doing a similar thing in ShapeColor already, but I moved it to its own class.
* Singleton is a design pattern. Made some changes to fit what a singleton is.
* There are several ways a singleton can work.*/

public class ColorSingleton {

    //constructor must empty and private
    private ColorSingleton() {
    }

    //object must be only exist here, be private and final
    //Use a map for all the colors, needs to be an EnumMap for enums
    private static final EnumMap<ShapeColor, Color> enumMapColor;

    //do not need the switch statement anymore, put them into the Map
    //initialize instance within a static block
    //colors are put in within a static block too
    static {
        enumMapColor = new EnumMap<>(ShapeColor.class);
        enumMapColor.put(ShapeColor.BLACK, Color.BLACK);
        enumMapColor.put(ShapeColor.BLUE, Color.BLUE);
        enumMapColor.put(ShapeColor.CYAN, Color.CYAN);
        enumMapColor.put(ShapeColor.DARK_GRAY, Color.DARK_GRAY);
        enumMapColor.put(ShapeColor.GRAY, Color.GRAY);
        enumMapColor.put(ShapeColor.GREEN, Color.GREEN);
        enumMapColor.put(ShapeColor.LIGHT_GRAY, Color.LIGHT_GRAY);
        enumMapColor.put(ShapeColor.MAGENTA, Color.MAGENTA);
        enumMapColor.put(ShapeColor.ORANGE, Color.ORANGE);
        enumMapColor.put(ShapeColor.PINK, Color.PINK);
        enumMapColor.put(ShapeColor.RED, Color.RED);
        enumMapColor.put(ShapeColor.WHITE, Color.WHITE);
        enumMapColor.put(ShapeColor.YELLOW, Color.YELLOW);
    }

    //one getInstance method, public and static. this how drawShapes get colors
    public static Color getInstanceColor(ShapeColor enumColor) {
        return enumMapColor.get(enumColor);
    }
}

