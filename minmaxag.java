
public class minmaxag {

    public int move(String s) {
        char[] board = s.toCharArray();
        int bestM = -1;
        int bestV = Integer.MIN_VALUE;

        for (int i = 0; i < board.length; i++) {
            if (board[i] == '_') { 
                board[i] = 'O'; 
                int mValue = minimax(board, 0, false);
                board[i] = '_'; 
                if (mValue > bestV) {
                    bestM = i;
                    bestV = mValue;
                }
            }
        }
        return bestM;
    }

    private int minimax(char[] board, int depth, boolean isMax) {
        int s = evaluate(board);
        if (s == 10 || s == -10) return s;
        if (!isMovesLeft(board)) return 0;

        if (isMax) {
            int best = Integer.MIN_VALUE;
            for (int i = 0; i < board.length; i++) {
                if (board[i] == '_') {
                    board[i] = 'O';
                    best = Math.max(best, minimax(board, depth + 1, false));
                    board[i] = '_';
                }
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (int i = 0; i < board.length; i++) {
                if (board[i] == '_') {
                    board[i] = 'X';
                    best = Math.min(best, minimax(board, depth + 1, true));
                    board[i] = '_';
                }
            }
            return best;
        }
    }

    private boolean isMovesLeft(char[] board) {
        for (char c : board) {
            if (c == '_') return true;
        }
        return false;
    }

    private int evaluate(char[] board) {
        int[][] winCombinations = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}
        };
        for (int[] line : winCombinations) {
            if (board[line[0]] == board[line[1]] && board[line[1]] == board[line[2]]) {
                if (board[line[0]] == 'O') return 10;
                if (board[line[0]] == 'X') return -10;
            }
        }
        return 0;
    }
}

