/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
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

    public Settings.Shapes shapeType;
    public Shape pS;
    public Body body;
    Color color;

    public Element(float w, float h, float x, float y) {
        pS = new PolygonShape();
        ((PolygonShape) pS).setAsBox(w / 2, h / 2);
        color = Color.red;

        BodyDef bd = new BodyDef();
        bd.type = BodyType.STATIC;
        bd.position = new Vec2(x, y);
        shapeType = Settings.Shapes.SquareShape;

        FixtureDef fd = new FixtureDef();
        fd.shape = pS;
        fd.friction = 0f;
        fd.restitution = 1.0f;
        fd.density = 1.0f;

        body = Window.game2.world.createBody(bd);
        body.setUserData(this);
        body.createFixture(fd);
    }

    public Element(float w, float h, float x, float y, float friction, float restitution, float density) {
        pS = new PolygonShape();
        ((PolygonShape) pS).setAsBox(w / 2, h / 2);
        color = Color.red;

        BodyDef bd = new BodyDef();
        bd.type = BodyType.STATIC;
        bd.position = new Vec2(x, y);
        shapeType = Settings.Shapes.SquareShape;

        FixtureDef fd = new FixtureDef();
        fd.friction = friction;
        fd.restitution = restitution;
        fd.density = density;


        body = Window.game2.world.createBody(bd);
        body.setUserData(this);
        body.createFixture(fd);
    }

    public Element(Vec2[] vertex, float x, float y) {
        pS = new PolygonShape();
        ((PolygonShape) pS).set(vertex, vertex.length);
        color = Color.red;

        BodyDef bd = new BodyDef();
        bd.type = BodyType.STATIC;
        bd.position = new Vec2(x, y);
        shapeType = Settings.Shapes.PolygonShape;

        FixtureDef fd = new FixtureDef();
        fd.shape = pS;
        fd.friction = 0f;
        fd.restitution = 1.0f;
        fd.density = 1.0f;

        body = Window.game2.world.createBody(bd);
        body.setUserData(this);
        body.createFixture(fd);
    }

    public Element(Vec2[] vertex, float x, float y, float friction, float restitution, float density) {
        pS = new PolygonShape();
        ((PolygonShape) pS).set(vertex, vertex.length);
        color = new Color(friction, restitution, density);

        BodyDef bd = new BodyDef();
        bd.type = BodyType.STATIC;
        bd.position = new Vec2(x, y);
        shapeType = Settings.Shapes.PolygonShape;
        FixtureDef fd = new FixtureDef();
        fd.shape = pS;
        fd.friction = friction;
        fd.restitution = restitution;
        fd.density = density;


        body = Window.game2.world.createBody(bd);
        body.setUserData(this);
        body.createFixture(fd);
    }

    public void debugDraw() {
        GL11.glPushMatrix();
        GL11.glTranslatef(this.body.getPosition().x, this.body.getPosition().y, 0);

        switch (Window.debugColor) {
            case Density:
                color = new Color(body.m_fixtureList.getDensity(), body.m_fixtureList.getDensity(), body.m_fixtureList.getDensity());
                break;
            case Friction:
                color = new Color(body.m_fixtureList.getFriction(), body.m_fixtureList.getFriction(), body.m_fixtureList.getFriction());
                break;
            case Normal:
                color = new Color(body.m_fixtureList.getFriction(), body.m_fixtureList.getRestitution(), body.m_fixtureList.getDensity());
                break;
            case Restitution:
                color = new Color(body.m_fixtureList.getRestitution(), body.m_fixtureList.getRestitution(), body.m_fixtureList.getRestitution());
                break;
        }
        color.bind();
        if (shapeType == Settings.Shapes.SquareShape) {
            GL11.glBegin(GL11.GL_QUADS);
            {
                for (int j = 0; j < ((PolygonShape) pS).getVertexCount(); j++) {
                    GL11.glVertex2f(((PolygonShape) pS).getVertex(j).x, ((PolygonShape) pS).getVertex(j).y);
                }
            }
        } else {
            GL11.glBegin(GL11.GL_POLYGON);
            {
                for (int j = 0; j < ((PolygonShape) pS).getVertexCount(); j++) {
                    GL11.glVertex2f(((PolygonShape) pS).getVertex(j).x, ((PolygonShape) pS).getVertex(j).y);
                }
            }
        }
        GL11.glEnd();
        GL11.glPopMatrix();


    }

    public void draw() {
        //Color.blue.bind();
        if (Window.debug) {
            this.debugDraw();
            return;
        }

        GL11.glPushMatrix();
        GL11.glTranslatef(this.body.getPosition().x, this.body.getPosition().y, 0);
        if (this.shapeType != Settings.Shapes.SquareShape) {
            GL11.glRotatef(360 - Ms.getAngle(), 0f, 0f, 1f);
        }
        color.bind();

        if (shapeType == Settings.Shapes.SquareShape) {
            GL11.glBegin(GL11.GL_QUADS);
            {
                for (int j = 0; j < ((PolygonShape) pS).getVertexCount(); j++) {
                    GL11.glVertex2f(((PolygonShape) pS).getVertex(j).x, ((PolygonShape) pS).getVertex(j).y);
                }
            }
        } else {
            GL11.glBegin(GL11.GL_POLYGON);
            {
                for (int j = 0; j < ((PolygonShape) pS).getVertexCount(); j++) {
                    GL11.glVertex2f(((PolygonShape) pS).getVertex(j).x, ((PolygonShape) pS).getVertex(j).y);
                }
            }
        }
        GL11.glEnd();
        GL11.glPopMatrix();

    }
}
