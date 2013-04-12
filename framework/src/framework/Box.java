/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

/**
 *
 * @author Marco
 * 
 * Simple class that handles rectangular-shaped items
 * Used only for position, dimensions and veryfing whether a point is in the box or not
 */
public class Box {

    public float x, y, w, h;

    public Box() {
    }

    public Box(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public boolean isHit(float x, float y) {
        if ((this.x < x) && (this.x + this.w > x)) {
            if ((this.y < y) && (this.y + this.h >y)) {
                return true;
            }
        }   
        return false;
    }

    /*public boolean collidesWith(Box other) {
        if (((other.x >= this.x) && (other.x <= this.x + this.w)) || ((other.x + other.w >= this.x) && (other.x + other.w <= this.x + this.w))|| ((other.x <= this.x) && (other.x + other.w >= this.x + this.w))) {
            if (((other.y  >= this.y ) && (other.y <= this.y + this.h)) || ((other.y + other.h >= this.y) && (other.y + other.h <= this.y + this.h))||((this.y>=other.y)&&(this.y+this.h <= other.y+other.h))) {
                return true;
            } 
        }
        return false;
    }*/

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
