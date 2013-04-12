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
 * @author Marco
 */
public class Menu {

    Box shape;
    ArrayList<Label> comps;

    public Menu(Box shape) {
        this.shape = shape;
        this.comps = new ArrayList<Label>();
    }

    public void addComp(Label lab) {
        lab.setVisible(true);
        this.comps.add(lab);
    }



    public void verticalCompile(float percX,float percY) {

        float vSpace = this.shape.h / comps.size();
        float hComp = vSpace * percY;
        float wComp = percX * this.shape.w;
        float vLeap = (vSpace - hComp) / 2;
        float hLeap = (this.shape.w - wComp) / 2;
        for (int i = 0; i < comps.size(); i++) {
            Label label = comps.get(i);
            label.setPosition(this.shape.x + hLeap, this.shape.y + vLeap + (vSpace * i));
            label.setDimension(wComp, hComp);
        }
    }


    public void update(String read) {
        for (Label label : comps) {
            if (label instanceof TextBox) {
                TextBox tlabel = (TextBox) label;
                if (tlabel.isClicked() && !tlabel.isEnabled()) {
                    tlabel.setEnabled(true);
                } else if (Ms.isClicked() && !label.isHover()) {
                    tlabel.setEnabled(false);
                } else if (tlabel.isEnabled()) {
                    tlabel.upText(read);
                }
                tlabel.update();
                
            } else if (label instanceof Button) {
                Button blabel = (Button) label;
                if (blabel.isClicked()) {
                    blabel.setEnabled(true);
                } else if (!blabel.isClicked() && blabel.isEnabled() && blabel.isHover()) {
                    blabel.run();
                    blabel.setEnabled(false);
                } else if (Ms.isClicked() && !blabel.isClicked()) {
                    blabel.setEnabled(false);
                }
            }

        }
    }
    

    public void horizontalCompile(float percX,float percY) {
        float hSpace = this.shape.w / comps.size();
        float hComp = this.shape.h*percY;
        float wComp = percX*hSpace;
        float vLeap = (this.shape.w - hComp) / 2;
        float hLeap = (hSpace - wComp) / 2;
        System.out.println(hLeap);
        for (int i = 0; i < comps.size(); i++) {
            Label label = comps.get(i);
            label.setPosition(this.shape.x + hLeap + (hSpace * i),this.shape.y + vLeap);
            label.setDimension(wComp, hComp);
        }
    }


    public void draw() {
        Color.white.bind();
        GL11.glPushMatrix();
        GL11.glTranslatef(this.shape.x, this.shape.y, 0);
        GL11.glBegin(GL11.GL_LINE_LOOP);
        {
            GL11.glVertex2f(0, 0);

            GL11.glVertex2f(this.shape.w, 0);

            GL11.glVertex2f(this.shape.w, this.shape.h);

            GL11.glVertex2f(0, this.shape.h);
        }
        GL11.glEnd();
        GL11.glPopMatrix();

        for (int i = 0; i < comps.size(); i++) {
            comps.get(i).draw();
        }
    }
}
