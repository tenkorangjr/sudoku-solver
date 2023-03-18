/*
 * Name: Michael Tenkorang
 * Class Purpose: Working with the Stacks Abstract Data Structure, DFS and Backtracking
 */

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Cell {

    private int row;
    private int col;
    private int value;
    private boolean locked;

    public Cell() {
        row = 0;
        col = 0;
        value = 0;
        locked = false;
    }

    public Cell(int row, int col, int value) {
        this.row = row;
        this.col = col;
        this.value = value;
        locked = false;
    }

    public Cell(int row, int col, int value, boolean locked) {
        this.row = row;
        this.col = col;
        this.value = value;
        this.locked = locked;
    }

    public int getRow() {
        /*
         * Get the row of a cell
         */
        return row;
    }

    public int getCol() {
        /*
         * Get the column of a cell
         */
        return col;
    }

    public int getValue() {
        /*
         * Get the value of a cell
         */
        return value;
    }

    public void setValue(int newVal) {
        /*
         * Set the value of a cell
         */
        value = newVal;
    }

    public boolean isLocked() {
        /*
         * Checked a cell is locked
         */
        return locked;
    }

    public void setLocked(boolean lock) {
        /*
         * Set the locked attribute of a cell
         */
        locked = lock;
    }

    public String toString() {
        /*
         * String representation of the cell
         */
        return "" + value;
    }

    public int getNumChoices(Board board) {
        /*
         * Get the total number of choices of the current cell
         */
        Set<Integer> choices = new HashSet<>();
        for (int i = 1; i <= 9; i++) {
            choices.add(i);
        }
        // Check the row
        for (int c = 0; c < 9; c++) {
            int value = board.get(row, c).getValue();
            if (value != 0) {
                choices.remove(value);
            }
        }
        // Check the column
        for (int r = 0; r < 9; r++) {
            int value = board.get(r, col).getValue();
            if (value != 0) {
                choices.remove(value);
            }
        }
        // Check the box
        int boxRow = (row / 3) * 3;
        int boxCol = (col / 3) * 3;
        for (int r = boxRow; r < boxRow + 3; r++) {
            for (int c = boxCol; c < boxCol + 3; c++) {
                int value = board.get(r, c).getValue();
                if (value != 0) {
                    choices.remove(value);
                }
            }
        }
        return choices.size();
    }

    public void draw(Graphics g, int x, int y, int scale) {
        /*
         * Draw Cell on window display
         */
        char toDraw = (char) ((int) '0' + getValue());
        g.setColor(isLocked() ? Color.BLUE : Color.RED);
        g.drawChars(new char[] { toDraw }, 0, 1, x, y);
    }

    public static void main(String[] args) {
        {
            Cell cell1 = new Cell();
            Cell cell2 = new Cell(40, 50, 10);
            Cell cell3 = new Cell(20, 30, 30, true);

            System.out.println(cell1.getRow() + " == 0");
            System.out.println(cell2.getRow() + " == 40");
            System.out.println(cell3.getRow() + " == 20");

            System.out.println(cell1.getCol() + " == 0");
            System.out.println(cell2.getCol() + " == 50");
            System.out.println(cell3.getCol() + " == 30");

            System.out.println(cell1.getValue() + " == 0");
            System.out.println(cell2.getValue() + " == 10");
            System.out.println(cell3.getValue() + " == 30");

            System.out.println(cell1.isLocked() + " == false");
            System.out.println(cell2.isLocked() + " == false");
            System.out.println(cell3.isLocked() + " == true");

            cell1.setValue(30);
            cell1.setLocked(true);

            System.out.println(cell1.getValue() + " == 30");
            System.out.println(cell1.isLocked() + " == true");

            System.out.println(cell1 + " == 30");
            System.out.println(cell2 + " == 10");
            System.out.println(cell3 + " == 30");
        }
    }
}
