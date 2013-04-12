/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package framework;

import java.awt.Font;
import java.util.HashMap;
import org.newdawn.slick.TrueTypeFont;

/**
 *
 * @author Marco
 */
public class FontHandler {

    public static HashMap<Integer,TrueTypeFont> f= new HashMap<Integer,TrueTypeFont>();
    private static int c=0;

    public static int createFont(String font, int options, int size){
        f.put(c, new TrueTypeFont(new Font(font,options,size), true));
        c++;
        return c-1;
    }

    public static void removeFont(int id){
        f.remove(id);
    }

    public static TrueTypeFont getFont(int id){
        return f.get(id);
    }
}
