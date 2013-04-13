/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import java.util.ArrayList;
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
    ArrayList<Element> objects = new ArrayList<Element>();

    public Map(int w, int h) {
        this.w = w;
        this.h = h;
        objects.add(new Element(w, 1, w / 2, 0.5f));
        objects.add(new Element(1, h, 0.5f, h / 2));
        objects.add(new Element(1, h, -0.5f + w, h / 2));
        objects.add(new Element(w, 1, w / 2, h - 0.5f));

        //this.mat.insertObject(new Element(1, 1, 0.5f, i+0.5f), i, 0);
       /* for (int i = 0; i < w; i++) {
         try{
                
         this.mat.insertObject(new Element(w, 1, i+0.5f, 0.5f), 0, i);
         this.mat.insertObject(new Element(1, 1, 0.5f, i+0.5f), i, 0);
         this.mat.insertObject(new Element(1, 1, h-0.5f, i+0.5f), i, 79);
         //this.mat.insertObject(new Element(1, 1, 79.5f, i+0.5f), i, 79);
         }
         catch(Exception ex){
                
         }
            
         }*/
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
    }/*
     for (Element element : objects) {
     element.draw();
     }*/


}
