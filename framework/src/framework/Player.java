/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import static framework.Window.SCALERATIO;
import org.jbox2d.common.Vec2;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import sun.awt.image.PixelConverter;

/**
 *
 * @author Marco
 */
public class Player extends Entity {

    public static Player pl;
    public boolean getRotation = true;

    public Player(float w, float h, float x, float y) {
        super(w, h, x, y);
        pl = this;
    }

    public Player(Vec2[] vertex, float x, float y) {
        super(vertex, x, y);
        pl = this;
    }

    public Player(float radius, float x, float y) {
        super(radius, x, y);
        pl = this;
    }

    @Override
    public void draw() {
        /*if (Window.game2.matrixMovement) {
         //this.setPosition(Window.game2.map.getMatrixPosition(this).getX(), Window.game2.map.getMatrixPosition(this).getY());
            
         }*/
        Window.game2.setVisual();

        // Window.game2.setVisualWithoutBorders();
        // Color.white.bind();
        /*
         GL11.glPushMatrix();
         GL11.glTranslatef(this.body.getPosition().x, this.body.getPosition().y, 0);
         /*if(this.getRotation){
         GL11.glRotatef(360 - Ms.getAngle(), 0f, 0f, 1f);
         }
         GL11.glBegin(GL11.GL_LINE_LOOP);
         {
         for (int j = 0; j < pS.getVertexCount(); j++) {
         GL11.glVertex2f(pS.getVertex(j).x, pS.getVertex(j).y);
         //    System.out.println(pS.getVertex(j).x+" // "+ pS.getVertex(j).y);
         }
           
         }
         GL11.glEnd();
         GL11.glPopMatrix();*/
        super.draw();
    }

    public void setGetRotation(boolean getRotation) {
        this.getRotation = getRotation;
    }

    public void update() {
        this.setD(Controls.getPlayerMovement());
        this.setSpeed();
        //  this.body.setTransform(this.body.getPosition(), Ms.getAngle((this.body.getPosition().x/SCALERATIO)/Window.PIXELTOMETERSRATIO,(this.body.getPosition().y/SCALERATIO)/Window.PIXELTOMETERSRATIO));

        //    this.body.setTransform(this.body.getPosition(), Ms.getAngle((pl.body.getPosition().x - Window.unfixedBounds[0]) * Window.METERSTOPIXEL, (pl.body.getPosition().y - Window.unfixedBounds[1]) * Window.METERSTOPIXEL));
        float angle = Ms.getAngle((pl.body.getPosition().x - Window.unfixedBounds[0]) * Window.METERSTOPIXEL, (pl.body.getPosition().y - Window.unfixedBounds[1]) * Window.METERSTOPIXEL);
        float nextAngle = body.getAngle() + body.getAngularVelocity() / 60;
        float totalRotation = angle - nextAngle;
        //    if (360 - totalRotation > 5) {
        Window.debugDrawHudString(1, 8, "Nangle: " + nextAngle, 0);
        while (totalRotation < Math.toRadians(-180)) {
            if (totalRotation < 0.1f) {
                break;
            }
            totalRotation += Math.toRadians(360);
        }
        while (totalRotation < Math.toRadians(180)) {
            if (totalRotation < 0.1f) {
                break;
            }
            totalRotation -= Math.toRadians(360);
        }
        float desiredAngularVelocity = totalRotation * 60;
        float impulse = body.getInertia() * desiredAngularVelocity;
        body.applyAngularImpulse(impulse);
        //  } else {
        //     body.setAngularVelocity(0);
        //  }
        //  body.setLinearVelocity(0);
        /*   
         Vec2 angleVect = new Vec2((float) Math.cos(angle), (float) Math.sin(angle));
         int force = 1;
         this.body.applyLinearImpulse(new Vec2(angleVect.x*force, angleVect.y*force), this.body.getWorldCenter());*/
    }
}
