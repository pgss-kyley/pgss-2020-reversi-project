//Make a copy of the board

public static int[][] makeBoardCopy(int[][] oldBoard){ //creats a duplicate board with a seperate memory address but the same data
  int[][] boardCopy = new int[8][8]; 
  for (int i = 0; i < 8; i++){ 
    for (int c = 0; c < 8; c++){ 
      boardCopy[i][c] = oldBoard[i][c]; //gives boardCopy[i][c] the value of oldBoard[i][c]
    }
  }
  return boardCopy; //returns the identical board that has different memory address
}
