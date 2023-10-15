package edu.hw1;

public class Task8 {
    private static final int CELL_EMPTY = 0;
    private static final int CELL_KNIGHT = 1;
    private static final int FIELD_SIZE = 8;
    private static final int[] KNIGHT_MOVE_COL = {-2, -2, 2, 2, 1, -1, 1, -1};
    private static final int[] KNIGHT_MOVE_ROW = {1, -1, 1, -1, 2, 2, -2, -2};
    private static final int KNIGHT_MOVES = KNIGHT_MOVE_COL.length;

    private Task8() {

    }

    public static boolean knightBoardCapture(byte[][] cellHasKnight) {
        if (cellHasKnight.length != FIELD_SIZE) {
            throw new IllegalArgumentException("Field should be 8 rows in size!");
        }
        for (int i = 0; i < FIELD_SIZE; ++i) {
            if (cellHasKnight[i].length != FIELD_SIZE) {
                throw new IllegalArgumentException("Field should be 8 columns in size!");
            }
        }
        for (int row = 0; row < 8; ++row) {
            for (int col = 0; col < 8; ++col) {
                if (cellHasKnight[row][col] != CELL_EMPTY && cellHasKnight[row][col] != CELL_KNIGHT) {
                    throw new IllegalArgumentException("Field should contain only 0s and 1s!");
                }
            }
        }
        for (int row = 0; row < 8; ++row) {
            for (int col = 0; col < 8; ++col) {
                if (cellHasKnight[row][col] != CELL_EMPTY) {
                    for (int k = 0; k < KNIGHT_MOVES; ++k) {
                        int row_step = row + KNIGHT_MOVE_ROW[k];
                        int col_step = col + KNIGHT_MOVE_COL[k];
                        if (cellValid(row_step, col_step) && cellHasKnight[row_step][col_step] != CELL_EMPTY) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private static boolean cellValid(int row, int col) {
        return row >= 0 && col >= 0 && row < FIELD_SIZE && col < FIELD_SIZE;
    }
}
