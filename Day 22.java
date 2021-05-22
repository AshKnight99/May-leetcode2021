/*
N-Queens
The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.

 

Example 1:


Input: n = 4
Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above
Example 2:

Input: n = 1
Output: [["Q"]]
 

Constraints:

1 <= n <= 9
*/
class Solution {
    List<List<String>> ans;
    char[][] board;
    
    public List<List<String>> solveNQueens(int N) {
        ans = new ArrayList<>();
        board = new char[N][N];
        for (char[] row : board) Arrays.fill(row, '.');
        place(0,0,0,0);
        return ans;
    }
    
    private void place(int i, int vert, int ldiag, int rdiag) {
        int N = board.length;
        if (i == N) {
            List<String> res = new ArrayList<>();
            for (char[] row : board) res.add(new String(row));
            ans.add(res);
            return;
        }
        for (int j = 0; j < N; j++) {
            int vmask = 1 << j, lmask = 1 << (i+j), rmask = 1 << (N - i - 1 + j);
            if ((vert & vmask) + (ldiag & lmask) + (rdiag & rmask) > 0) continue;
            board[i][j] = 'Q';
            place(i+1, vert | vmask, ldiag | lmask, rdiag | rmask);
            board[i][j] = '.';
        }
    }
}