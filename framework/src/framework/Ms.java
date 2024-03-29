/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import org.lwjgl.input.Mouse;

/**
 *
 * @author matteo
 */
public class Ms {

    private static Position lPosition = new Position();
    private static boolean lClicked;
    private static long lTime;
    private static Position rPosition = new Position();
    private static boolean rClicked;
    private static long rTime;
    public static float angle = 0;

    public static float getX() {
        return Mouse.getX();
    }

    public static float getY() {
        return Mouse.getY();
    }

    public static Position getPosition() {

        return new Position(Mouse.getX(), Mouse.getY());
    }

    public static boolean isClicked() {
        if (Mouse.isButtonDown(0)) {
            lClicked = true;
            lPosition = getPosition();
            return true;
        } else {
            lClicked = false;
            return false;
        }
    }

    public static boolean wasClicked() {
        if (lTime > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static Position whereWasClicked() {
        return lPosition;
    }

    public static long clickedTime() {
        return lTime;
    }

    public static boolean isRClicked() {
        if (Mouse.isButtonDown(1)) {
            rClicked = true;
            rPosition = getPosition();
            return true;
        } else {
            rClicked = false;
            return false;
        }
    }

    public static boolean wasRClicked() {
        if (rTime > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static Position whereWasRClicked() {
        return rPosition;
    }

    public static long clickedRTime() {
        return rTime;
    }

    public static void lControl(int delta) {

        if (lClicked) {
            lTime += delta;
        } else {
            lTime = 0;
        }
        isClicked();
    }

    public static void rControl(int delta) {

        if (rClicked) {
            rTime += delta;
        } else {
            rTime = 0;
        }
        isRClicked();
    }

    public static void update(int delta) {

        lControl(delta);
        rControl(delta);
    }

    public static float getAngle(float posX, float posY) {
        if (Window.debug)
        {
            
            System.out.println("AND");
        }
        double deltaX = (Window.w / 2)- Ms.getX();
        double deltaY =(Window.h / 2)- Ms.getY();

        double xMouse = Ms.getX() - posX;
        double yMouse = Ms.getY() - posY;
        if (xMouse >= 0) {
            angle = ((float) Math.toDegrees((float) Math.atan(yMouse / xMouse)));
        } else {
            angle = (180 - (float) Math.toDegrees(-(float) Math.atan(yMouse / xMouse)));
        }
        //angle = (float)-(Math.atan2(yMouse, xMouse)*180/Math.PI);
        return angle;
    }
}
