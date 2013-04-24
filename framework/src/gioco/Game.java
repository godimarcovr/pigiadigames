/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gioco;

import framework.Box;
import framework.Button;
import framework.Controls;
import framework.Entity;
import framework.Hud;
import framework.Kb;
import framework.Label;
import framework.Map;
import framework.Ms;
import framework.Player;
import framework.Settings;
import framework.TextBox;
import framework.TimerHandler;
import framework.Window;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
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
    public int timeStep = 120;
    int oldFPS;
    public Player pl;
    public boolean matrixMovement = true;
    public Hud hud;

    public Game() {
    }

    public void start() {
        // init OpenGL here
        //CHANGE RESOLUTION HERE
        boolean success = Window.initialise(1280, 768, true);
        //  Window.setMeterSpace(20, 15);
        Window.game2 = this;

        //  Window.debug = true;
        try {
            Display.create();
            // Display.setResizable(true);
            // Display.setFullscreen(true);
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

        this.hud = new Hud(new Box(0, 0, Window.w, Window.h));

        Label label = new Label(new Box(Window.w - 200, Window.h - 100, 200, 100), "Label", 0, Color.red, Color.cyan, Color.lightGray);
        TextBox textTest = new TextBox(new Box(Window.w - 200, Window.h - 200, 200, 100), "GODI LAMER", 0, Color.red, Color.cyan, Color.lightGray);
        Button butTest2 = new Button(new Box(Window.w - 200, Window.h - 300, 200, 100), "Exit", 0, Color.red, Color.cyan, Color.lightGray, Color.green, Color.gray) {
            @Override
            public void run() {
                System.exit(0);
            }
        };
        hud.addComp(label);
        hud.addComp(butTest2);
        hud.addComp(textTest);
        this.e2 = new Entity(1, 1, 8, 8, 1, 0, 1);
        e2.setModifiers(0, 0, 1);
        map = new Map(50, 50);


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
            Display.sync(timeStep); // cap fps to 60fps
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
        hud.draw();
        Window.debugDrawLine(0, 0, pl.body.getPosition().x, pl.body.getPosition().y);
        Window.debugDrawStaticString(5, 5, Window.debugColor.toString(), 0);
        Window.debugDrawHudString(1, 0, "DEBUG MODE: " + Window.debug, 0);
        Window.debugDrawHudString(1, 1, "COLOR TYPE: " + Window.debugColor.toString(), 0);
        Window.debugDrawHudString(1, 2, "ZOOM RATIO: " + Window.ZOOMRATIO, 0);
        Window.debugDrawHudString(1, 3, "MSX: " + Ms.getX(), 0);
        Window.debugDrawHudString(1, 4, "MSY: " + Ms.getY(), 0);
        Window.debugDrawHudString(1, 5, "GRAVITY: " + world.getGravity().y, 0);
        if (timeStep == 0) {
            Window.debugDrawHudString(1, 6, "PAUSED", 0);
        }

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

        Window.setUnfixedBoundaries(bordSX, bordDX, bordDOWN, bordUP);
        GLU.gluOrtho2D(bordSX, bordDX, bordDOWN, bordUP);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);

    }

    public void update(int delta) {
        String read = Kb.getChars();
        if ("F".equals(read)) {
            Window.debug = !Window.debug;
        } else if ("G".equals(read)) {
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
        } else if ("O".equals(read)) {
          /*  if (fps < timeStep - 500) {
                if (fps < 60) {
                    timeStep = 60;
                }
                timeStep = fps;
            }*/
        } else if ("M".equals(read)) {
            if (Window.ZOOMRATIO == 1) {
                Window.setZoomRatio(0.66f);
            } else if (Window.ZOOMRATIO == 0.66f) {
                Window.setZoomRatio(0.50f);
            } else if (Window.ZOOMRATIO == 0.50f) {
                Window.setZoomRatio(0.33f);
            } else if (Window.ZOOMRATIO == 0.33f) {
                Window.setZoomRatio(1f);
            }
        } else if ("P".equals(read)) {
            if (timeStep != 0) {
                oldFPS = timeStep;
                timeStep = 0;
            } else {
                timeStep = oldFPS;
            }
        }else if ("K".equals(read)) {
            hud.setVisible(!hud.isVisible());
        }


        this.pl.update();
        if (timeStep != 0) {
            world.step(1.0f / timeStep, velIt, posIt);
        }
        hud.update(read);

        //System.out.print(pl.c +" " + pl.r+"\n");

    }
}
