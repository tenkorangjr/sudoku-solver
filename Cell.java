import java.awt.*;

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
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int newVal) {
        value = newVal;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean lock) {
        locked = lock;
    }

    public String toString() {
        return "" + value;
    }

    public void draw(Graphics g, int x, int y, int scale) {
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
