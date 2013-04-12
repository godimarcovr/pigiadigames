/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package framework;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author Marco
 */
public class TimerHandler {

    public static HashMap<Integer,Timer> tim= new HashMap<Integer,Timer>();
    private static int c=0;

    public static int createTimer(){
        tim.put(c, new Timer());
        c++;
        return c-1;
    }

    public static int createCD(long ms){
        tim.put(c,new Timer(ms));
        c++;
        return c-1;
    }

    public static void update(int delta){
        Set<Entry<Integer, Timer>> keyset=tim.entrySet();
        for(Entry ent:keyset){
            Timer t=(Timer)ent.getValue();
            if(t.isTimeUp()){
                int remain=(int)t.counter;
                t.reset();
                t.counter+=remain;
            }
            else{
                t.pass(delta);
            }
        }
    }

    public static boolean isTimeUp(int id){
        Timer t=tim.get(id);
        if(t==null){
            return false;
        }
        if(t.isTimeUp()){
            return true;
        }
        else{
            return false;
        }
    }

    public static void removeTimer(int id){
        tim.remove(id);
    }
}
