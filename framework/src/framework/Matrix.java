/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import java.util.ArrayList;

/**
 *
 * @author matteo
 */
public class Matrix {

    Object[][] matrix;
    int rows;
    int columns;

    public Matrix(int column, int row) {
        matrix = new Object[column][row];
        this.rows = row;
        this.columns = column;
    }

    public void insertObject(Object o, int row, int column) {
        matrix[column][row] = o;
    }

    public Object[][] getMatrix() {
        return matrix;
    }

    public Object getElementAt(int row, int column) {
        return matrix[column][row];
    }

    public int[] getIndexOf(Object o) {
        int row = 0;
        int column = 0;
        int[] position = new int[2];
        for (Object[] objects : matrix) {
            row++;
            for (Object object : objects) {
                column++;
                if (object.equals(o)) {
                    position[0] = column;
                    position[1] = row;
                    return position;
                }
            }
        }
        return null;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Object getUpperCell(int x, int y){
        try{
            return this.matrix[rows-1][x];
        }
        catch(Exception e){
            return null;
        }
    }

    public Object getLowerCell(int x, int y){
        try{
            return this.matrix[rows+1][x];
        }
        catch(Exception e){
            return null;
        }
    }

    public Object getRightCell(int x, int y){
        try{
            return this.matrix[rows][x+1];
        }
        catch(Exception e){
            return null;
        }
    }

    public Object getLeftCell(int x, int y){
        try{
            return this.matrix[rows][x-1];
        }
        catch(Exception e){
            return null;
        }
    }

    public Object getNeighbourCell(int x, int y, int xMod, int yMod){
        try{
            return this.matrix[rows+yMod][x+xMod];
        }
        catch(Exception e){
            return null;
        }
    }

    public Object[] getNeighboursOf(int x, int y, int sX, int sY) {
        ArrayList<Object> arr = new ArrayList<Object>();
        for (int i = y-sY; i <= y+sY; i++) {
            for (int j = x-sX; j <= x+sX; j++) {
                try {
                    arr.add(this.matrix[j][i]);
                } catch (Exception ex) {
                }


            }
        }
        return arr.toArray();

    }
    
    public Object[] getNeighboursOf(int x, int y, int s) {
        ArrayList<Object> arr = new ArrayList<Object>();
        for (int i = y-s; i <= y+s; i++) {
            for (int j = x-s; j <= x+s; j++) {
                try {
                    arr.add(this.matrix[i][j]);
                } catch (Exception ex) {
                }


            }
        }
        return arr.toArray();

    }
}
