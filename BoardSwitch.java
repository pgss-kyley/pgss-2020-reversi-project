//Switch the colors of the board

public static int[][] makeReverseBoard(int[][] board){ //reverses all of the colors on the board (black --> white && white --> black)
  for (int i = 0; i < 8; i++){ 
    for (int c = 0; c < 8; c++){ 
      flipPosition(board, i, c);
    }
  }
  return board; //returns a new board with all of the colors flipped
}
