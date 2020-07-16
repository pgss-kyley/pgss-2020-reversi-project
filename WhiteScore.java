public static int getWhiteScore(int[][] board){ //get total white pieces
  int score = 0;
  for (int[] row : board){
    for (int element : row){
      if (element == 2){ //if white, add one to score
        score++;
      }
    }
  }
  return score;
}