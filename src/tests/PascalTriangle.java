/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.util.ArrayList;

/**
 *
 * @author j.anaya.villagrana
 */
public class PascalTriangle {

    private ArrayList<Integer> pascalTriangleValues;
    private int level = 1;
    private int positionLimit;

    public int getPositionLimit() {
        return positionLimit;
    }

    public void setPositionLimit(int positionLimit) {
        this.positionLimit = positionLimit;
    }

    public int getLevel() {
        return level;
    }

    public PascalTriangle() {
        pascalTriangleValues = new ArrayList<Integer>();
    }

    /**
     * This methods retrieves the value from the pascal triangle based on the
     * variable<br>
     * positionLimit
     *
     * @return The values that corresponds to the positionLimit value, will
     * return 0 if the position<br>
     * requested is 0 or less.
     */
    public int returnValue() {
        if (positionLimit > 0) {
            getValueRecursive(false, 1);
            System.out.println(pascalTriangleValues);
            return pascalTriangleValues.get((positionLimit - 1));
        }
        System.out.println("incorrect value");
        return 0;
    }

    /**
     * recursive method that populates the inner list from the class
     *
     * @param levelChangeFlag
     * @param currentPosition
     */
    private void getValueRecursive(boolean levelChangeFlag, int currentPosition) {
        int listSize = pascalTriangleValues.size();
        if (listSize < positionLimit) {
            if (levelChangeFlag) {
                System.out.println(pascalTriangleValues);
                pascalTriangleValues.add(1);
                levelChangeFlag = false;
                if (level > 2) {
                    currentPosition++;
                }
                level++;
                getValueRecursive(levelChangeFlag, currentPosition);
            } else {
                if (levelChanges(listSize)) {
                    pascalTriangleValues.add(1);
                    levelChangeFlag = true;
                    getValueRecursive(levelChangeFlag, currentPosition);
                } else {
                    int value;
                    value = pascalTriangleValues.get(currentPosition) + pascalTriangleValues.get(++currentPosition);
                    pascalTriangleValues.add(value);
                    getValueRecursive(levelChangeFlag, currentPosition);
                }
            }
        }
    }

    /**
     * method that determines if we change level in the pascal triangle
     *
     * @param listSize
     * @return true if level has changed, false if there is no level change
     */
    private boolean levelChanges(int listSize) {
        if ((listSize + 1) == expectedListSize(level)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * this method calculates what should be the size of the list based on which
     * level
     *
     * @param level Pascal triangle level
     * @return
     */
    private int expectedListSize(int level) {
        if (level == 1) {
            return level;
        } else {
            return level + expectedListSize(level - 1);
        }
    }
    /**
     * test main method
     * @param args 
     */
    public static void main(String[] args) {
        PascalTriangle pt = new PascalTriangle();
        pt.setPositionLimit(25);
        //DUE TO THE DOUBLE RECURSIVITY, WHEN WE SET THE LIMIT POSITION TO 100 000, A STACK OVERFLOW HAPPENDS.
        System.out.println("PASCAL TRIANGLE POSITION " + pt.getPositionLimit() + " IS -> " + pt.returnValue() + "\n LEVEL " + pt.getLevel());
    }
}
