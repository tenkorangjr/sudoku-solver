import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Board {

    Cell[][] arr;

    public Board() {
        arr = new Cell[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                arr[i][j] = new Cell(i, j, 0);
            }
        }
    }

    public Cell get(int row, int col) {
        /*
         * Get the Cell at a particular point on grid
         */

        return arr[row][col];
    }

    public void set(int row, int col, int value) {
        /*
         * Set the value of a cell in grid
         */

        arr[row][col].setValue(value);
    }

    public void set(int row, int col, boolean locked) {
        /*
         * Set the locked of a Cell on grid
         */

        arr[row][col].setLocked(locked);
    }

    public boolean read(String filename) {
        try {
            // assign to a variable of type FileReader a new FileReader object, passing
            // filename to the constructor
            FileReader fr = new FileReader(filename);
            // assign to a variable of type BufferedReader a new BufferedReader, passing the
            // FileReader variable to the constructor
            BufferedReader br = new BufferedReader(fr);

            // assign to a variable of type String line the result of calling the readLine
            // method of your BufferedReader object.
            String line = br.readLine();
            // start a while loop that loops while line isn't null
            int row = 0;
            while (line != null) {
                // assign to an array of Strings the result of splitting the line up by spaces
                String[] newArr = line.split("[ ]+");
                // print the size of the String array (you can use .length)
                // use the line to set various Cells of this Board accordingly
                for (int i = 0; i < newArr.length; i++) {
                    if (Integer.parseInt(newArr[i]) != 0) {
                        arr[row][i] = new Cell(row, i, Integer.parseInt(newArr[i]), true);
                    } else {
                        arr[row][i] = new Cell(row, i, Integer.parseInt(newArr[i]));
                    }
                }
                // assign to line the result of calling the readLine method of your
                // BufferedReader object.
                line = br.readLine();
                row++;
            }
            // call the close method of the BufferedReader
            br.close();
            return true;
        } catch (FileNotFoundException ex) {
            System.out.println("Board.read():: unable to open file " + filename);
        } catch (IOException ex) {
            System.out.println("Board.read():: error reading file " + filename);
        }

        return false;
    }

    public String toString() {
        /*
         * Return a string representation of the board
         */
        String toReturn = "\n";
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                toReturn += arr[i][j] + " ";
                if ((j + 1) % 3 == 0) {
                    toReturn += " ";
                }
            }
            toReturn += "\n";
            if ((i + 1) % 3 == 0) {
                toReturn += "\n";
            }
        }

        return toReturn;
    }

    public static void main(String[] args) {
        Board board = new Board();
        if (args.length > 0) {
            board.read(args[0]);
            System.out.println(board);
        } else {
            System.out.println("Usage: filename for board");
        }

        // for (Cell[] row : board.arr) {
        // for (Cell cell : row) {
        // System.out.println(cell.isLocked());
        // }
        // }

    }

}
