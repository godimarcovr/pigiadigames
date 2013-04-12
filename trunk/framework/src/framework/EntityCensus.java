/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package framework;

import java.util.ArrayList;

/**
 *
 * @author Marco
 */
public class EntityCensus {
    
    public static ArrayList<Entity> ents=new ArrayList<Entity>();

    public static void addEntity(Entity e){
        ents.add(e);
    }

    public static void removeEntity(Entity e){
        ents.remove(e);
    }
}
