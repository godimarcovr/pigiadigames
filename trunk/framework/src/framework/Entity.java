/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import framework.Window;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.FixtureDef;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.newdawn.slick.Color;

/**
 *
 * @author Marco
 */
public class Entity {

    public Settings.Shapes shapeType;
    public Shape pS;
    public Body body;
    public Fixture fix;
    public float dx, dy;
    public float speedMult = 8;
    public float w, h;
    public Color col;

    public Entity(float w, float h, float x, float y) {
        col = Color.white;
        this.w = w;
        this.h = h;
        pS = new PolygonShape();
        ((PolygonShape) pS).setAsBox(w / 2, h / 2);
        FixtureDef fd = new FixtureDef();
        fd.shape = pS;
        fd.friction = 0;
        fd.density = 1;
        fd.restitution = 0;
        shapeType = Settings.Shapes.SquareShape;
        BodyDef bd = new BodyDef();
        bd.type = BodyType.DYNAMIC;
        bd.position = new Vec2(x, y);
        body = Window.game2.world.createBody(bd);
        body.setUserData(this);
        this.fix = body.createFixture(fd);


        EntityCensus.addEntity(this);
    }

    public Entity(float w, float h, float x, float y, float friction, float restitution, float density) {
        col = Color.white;
        this.w = w;
        this.h = h;
        pS = new PolygonShape();
        ((PolygonShape) pS).setAsBox(w / 2, h / 2);
        FixtureDef fd = new FixtureDef();
        fd.shape = pS;
        fd.friction = friction;
        fd.density = restitution;
        fd.restitution = density;
        shapeType = Settings.Shapes.SquareShape;
        BodyDef bd = new BodyDef();
        bd.type = BodyType.DYNAMIC;
        bd.position = new Vec2(x, y);
        body = Window.game2.world.createBody(bd);
        body.setUserData(this);
        this.fix = body.createFixture(fd);


        EntityCensus.addEntity(this);
    }

    public Entity(Vec2[] vertex, float x, float y) {
        col = Color.white;
        pS = new PolygonShape();
        ((PolygonShape) pS).set(vertex, vertex.length);

        shapeType = Settings.Shapes.PolygonShape;
        BodyDef bd = new BodyDef();
        bd.type = BodyType.DYNAMIC;
        bd.position = new Vec2(x, y);
       

        
        FixtureDef fd = new FixtureDef();
        fd.shape = pS;
        fd.friction = 0;
        fd.density = 1.0f;
        fd.restitution = 1f;
       

        body = Window.game2.world.createBody(bd);
        body.setUserData(this);
        
        body.createFixture(fd);

        EntityCensus.addEntity(this);
    }

    public Entity(Vec2[] vertex, float x, float y, float friction, float restitution, float density) {
        col = Color.white;
        pS = new PolygonShape();
        ((PolygonShape) pS).set(vertex, vertex.length);
        shapeType = Settings.Shapes.PolygonShape;

        BodyDef bd = new BodyDef();
        bd.type = BodyType.DYNAMIC;
        bd.position = new Vec2(x, y);

        FixtureDef fd = new FixtureDef();
        fd.shape = pS;
        fd.friction = friction;
        fd.density = restitution;
        fd.restitution = density;

        body = Window.game2.world.createBody(bd);
        body.setUserData(this);
        body.createFixture(fd);


        EntityCensus.addEntity(this);
    }

    public Entity(float radius, float x, float y) {
        col = Color.white;
        this.w = w;
        this.h = h;
        pS = new CircleShape();
        ((CircleShape) pS).setRadius(radius);
        FixtureDef fd = new FixtureDef();
        fd.shape = pS;
        fd.friction = 0;
        fd.density = 1;
        fd.restitution = 0;
        shapeType = Settings.Shapes.CircularShape;
        BodyDef bd = new BodyDef();
        bd.type = BodyType.DYNAMIC;
        bd.position = new Vec2(x, y);
        body = Window.game2.world.createBody(bd);
        body.setUserData(this);
        this.fix = body.createFixture(fd);


        EntityCensus.addEntity(this);
    }

    public void setSpeed() {
        Vec2 vel = this.body.getLinearVelocity();
        float velChangex = dx * this.speedMult - vel.x;
        float velChangey = dy * this.speedMult - vel.y;
        float impulsex = this.body.getMass() * velChangex; //disregard time factor
        float impulsey = this.body.getMass() * velChangey; //disregard time factor
        this.body.applyLinearImpulse(new Vec2(impulsex, impulsey), this.fix.getBody().getWorldCenter());
    }

    public void setSpeedMult(float speedMult) {
        this.speedMult = speedMult;
    }

    public void setModifiers(float friction, float restitution, float density) {
        body.m_fixtureList.setDensity(density);
        body.m_fixtureList.setFriction(friction);
        body.m_fixtureList.setRestitution(restitution);
    }

    public void draw() {

        col.bind();
        GL11.glPushMatrix();
        GL11.glTranslatef(this.body.getPosition().x, this.body.getPosition().y, 0);
        GL11.glRotatef(body.getAngle(), 0f, 0f, 1f);
        if (shapeType == Settings.Shapes.SquareShape) {
            GL11.glBegin(GL11.GL_QUADS);
            {
                for (int j = 0; j < ((PolygonShape) pS).getVertexCount(); j++) {
                    GL11.glVertex2f(((PolygonShape) pS).getVertex(j).x, ((PolygonShape) pS).getVertex(j).y);
                }
            }
        } else if (shapeType == Settings.Shapes.PolygonShape) {
            GL11.glBegin(GL11.GL_POLYGON);
            {
                for (int j = 0; j < ((PolygonShape) pS).getVertexCount(); j++) {
                    GL11.glVertex2f(((PolygonShape) pS).getVertex(j).x, ((PolygonShape) pS).getVertex(j).y);
                }
            }
        } else {
            int num_segments = 20;
            float theta = (float) ((2 * 3.1415926) / num_segments);
            float c = (float) Math.cos(theta);//precalculate the sine and cosine
            float s = (float) Math.sin(theta);
            float t;

            float x = (pS).getRadius();//we start at angle = 0 
            float y = 0;

            GL11.glBegin(GL11.GL_LINE_LOOP);
            for (int ii = 0; ii < num_segments; ii++) {
                GL11.glVertex2f(x, y);//output vertex 

                //apply the rotation matrix
                t = x;
                x = c * x - s * y;
                y = s * t + c * y;
            }
            GL11.glEnd();
        }
        GL11.glEnd();
        GL11.glPopMatrix();


    }

    public void setPosition(float x, float y) {
        body.setTransform(new Vec2(x, y), 0);

    }

    public void setD(Vec2 dir) {
        this.dx = dir.x;
        this.dy = dir.y;
    }
}
