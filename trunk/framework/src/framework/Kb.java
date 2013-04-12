/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import java.util.ArrayList;
import org.lwjgl.input.Keyboard;

/**
 *
 * @author matteo
 */
public class Kb {

    private static String old = "";
    private static String current = "";
    private static int kOld = -1;
    private static int kCurrent = -1;
    //private static ArrayList<String> lPressed = "";
    //private static int timBS;

    public static String getChars() {
        if (Keyboard.getNumKeyboardEvents() > 0) {
            old = "";
        }
        Keyboard.next();
        if (Keyboard.getEventKeyState()) {
            current = Keyboard.getKeyName(Keyboard.getEventKey());
            if (!(old.equals(current)) && current != null) {

                old = current;

                return (current);
            }
        }
        return "";
    }

    public static int getKeys() {
        if (Keyboard.getNumKeyboardEvents() > 0) {
            kOld = -1;
        }
        Keyboard.next();
        if (Keyboard.getEventKeyState()) {
            kCurrent = (Keyboard.getEventKey());
            if (kOld != kCurrent) {

                kOld = kCurrent;

                return (kCurrent);
            } else if (Keyboard.isRepeatEvent()) {

                return (kCurrent);
            }
        }
        return -1;
    }
    
    /*public static String getFromKB() {
        /*if (Keyboard.getNumKeyboardEvents() > 0) {
            old = "";
            kOld= 0;
        }
        //Keyboard.next();
        lPressed="";
        while(Keyboard.next()){
            int iPress= (Keyboard.getEventKey());
            switch(iPress){
                case(Keyboard.KEY_SPACE):{
                    lPressed+=" ";
                    break;
                }
                    
                case(Keyboard.KEY_BACK):{
                    if(timBS!=-1){
                        timBS=TimerHandler.createCD(500);
                        lPressed+=String.
                    }
                    else{
                        if(TimerHandler.isTimeUp(timBS)){
                            
                        }
                        else{
                            
                        }
                    }
                }
                
                default:{
                    lPressed+=Keyboard.getKeyName(iPress);
                    break;
                }    
            }
           
            
        }
        
        if (Keyboard.getEventKeyState()) {
            current = Keyboard.getKeyName(Keyboard.getEventKey());
            if (!(old.equals(current)) && current != null) {

                old = current;
                System.out.println(current);
                return (current);
            }
        }
        return "";
    }*/

    public static boolean isPressed(String key) {
        if (Keyboard.isKeyDown(Keyboard.getKeyIndex(key))) {
            return true;
        }
        while(Keyboard.next()){ //pulisco il buffer
        }
        return false;
    }
}
