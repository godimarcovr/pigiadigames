/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import gioco.Game;

/**
 *
 * @author Marco
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] argv) {
        Game test = new Game();
        Window.game2 = test;
        test.start();

        //Tester test = new Tester();
        // Window.game=test;
        // test.start();
    }
}
