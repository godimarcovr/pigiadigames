/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import java.util.ArrayList;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

/**
 *
 * @author marco
 */
public class Map {

    Matrix mat;
    public int w, h; //in metri

    public Map(int w, int h) {
        this.mat = new Matrix(w, h);
        this.w = w;
        this.h = h;
        for (int i = 0; i < 80; i++) {
            try{
                this.mat.insertObject(new Element(1, 1, i+0.5f, 0.5f), 0, i);
                this.mat.insertObject(new Element(1, 1, 0.5f, i+0.5f), i, 0);
                this.mat.insertObject(new Element(1, 1, 79.5f, i+0.5f), i, 79);
            }
            catch(Exception ex){
                
            }
            
        }
    }

    public void draw() {
        Color.red.bind();//colore custom

        for (int i = 0; i < this.mat.columns; i++) {//solo porzioni di mappa
            GL11.glPushMatrix();
            GL11.glTranslatef(i, 0, 0);
            for (int j = 0; j < this.mat.rows; j++) {
                Object object = this.mat.matrix[i][j];
                GL11.glPushMatrix();
                GL11.glTranslatef(0, j, 0);
                GL11.glBegin(GL11.GL_LINE_LOOP);
                {
                        GL11.glVertex2f(0, 0);
                        GL11.glVertex2f(1, 0);
                        GL11.glVertex2f(1, 1);
                        GL11.glVertex2f(0, 1);
                    
                }
                GL11.glEnd();
                GL11.glPopMatrix();
            }
            GL11.glPopMatrix();

        }

        Object[] neigh = this.mat.getNeighboursOf((int) Player.pl.body.getPosition().x
                , (int) Player.pl.body.getPosition().y
                , (int) (Window.mSpace.x)
                , (int) (Window.mSpace.y));

        for (Object element : neigh) {
            if(element!=null){
                Element el = (Element) element;
                el.draw();
            }

        }
    }
}
