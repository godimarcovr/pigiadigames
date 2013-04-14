/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import gioco.Game;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

/**
 *
 * @author Marco
 */
public class Window {

    public static boolean debug;
    public static int w, h;
    public static Tester game;
    public static Game game2;
    public static Vec2 mSpace;
    public static float conv, invconv;
    public static Float[] bounds = new Float[4];

    public static boolean initialise(int width, int heigth) {
        try {
            Display.setDisplayMode(new DisplayMode(width, heigth));
            w = width;
            h = heigth;
            return true;
        } catch (LWJGLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static World getWorld() {
        return Window.game2.world;
    }

    public static void debugDrawLine(float x, float y, float fx, float fy) {
        if (debug) {
            GL11.glPushMatrix();
            GL11.glTranslatef(0, 0, 0);
            Color.yellow.bind();
            GL11.glBegin(GL11.GL_LINES);
            {
                GL11.glVertex2d(x, y);
                GL11.glVertex2d(fx, fy);
            }
            GL11.glEnd();
            GL11.glPopMatrix();
        }
    }

    public static void debugDrawLine(float x, float y, float fx, float fy, Color color) {
        if (debug) {
            GL11.glPushMatrix();
            GL11.glTranslatef(0, 0, 0);
            color.bind();
            GL11.glBegin(GL11.GL_LINES);
            {
                GL11.glVertex2d(x, y);
                GL11.glVertex2d(fx, fy);
            }
            GL11.glEnd();
            GL11.glPopMatrix();
        }
    }

    public static void setMeterSpace(Vec2 value) {
        Window.mSpace = value;
        bounds[0] = -Window.mSpace.x / 2.0f;
        bounds[1] = Window.mSpace.x / 2.0f;
        bounds[2] = -Window.mSpace.y / 2.0f;
        bounds[3] = Window.mSpace.y / 2.0f;
        conv = w / mSpace.x;
        invconv = mSpace.x / w;
    }

    public static void setMeterSpace(float x, float y) {
        Window.mSpace = new Vec2(x, y);
        bounds[0] = -Window.mSpace.x / 2.0f;
        bounds[1] = Window.mSpace.x / 2.0f;
        bounds[2] = -Window.mSpace.y / 2.0f;
        bounds[3] = Window.mSpace.y / 2.0f;
        conv = w / mSpace.x;
        invconv = mSpace.x / w;
    }

    public static Float[] getBoundaries() {
        bounds[0] = -Window.mSpace.x / 2.0f;
        bounds[1] = Window.mSpace.x / 2.0f;
        bounds[2] = -Window.mSpace.y / 2.0f;
        bounds[3] = Window.mSpace.y / 2.0f;
        return bounds;
    }
}
