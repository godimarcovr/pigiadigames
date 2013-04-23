/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import static framework.Window.SCALERATIO;
import static framework.Window.STRINGZOOMRATIO;
import static framework.Window.defmSpace;
import static framework.Window.mSpace;
import static framework.Window.unfixedBounds;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.TextureImpl;

/**
 *
 * @author matteo
 *
 */
public class Label {

    boolean visible;
    Box shape;
    String text;
    TrueTypeFont font;
    Color textCol, borderCol, bgCol;

    public Label() {
    }

    public Label(Box box, String text, int font, Color tCol, Color bCol, Color sCol) {
        this.text = text;
        this.shape = box;
        this.font = FontHandler.getFont(font);
        this.textCol = tCol;
        this.borderCol = bCol;
        this.bgCol = sCol;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public boolean isClicked() {
        if (this.isHover() && Ms.isClicked()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isClicked(float x, float y) {
        if (shape.isHit(x, y)) {
            return true;
        } else {
            return false;
        }
    }


    public boolean isHover() {
        if (shape.isHit(Ms.getX(), Ms.getY())) {
            return true;
        } else {
            return false;
        }
    }

    public void draw() {
        if (visible) {
            bgCol.bind();
            GL11.glPushMatrix();
            {
                GL11.glTranslatef(this.shape.x, this.shape.y, 0);

                GL11.glBegin(GL11.GL_QUADS);
                {
                    GL11.glVertex2f(0, 0);

                    GL11.glVertex2f(this.shape.w, 0);

                    GL11.glVertex2f(this.shape.w, this.shape.h);

                    GL11.glVertex2f(0, this.shape.h);
                }
                GL11.glEnd();

                borderCol.bind();

                GL11.glBegin(GL11.GL_LINE_LOOP);
                {
                    GL11.glVertex2f(0, 0);

                    GL11.glVertex2f(this.shape.w, 0);

                    GL11.glVertex2f(this.shape.w, this.shape.h);

                    GL11.glVertex2f(0, this.shape.h);
                }
                GL11.glEnd();

                GL11.glPushMatrix();
                {
                    GL11.glTranslatef(fontCenterPosX(), fontCenterPosY(), 0);
                    GL11.glEnable(GL11.GL_BLEND);
                    TextureImpl.bindNone();
                    this.font.drawString(0, 0, this.text, this.textCol);
                    GL11.glDisable(GL11.GL_BLEND);
                }
                GL11.glPopMatrix();
            }
            GL11.glPopMatrix();
        }
    }

    public void scaledDraw() {
        if (visible) {
            bgCol.bind();
            GL11.glPushMatrix();
            {
                GL11.glTranslatef(unfixedBounds[0] + (this.shape.x/Window.w*(defmSpace.x)) * (mSpace.x / defmSpace.x), unfixedBounds[3] - (this.shape.y/Window.h*(defmSpace.y)) * (mSpace.y / defmSpace.y), 0);
                GL11.glScalef(mSpace.x * SCALERATIO, -mSpace.x * SCALERATIO, 0);

                GL11.glBegin(GL11.GL_QUADS);
                {
                    GL11.glVertex2f(0, 0);

                    GL11.glVertex2f(this.shape.w, 0);

                    GL11.glVertex2f(this.shape.w, this.shape.h);

                    GL11.glVertex2f(0, this.shape.h);
                }
                GL11.glEnd();

                borderCol.bind();

                GL11.glBegin(GL11.GL_LINE_LOOP);
                {
                    GL11.glVertex2f(0, 0);

                    GL11.glVertex2f(this.shape.w, 0);

                    GL11.glVertex2f(this.shape.w, this.shape.h);

                    GL11.glVertex2f(0, this.shape.h);
                }
                GL11.glEnd();
                
                GL11.glPushMatrix();
                {
                    GL11.glTranslatef(fontCenterPosX(), fontCenterPosY(), 0);
                    GL11.glScalef(STRINGZOOMRATIO, STRINGZOOMRATIO, 0);
                    GL11.glEnable(GL11.GL_BLEND);
                    TextureImpl.bindNone();
                    this.font.drawString(0, 0, this.text, this.textCol);
                    GL11.glDisable(GL11.GL_BLEND);
                }
                GL11.glPopMatrix();
            }
            GL11.glPopMatrix();
        }
    }

    public float fontCenterPosX() {
        return shape.w / 2 - font.getWidth(this.text) / 2;
    }

    public float fontCenterPosY() {
        return shape.h / 2 - font.getLineHeight() / 2;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setPosition(float x, float y) {
        this.shape.x = x;
        this.shape.y = y;
    }

    public void setDimension(float w, float h) {
        this.shape.w = w;
        this.shape.h = h;
    }
}
