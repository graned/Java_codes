/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

/**
 * THIS IS AN EXERCISE TO DETERMINE HOW MANY WAYS ARE TO REACH POINT [M,N] FROM POSITION [0,0] 
 * @author j.anaya.villagrana
 */
public class Matrix {

    /**
     * test main class
     * @param args 
     */
    public static void main(String[] args) {
        int[][] a = {
            {1,1,1,1,1},
            {0,1,0,0,1},
            {1,1,1,1,1},
            {1,0,1,1,1}
        };
        System.out.println(travel(a, 0, 0));
    }
    /**
     * RECURSIVE IMPLEMENTATION, THIS METHOD TRAVELS FROM POINT 0,0 TO M,N AS LONG AS THE VALUE AT THE RIGHT OR DOWN
     * ARE EQUAL TO 1
     * @param a
     * @param posX
     * @param posY
     * @return 
     */
    private static int travel(int[][] a, int posX, int posY) {
        int count = 0;
        //TRAVEL FIRST TO THE RIGHT
        if (posY + 1 < a[0].length) {
            if (a[posX][posY + 1] == 1) {
                count = count + travel(a, posX, posY + 1);
            }
        }
        //TRAVEL DOWN
        if (posX + 1 < a.length) {
            if (a[posX + 1][posY] == 1) {
                count = count + travel(a, posX + 1, posY);
            }
        }
        //IF REACHED THE END OF THE MATRIX
        if (posY == a[0].length - 1 && posX == a.length - 1) {
            //System.out.println("I FOUND A PATH!!!");
            return 1;
        }
        return count;
    }
}
