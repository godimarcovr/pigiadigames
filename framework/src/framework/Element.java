/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

/**
 *
 * @author matteo
 */
public class Element {

    public PolygonShape pS;
    public Body body;
    boolean pol = false;
    Color color;

    public Element(float w, float h, float x, float y) {
        pS = new PolygonShape();
        pS.setAsBox(w / 2, h / 2);
        color = Color.red;

        BodyDef bd = new BodyDef();
        bd.type = BodyType.STATIC;
        bd.position = new Vec2(x, y);


        FixtureDef fd = new FixtureDef();
        fd.shape = pS;
        fd.friction = 0f;

        body = Window.game2.world.createBody(bd);
        body.setUserData(this);
        body.createFixture(fd);
    }

    public Element(Vec2[] vertex, float x, float y) {
        pS = new PolygonShape();
        pS.set(vertex, vertex.length);
        color = Color.red;

        BodyDef bd = new BodyDef();
        bd.type = BodyType.STATIC;
        bd.position = new Vec2(x, y);

        FixtureDef fd = new FixtureDef();
        fd.shape = pS;
        fd.friction = 0;
        pol = true;

        body = Window.game2.world.createBody(bd);
        body.setUserData(this);
        body.createFixture(fd);
    }

    public void draw() {
        //Color.blue.bind();
        GL11.glPushMatrix();
        GL11.glTranslatef(this.body.getPosition().x, this.body.getPosition().y, 0);
        color.bind();
        if (!pol) {
            GL11.glBegin(GL11.GL_QUADS);
            {
                for (int j = 0; j < pS.getVertexCount(); j++) {
                    GL11.glVertex2f(pS.getVertex(j).x, pS.getVertex(j).y);
                }
            }
        } else {
            GL11.glBegin(GL11.GL_POLYGON);
            {
                for (int j = 0; j < pS.getVertexCount(); j++) {
                    GL11.glVertex2f(pS.getVertex(j).x, pS.getVertex(j).y);
                }
            }
        }
        GL11.glEnd();
        GL11.glPopMatrix();
        
    }
}
