/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import java.awt.Rectangle;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.lwjgl.opengl.GL11;

import org.newdawn.slick.Color;

/**
 *
 * @author matteo
 */
public class GraphicElement {

    private PolygonShape pS;
    Position p;
    Color col;
    int c;
    int r;

    public GraphicElement(float w, float h, float x, float y, int c, int r) {
        pS = new PolygonShape();
        pS.setAsBox(w, h);
        p = new Position(x, y);
        col = Color.red;
        this.c = c;
        this.r = r;
    }

    public void draw() {
        col.bind();
        if (col == Color.red) {
            GL11.glPushMatrix();
            GL11.glTranslatef(p.x, p.y, 0);
            GL11.glBegin(GL11.GL_LINE_LOOP);
            {
                for (int j = 0; j < pS.getVertexCount(); j++) {
                    GL11.glVertex2f(pS.getVertex(j).x, pS.getVertex(j).y);
                }
            }
            GL11.glEnd();
            GL11.glPopMatrix();
        } else {
            GL11.glPushMatrix();
            GL11.glTranslatef(p.x, p.y, 0);
            GL11.glBegin(GL11.GL_QUADS);
            {             
                    GL11.glVertex2f(pS.getVertex(0).x, pS.getVertex(0).y);
                    GL11.glVertex2f(pS.getVertex(1).x, pS.getVertex(1).y);
                    GL11.glVertex2f(pS.getVertex(2).x, pS.getVertex(2).y);
                    GL11.glVertex2f(pS.getVertex(3).x, pS.getVertex(3).y);
            }
            GL11.glEnd();
            GL11.glPopMatrix();
        }


    }
}
