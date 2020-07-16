//Find the number of black tiles

public static int getBlackScore(int[][] board){ //get total black pieces
  int score = 0;
  for (int[] row : board){
    for (int element : row){
      if (element == 1) { //if black, add one ot score
        score++;
      }
    }
  }
  return score;
}