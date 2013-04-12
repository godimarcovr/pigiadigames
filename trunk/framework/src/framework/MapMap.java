/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import java.math.MathContext;
import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.TextureImpl;

/**
 *
 * @author matteo
 */
public class MapMap {

    ArrayList<Element> mp;
    Matrix world;
    float height, width;

    public MapMap() {
        mp = new ArrayList<Element>();
        world = new Matrix(500, 500);
        height = 10;
        width = 10;
        generate();
    }

    public void draw() {
        Color.red.bind();
        GraphicElement gl;
        Element el;
        /*
         for (Element element : mp) {

         if (Math.abs(element.body.getPosition().x
         - Window.game2.pl.body.getPosition().x) <= Window.getBoundaries()[1]
         * 2) {
         if (Math.abs(element.body.getPosition().y
         - Window.game2.pl.body.getPosition().y) <= Window.getBoundaries()[3]
         * 2) {
         element.draw();
         }
         }
         }*/


        //(int) (Window.getBoundaries()[3].intValue() + 1 / width
        /*for (Object object : world.getNeighboursOf(Window.game2.pl.c, Window.game2.pl.r, 12)) {
           
         if (((Terrain) object).type == 1) {
         gl = new GraphicElement(width/2, height/2, ((Terrain) object).x, ((Terrain) object).y,((Terrain) object).c,((Terrain) object).r);
         gl.col = Color.blue;
         gl.draw();
         } else if (((Terrain) object).type == 0) {
         gl = new GraphicElement(width/2, height/2, ((Terrain) object).x, ((Terrain) object).y,((Terrain) object).c,((Terrain) object).r);
         gl.col = Color.red;
         gl.draw();
         }

         }*/

        /*
        for (int i = 0; i < world.getColumns(); i++) {
            for (int j = 0; j
                    < world.getRows(); j++) {

                ((GraphicElement) world.getElementAt(i, j)).draw();
            }
        }

        TextureImpl.bindNone();
        */
    }

    public void generate() {
        /*int w = world.getColumns();
         float x = -width * world.getColumns() / 2;
         float y = -height * world.getRows() / 2;
         int h = world.getRows();
         for (int i = 0; i < h; i++) {
         for (int j = 0; j < w; j++) {
         world.insertObject(new Terrain(0, x, y, i, j), i, j);
         y += height;

         }
         x += width;

         y = -height * world.getRows() / 2;
         }
         Random r = new Random();

         generateObstacles(r.nextInt(1000) + 10);*/
    }

    public void generateObstacles(int nObstacles) {
        /*
        Random r = new Random();
        int n = 0;
        int l = 0;
        int maxLength = 5;

        while (n < nObstacles) {
            int x = r.nextInt(world.getColumns() - maxLength);
            int y = r.nextInt(world.getRows());
            float newX = this.getMatrixPosition(x, y).x;
            float newY = this.getMatrixPosition(x, y).y;

            while (l < maxLength) {
                mp.add(new Element(height / 2, height / 2, newX + l * width, newY));
                world.insertObject((new Terrain(1, newX + l * width, newY, x + l, y)), x + l, y);
                l++;
            }
            l = 0;
            n++;
        }
        */
    }

    //world.insertObject(new Element(0, 0, 10, 10), 0, 0);
    public Position getEntityCoordinates(Entity e) {
        return new Position((float) (Math.floor((e.body.getPosition().x) / width) + (world.getColumns() / 2)), (float) (Math.floor(world.getRows() - (e.body.getPosition().y) / height) - (world.getRows() / 2)) + 1);
    }

    /*public Position getMatrixPosition(Entity e) {
     return new Position(-width * world.getColumns() / 2 + e.c * width, -height * world.getRows() / 2 + e.r * height);
     }*/
    public Position getMatrixPosition(int c, int r) {
        return new Position(-width * world.getColumns() / 2 + c * width, -height * world.getRows() / 2 + r * height);
    }

    public int getType(int c, int r) {
        return ((Terrain) world.getElementAt(c, r)).type;
    }
}
