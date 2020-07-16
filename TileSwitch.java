//Switch the color of one tile

public static void flipPosition(int[][] board, int x, int y){
  if (board[x][y] == 1){
    board[x][y] == 2;
  }
  else if(board[x][y] == 2){
    board[x][y] == 1;
  }
}