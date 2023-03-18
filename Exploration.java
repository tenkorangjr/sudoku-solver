/*
 * Name: Michael Tenkorang
 * Class Purpose: Working with the Stacks Abstract Data Structure, DFS and Backtracking
 */

public class Exploration {

    public static void main(String[] args) {
        int[] numOfStarterValues = { 40 };
        int numTrials = 20;
        // int delay = 0;

        for (int num : numOfStarterValues) {
            int numSolutions = 0;
            long totalTime = 0;

            for (int i = 0; i < numTrials; i++) {
                Sudoku sudoku = new Sudoku(num);
                // System.out.println("Start");
                // System.out.println(sudoku.board.toString());
                long startTime = System.currentTimeMillis();
                sudoku.solve(0);
                long endTime = System.currentTimeMillis();
                long duration = endTime - startTime;

                if (sudoku.board.finished == true) {
                    numSolutions++;
                    totalTime += duration;
                }

                sudoku.board.finished = false;
                // sudoku.solveRec(sudoku.findNextCell());
                // System.out.println("End");
                // System.out.println(sudoku.board.toString());
            }
            ;

            double avgTime = numSolutions > 0 ? totalTime / numSolutions : 0;
            System.out.printf("Number of initial values: %d\nnumber of solutions found: %d\naverage time: %dms\n",
                    num, numSolutions, (int) avgTime);
            System.out.println("---------------------------------");
        }
    }
}