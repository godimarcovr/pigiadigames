/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import java.util.ArrayList;
import java.util.Random;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

/**
 *
 * @author marco
 */
public class Map {

    public int w, h; //in metri
    ArrayList<Element> boundary = new ArrayList<Element>();
    ArrayList<Element> obstacles = new ArrayList<Element>();

    public Map(int w, int h) {
        this.w = w;
        this.h = h;
        boundary.add(new Element(w, 1, w / 2, 0.5f));
        boundary.add(new Element(1, h, 0.5f, h / 2));
        boundary.add(new Element(1, h, -0.5f + w, h / 2));
        boundary.add(new Element(w, 1, w / 2, h - 0.5f));
        this.GenerateMap(1, w / 2, w / 4, w / 8, w / 8);
    }

    public void draw() {
        Color.red.bind();//colore custom
        for (Body b = Window.game2.world.getBodyList(); b != null; b = b.getNext()) {
            if (b.getUserData() instanceof Element) {
                ((Element) b.getUserData()).draw();
            } else {
                ((Entity) b.getUserData()).draw();
            }
        }
        Window.debugDrawLine(0, 0, 20, 20);
    }/*
     for (Element element : boundary) {
     element.draw();
     }*/


    public void GenerateMap(int seed, int maxObs, int minObs, int maxW, int maxH) {
        Random r = new Random(seed);
        int nObj = minObs + (int) (r.nextFloat() * (maxObs - minObs));
        System.out.println(nObj);
        for (int i = 0; i < nObj; i++) {
            float lenW = 1 + (float) (r.nextFloat() * (maxW - 1));
            float lenH = 1 + (float) (r.nextFloat() * (maxH - 1));
            float x = 1 + (float) (r.nextFloat() * (w - lenW));
            float y = 1 + (float) (r.nextFloat() * (h - lenH));
            Vec2[] v = GeneratePolygonVertex(8, r);
            obstacles.add(new Element(v, x, y));
           // obstacles.add(new Element(lenW, lenH, x, y));
        }

    }

    public Vec2[] GeneratePolygonVertex(int maxVertex, Random r) {

        int vertN = 3 + (int) (r.nextFloat() * (maxVertex - 3));
        int radius = 2;
        Vec2[] vertex = generateRegularPolygon(vertN,radius);
        System.out.println("N" + vertN);
        for (int i = 0; i < vertN; i++) {
    /*        if (vertex[i].x < 0){
                vertex[i].x -=  radius/4 + (r.nextFloat() * (radius/2 - radius/4));
            }else
            {
                vertex[i].x +=  radius/4 + (r.nextFloat() * (radius/2 - radius/4));
            }*/
             vertex[i].x +=  -radius/2 + (r.nextFloat() * (radius/2 + radius/2));
        }

        return vertex;
    }

    private Vec2[] generateRegularPolygon(int vertN, float r) {
        Vec2[] vertex = new Vec2[vertN];
        for (int i = 0; i < vertN; i++) {
            vertex[i] = new Vec2((float)(r * Math.cos(2 * Math.PI * i / vertN)), (float)(r * Math.sin(2 * Math.PI * i / vertN)));
        }
        return vertex;
    }
}
