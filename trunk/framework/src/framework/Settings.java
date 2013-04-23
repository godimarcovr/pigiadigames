/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

/**
 *
 * @author muram_000
 */
public class Settings {

    static float ScaleRatioX = 0.03f;
    static float ScaleRatioY = 0.03f;

    public static enum Shapes {

        CircularShape,
        SquareShape,
        PolygonShape
    }

    public static enum DebugColor {

        Normal,
        Restitution,
        Friction,
        Density
    }
}
