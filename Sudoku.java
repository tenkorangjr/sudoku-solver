public class Sudoku {

    Board board;
    LandscapeDisplay ld;

    public Sudoku(int initialValues) {
        board = new Board(initialValues);
        ld = new LandscapeDisplay(board);
    }

    public int findNextValue(Cell curr) {
        /*
         * Find the next value to try for a specific cell
         */

        for (int i = curr.getValue() + 1; i < 10; i++) {
            if (board.validValue(curr.getRow(), curr.getCol(), i)) {
                return i;
            }
        }

        return 0;
    }

    public Cell findNextCell() {
        /*
         * Find the next cell that seems fit
         */

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board.get(r, c).getValue() == 0) {
                    return board.get(r, c);
                }
            }
        }

        return null;
    }

    public boolean solveRec(Cell next) {
        /*
         * Solving Sudoku with recursion
         */
        if (next == null) {
            return true;
        }

        if (10 > 0)
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        if (ld != null)
            ld.repaint();

        int currRow = next.getRow();
        int currCol = next.getCol();

        for (int i = 1; i <= 9; i++) {
            if (board.validValue(currRow, currCol, i)) {
                board.get(currRow, currCol).setValue(i);

                if (solveRec(findNextCell())) {
                    return true;
                }
            }
        }

        board.get(currRow, currCol).setValue(0);

        return false;
    }

    public boolean solve(int delay) {
        /*
         * Solve a given board
         */

        Stack<Cell> stack = new LinkedList<>();

        while (stack.size() < (board.getCols() * board.getRows()) - board.numLocked()) {

            Cell next = findNextCell();

            if (delay > 0) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (ld != null) {
                ld.repaint();
            }

            while (next == null && stack.size() > 0) {
                Cell temp = stack.pop();
                int tempVal = findNextValue(temp);
                temp.setValue(tempVal);

                if (tempVal != 0) {
                    next = temp;
                }
            }

            if (next == null) {
                return false;
            } else {
                stack.push(next);
            }
        }

        board.finished = true;
        return true;
    }

    public static void main(String[] args) {
        Sudoku gameSudoku = new Sudoku(10);
        System.out.println(gameSudoku.board);

        gameSudoku.solveRec(gameSudoku.findNextCell());
        System.out.println(gameSudoku.board);
    }
}
