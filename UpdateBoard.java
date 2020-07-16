

public static int updateBoard(int[][] board, int[] points, int color){ //input board, coordinate, and color of piece
  board[points[0]][points[1]] = color; //adds the new piece to the board
  int totalFlip = 0; 
  if (points[1] < 7){ //evaluate to the right
    for(int i = points[1] + 1; i < 8; i++){
      if (board[points[0]][i] == color){
        for(int c = points[1] + 1; c < i; c++){
          flipPosition(board, points[0], c);
          totalFlip++;
        }
        break;
      }
    }
  }
  if (points[1] > 0){ //evaluate to the left
    for (int i = points[1] - 1; i >= 0; i--){
      if (board[points[0]][i] == color){
        for (int c = points[1] - 1; c > i; c--){
          flipPosition(board, points[0], c);
          totalFlip++;
        }
        break;
      }
    }
  }
  if (points[0] < 7){ //evaluate beneath
    for (int i = points[0] + 1; i < 8; i++){
      if (board[i][points[1]] == color){
        for (int c = points[0] + 1; c < i; c++){
          flipPosition(board, c, points[1]);
          totalFlip++;
        }
        break;
      }
    }
  }
  if (points[0] > 0){ //evaluate above
    for (int i = points[0] - 1; i >= 0; i--){
      if(board[i][points[1]] == color){
        for (int c = points[0] - 1; c > i; c--){
          flipPosition(board, c, points[1]);
          totalFlip++;
        }
        break;
      }
    }
  }
  if (points[0] < 7 && points[1] < 7){ //evaluate to bottom right
    for (int i = points[0] + 1, j = points[1] + 1; i < 8 && j < 8; i++, j++){
      if (board[i][j] == color){
        for (int c = points[0] + 1, d = points[1] + 1; c < i && d < j; c++, d++){
          flipPosition(board, c, d);
          totalFlip++;
        }
        break;
      }
    }
  }
  if (points[0] > 0 && points[1] > 0){ //evaluate to upper left
    for (int i = points[0] - 1, j = points[1] - 1; i >= 0 && j >= 0; i--, j--){
      if (board[i][j] == color){
        for (int c = points[0] - 1, d = points[1] - 1; c > i && d > j; c--, d--){
          flipPosition(board, c, d);
          totalFlip++;
        }
        break;
      }
    }
  }
  if (points[0] < 7 && points[1] > 0){ //evaluate to bottom left
    for (int i = points[0] + 1, j = points[1] - 1; i < 8 && j >= 0; i++, j--){
      if (board[i][j] == color){
        for (int c = points[0] + 1, d = points[1] - 1; c < i && d > j; c++, d--){
          flipPosition(board, c, d);
          totalFlip++;
        }
        break;
      }
    }
  }
  if (points[0] > 0 && points[1] < 7){ //evaluate to upper right
    for (int i = points[0] - 1, j = points[1] + 1; i >= 0 && j < 8; i--, j++){
      if (board[i][j] == color){
        for (int c = points[0] - 1, d = pointer[1] + 1; c > i && d < j; c--, d++){
          flipPosition(board, c, d);
          totalFlip++;
        }
        break;
      }
    }
  }
  return totalFlip; //returns the number of pieces that were flipped for evaluation
}
  
  
  
  