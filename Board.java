import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.awt.*;;

public class Board {

    Cell[][] arr;
    public static final int SIZE = 9;
    private int numberOfLockedCells;
    boolean finished;

    Random random;

    public Board() {
        arr = new Cell[getRows()][getCols()];
        finished = false;
        numberOfLockedCells = 0;

        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                arr[i][j] = new Cell(i, j, 0);
            }
        }
    }

    public Board(int filled) {
        random = new Random();
        int[][] filledPositions = generateUniquePositions(filled);
        numberOfLockedCells = filled;
        finished = false;
        arr = new Cell[getRows()][getCols()];

        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                arr[i][j] = new Cell(i, j, 0);
            }
        }

        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                if (!positionContains(filledPositions, i, j)) {
                    arr[i][j] = new Cell(i, j, 0);
                } else {
                    int newValue = random.nextInt(10);
                    while (!validValue(i, j, newValue)) {
                        newValue = random.nextInt(10);
                    }

                    arr[i][j] = new Cell(i, j, newValue);
                }
            }
        }
    }

    public void draw(Graphics g, int scale) {
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                get(i, j).draw(g, j * scale + 5, i * scale + 10, scale);
            }
        }
        if (finished) {
            if (validSolution()) {
                g.setColor(new Color(0, 127, 0));
                g.drawChars("Hurray!".toCharArray(), 0, "Hurray!".length(), scale * 3 + 5, scale * 10 + 10);
            } else {
                g.setColor(new Color(127, 0, 0));
                g.drawChars("No solution!".toCharArray(), 0, "No Solution!".length(), scale * 3 + 5, scale * 10 + 10);
            }
        }
    }

    public int numLocked() {
        /*
         * Get the total number of locked cells
         */

        return numberOfLockedCells;
    }

    public int getRows() {
        /*
         * Get the total row of a board
         */

        return SIZE;
    }

    public int getCols() {
        /*
         * Get the total column of a board
         */

        return SIZE;
    }

    public boolean isLocked(int r, int c) {
        /*
         * Check whether the cell at (r,c) is locked
         */
        return get(r, c).isLocked();
    }

    public int value(int r, int c) {
        /*
         * Return the value at (r,c)
         */
        return get(r, c).getValue();
    }

    private int[][] generateUniquePositions(int number) {
        /*
         * Generate unique positions for filled positions
         */

        int[][] positions = new int[number][2];

        for (int i = 0; i < number; i++) {
            int row = random.nextInt(9);
            int col = random.nextInt(9);
            while (positionContains(positions, row, col)) {
                row = random.nextInt(9);
                col = random.nextInt(9);
            }
            positions[i][0] = row;
            positions[i][1] = col;
        }
        return positions;
    }

    private boolean positionContains(int[][] positions, int row, int col) {
        /*
         * Check if an array of positions contains (row, col)
         */
        for (int[] position : positions) {
            if (position[0] == row && position[1] == col) {
                return true;
            }
        }
        return false;
    }

    public boolean validValue(int row, int column, int value) {
        /*
         * Test to see if a value is value for a coordinate
         */

        for (int r = 0; r < getRows(); r++) {
            if (r != row && arr[r][column].getValue() == value) {
                return false;
            }
        }

        for (int c = 0; c < getCols(); c++) {
            if (c != column && arr[row][c].getValue() == value) {
                return false;
            }
        }

        for (int r = (row / 3) * 3; r < ((row / 3) * 3) + 3; r++) {
            for (int c = (column / 3) * 3; c < ((column / 3) * 3) + 3; c++) {
                if (row != r && column != c && arr[r][c].getValue() == value) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean validSolution() {
        /*
         * Check if the board has been completed
         */
        for (int r = 0; r < getRows(); r++) {
            for (int c = 0; c < getCols(); c++) {
                int currVal = arr[r][c].getValue();
                if (!validValue(r, c, currVal) || currVal < 1 || currVal > 9) {
                    return false;
                }
            }
        }
        return true;
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
        Board board = new Board(40);
        System.out.println(board);
        // if (args.length > 0) {
        // board.read(args[0]);
        // System.out.println(board);
        // } else {
        // System.out.println("Usage: filename for board");
        // }

    }

}
