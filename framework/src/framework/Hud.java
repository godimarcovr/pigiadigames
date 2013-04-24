/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import static framework.Window.SCALERATIO;
import static framework.Window.defmSpace;
import static framework.Window.mSpace;
import static framework.Window.unfixedBounds;
import java.awt.Font;
import java.util.ArrayList;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

/**
 *
 * @author muram_000
 */
public class Hud {

    Box shape;
    ArrayList<Label> comps;
    int f1;
    boolean visible = true;

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean isVisible) {
        this.visible = isVisible;
    }
    float x, y;

    public Hud(Box shape) {
        this.shape = shape;
        this.comps = new ArrayList<Label>();
        this.x = this.y = 0;
        initialize();
    }

    public void addComp(Label lab) {
        lab.setVisible(true);
        this.comps.add(lab);
    }

    public void initialize() {
        f1 = FontHandler.createFont("Times New Roman", Font.PLAIN, 15);
    }

    public void update(String read) {
        if (visible) {
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
    }

    public void draw() {
        if (visible) {
            for (int i = 0; i < comps.size(); i++) {
                comps.get(i).scaledDraw();
            }
        }
    }
}
