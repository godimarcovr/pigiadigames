/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

/**
 *
 * @author matteo
 */
public class Position {
    float x;
    float y;

    public Position(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Position() {
        this.x = 0;
        this.y = 0;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getY() {
        return y;
    }

    @Override
    public String toString() {
        return x+" "+y;
    }
    
}
