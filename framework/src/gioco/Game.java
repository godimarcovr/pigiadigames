/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gioco;

import framework.Controls;
import framework.Entity;
import framework.EntityCensus;
import framework.FontHandler;
import framework.Kb;
import framework.Map;
import framework.Ms;
import framework.Player;
import framework.Settings;
import framework.TimerHandler;
import framework.Window;
import java.awt.Font;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.newdawn.slick.Color;

/**
 *
 * @author Marco
 */
public class Game {

    public Map map;
    public long lastFrame;
    public int fps;
    public long lastFPS;
    public World world;
    public int velIt = 6, posIt = 2;
    public Entity e2;
    public int FixedFps = 120;
    public Player pl;
    public boolean matrixMovement = true;
    int f1;

    public Game() {
    }

    public void start() {
        // init OpenGL here
        boolean success = Window.initialise(1024, 768);
        Window.setMeterSpace(20, 15);
        Window.game2 = this;
        //  Window.debug = true;
        try {
            Display.create();
        } catch (LWJGLException ex) {
            ex.printStackTrace();
        }
        //menuInitialize();//Menu initialize
        initGL(); // init OpenGL
        getDelta(); // call once before loop to initialise lastFrame
        lastFPS = getTime(); // call before loop to initialise fps timer

        /**
         * ********************************************************************************************************
         */
        this.world = new World(new Vec2(0, 0));
        world.setSleepingAllowed(false);
        this.pl = new Player(0.5f, 5, 5);
        pl.setModifiers(0, 0, 1);

        this.e2 = new Entity(1, 1, 8, 8, 1, 0, 1);
        e2.setModifiers(0, 0, 1);
        map = new Map(50, 50);

        f1 = FontHandler.createFont("Times New Roman", Font.PLAIN, 15);
        Controls.setKeys(new String[]{"W", "S", "A", "D", "SPACE"});

        /**
         * *******************************************************************************************************
         */
        while (!Display.isCloseRequested()) {
            int delta = getDelta();
            Ms.update(delta);
            TimerHandler.update(delta);
            updateFPS();
            update(delta);
            long startTime = System.currentTimeMillis();
            renderGL();
            //   System.out.print(System.currentTimeMillis() - startTime + "\n");
            Display.update();
            Display.sync(FixedFps); // cap fps to 60fps
        }



        Display.destroy();
    }

    private void initGL() {

        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        Float[] bounds = Window.getBoundaries();
        GLU.gluOrtho2D(bounds[0], bounds[1], bounds[2], bounds[3]);
        //GLU.gluOrtho2D(0, Window.w, Window.h ,0);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_TEXTURE_2D);

        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }

    private int getDelta() {
        long time = getTime();
        int delta = (int) (time - lastFrame);
        lastFrame = time;

        return delta;
    }

    private long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

    private void updateFPS() {
        if (getTime() - lastFPS > 1000) {
            Display.setTitle("FPS: " + fps);
            fps = 0;
            lastFPS += 1000;
        }
        fps++;
    }

    public void renderGL() {

        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        map.draw();
        Window.debugDrawLine(0, 0, pl.body.getPosition().x, pl.body.getPosition().y);
        Window.debugDrawStaticString(5, 5, Window.debugColor.toString(), f1);
        Window.debugDrawHudString(5, 5, Window.debugColor.toString(), f1);
        /*        for (Entity entity : EntityCensus.ents) {
         entity.draw();
         }
         //   pl.draw();//********************************/
    }

    public void setVisual() {
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        //settare visuale decentrata sui bordi
        float bordDX;
        float bordSX;
        float bordDOWN;
        float bordUP;
        if (pl.body.getPosition().x - 0 > (Window.mSpace.x / 2)) { //se non vicino a bordo sinistro
            if (Window.game2.map.w - pl.body.getPosition().x < (Window.mSpace.x / 2)) { //se vicino a bordo destro
                bordDX = Window.game2.map.w;

            } else {
                bordDX = pl.body.getPosition().x + Window.bounds[1];
            }
            bordSX = bordDX - Window.mSpace.x;
        } else {
            bordSX = 0;
            bordDX = Window.mSpace.x;
        }

        if (pl.body.getPosition().y - 0 > (Window.mSpace.y / 2)) { //se non vicino a bordo sotto
            if (Window.game2.map.h - pl.body.getPosition().y < (Window.mSpace.y / 2)) { //se vicino a bordo su
                bordUP = Window.game2.map.h;

            } else {
                bordUP = pl.body.getPosition().y + Window.bounds[3];
            }
            bordDOWN = bordUP - Window.mSpace.y;
        } else {
            bordDOWN = 0;
            bordUP = Window.mSpace.y;
        }

        /*GLU.gluOrtho2D(pl.body.getPosition().x - 0 > (Window.mSpace.x / 2) ? pl.body.getPosition().x + Window.bounds[0] : 0, pl.body.getPosition().x + Window.bounds[1], pl.body.getPosition().y + Window.bounds[2]//***********
         , pl.body.getPosition().y + Window.bounds[3]);
         System.out.println(pl.body.getPosition().x + "/////" + Window.bounds[1]);*/
        //System.out.println(pl.body.getPosition().y+"/////"+Window.bounds[2]);, Window.game2.map.w-pl.body.getPosition().x<(Window.mSpace.x/2)?Window.game2.map.w:pl.body.getPosition().x+ Window.bounds[1]
        GLU.gluOrtho2D(bordSX, bordDX, bordDOWN, bordUP);
        Window.setUnfixedBoundaries(bordSX, bordDX, bordDOWN, bordUP);

        GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }

    public void setVisualWithoutBorders() {
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        //settare visuale decentrata sui bordi
        float bordDX;
        float bordSX;
        float bordDOWN;
        float bordUP;

        bordDX = pl.body.getPosition().x + Window.getBoundaries()[1];
        bordSX = pl.body.getPosition().x + Window.getBoundaries()[0];;
        bordDOWN = pl.body.getPosition().y + Window.getBoundaries()[2];;
        bordUP = pl.body.getPosition().y + Window.getBoundaries()[3];;

        GLU.gluOrtho2D(bordSX, bordDX, bordDOWN, bordUP);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);

    }

    public void update(int delta) {
        String read = Kb.getChars();
        if ("F".equals(read)) {
            Window.debug = !Window.debug;
        } else if ("P".equals(read)) {
            if (world.getGravity().y == 9.8f) {
                world.setGravity(new Vec2(0, -9.8f));
            } else if (world.getGravity().y == -9.8f) {
                world.setGravity(new Vec2(0, 0));
            } else {
                world.setGravity(new Vec2(0, 9.8f));
            }
        } else if ("L".equals(read)) {
            if (Window.debugColor == Settings.DebugColor.Density) {
                Window.debugColor = Settings.DebugColor.Friction;
            } else if (Window.debugColor == Settings.DebugColor.Friction) {
                Window.debugColor = Settings.DebugColor.Normal;
            } else if (Window.debugColor == Settings.DebugColor.Normal) {
                Window.debugColor = Settings.DebugColor.Restitution;
            } else if (Window.debugColor == Settings.DebugColor.Restitution) {
                Window.debugColor = Settings.DebugColor.Density;
            }
        }


        this.pl.update();
        world.step(1.0f / FixedFps, velIt, posIt);

        //System.out.print(pl.c +" " + pl.r+"\n");

    }
}
