
//Ellen implementations
    
    public boolean CanFlipTile(int wx, int wy, int bx, int by, int[][] hi) {//"place" is the new tile of color 2, "flip" is the old tile of color 1
	int sum = bx+by-wx-wy;
	//basically draws lines to figure out whether if someone is placing it, the new tile can flip the original tile 
	//0 = empty, 1= black, 2=white
		
	//this perspective is that I'm the white tile, looking to flip a black tile
	if (sum==-2) { 
            while (bx<8 && by<8) {
                bx++;
		by++;
		if (hi[bx][by]==0) {return false;} //empty; therefore I cannot flip the tile
                // if (b.getColor(flipx,flipy,b)){return false;}
		else if (hi[bx][by]==2) {return true;}
		}
	}
		
	else if (sum==2) {
            while (0<=bx && 0<=by) {
                bx--;
                by--;
                if (hi[bx][by]==0)  { return false;} //if tile is empty, then there's no way to flip so just return false
                else if (hi[bx][by]==2) {return true;} //
            }
	}
		
	else if (sum==-1) {
            if (bx==wx) {
                while (0<=by) {
                    by--;
                    if (hi[bx][by]==0)  { return false;}
                    else if (hi[bx][by]==2)  { return true;}
		}
            }
            else {
		while (0<=bx) {
                    bx--;
                    if (hi[bx][by]==0)  { return false;}
                    else if (hi[bx][by]==2)  { return true;}
		}
            }
	}
	else if (sum==1) {
            if (bx==wx) {
		while (by<8) {
                    by++;
                    if (hi[bx][by]==0)  { return false;}
                    else if (hi[bx][by]==2)  { return true;}
		}
            }
            else {
		while (bx<8) {
                    bx++;
                    if (hi[bx][by]==0)  { return false;}
                    else if (hi[bx][by]==2)  { return true;}
		}
            }
	}
	else {//sum=0
            if (bx>wx) {
		while (bx<8 && 0<=by) {
                    bx++;
                    by--;
                    if (hi[bx][by]==0)  { return false;}
                    else if (hi[bx][by]==2)  { return true;}
		}
            }
            else { 
		while (by<8 && 0<=bx ) {
                    bx--;
                    by++;
                    if (hi[bx][by]==0)  { return false;}
                    else if (hi[bx][by]==2)  { return true;}
		}
            }
	}
		return false;
    }
	
    public ArrayList<int[]> returnMoves(int[][] board) {//ARRAY OF ARRAYS
        //return possible moves for player2 (who is playing with 2 (i.e. white))
        //rules: empty, adjacent and must flip when placed


        ArrayList<int[]> available = new ArrayList<int[]>();
        //so it should return something like {{1,2}, {2,2}, {3,4}} so each element is an array that represents a single tile. 
        //In total, it represents all possible tiles during a move
        int[][] COPY = new int[8][8];
        int index = 0;

        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                COPY[i][j] = board[i][j];
        }}

        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                if (COPY[i][j]==1) {//if opponent had placed a piece on this cell

                    //find adjacent empty squares
                    int r= i-1; //super long code
                    int c= j-1; //basically if it's one of the four adjacent squares

                    for (int row = r; row<r+3; row++) {
                        for (int col = c; col<c+3; col++) {
                            if (0<= row && row<8 && 0<= col && col<8 && !(row==i && col==j)) {
                                if (COPY[row][col]==0 &&  CanFlipTile(row,col,i,j,board)) {//if there exists an empty adjacent tile
                                    int[] Point = new int[2];
                                    Point[0]=row;
                                    Point[1]=col;
                                    available.add(index, Point);
                                    index++;
                                }
                            }
                        }
                    }
                }
            }
        }
        return available;
    }
    
    
    //pointValue for a specific move. Testable
    public int pointValue(int[] move) { //returns best move for a given board of ints. For example,it might return 
		//point values for each region
    	//CHANGEABLE, MAYBE TEST IT OUT
		final int R5= 10; 
		final int R3= 5;
		final int R1= 0; 
		final int R2= -5;
		final int R4=-10;
		
		//initializing variables
		int pointvalue;
		
		//main code
		int x= move[0];
		int y= move[1];
		if ((x==0 && y==0) || (x==0 && y==7) || (x==7 && y==0) || (x==7 && y==7)) {
			pointvalue = R5;
		}			
		else if (((x==2 || x==3 || x==4 || x==5)&& (y==0 | y==7)) || ((y==2 || y==3 || y==4 || y==5)&& (x==0 | x==7))) {
			pointvalue = R3;
		}
		else if ((2<=x && x<=5) && (2<=y && y<=5)) {
			pointvalue = R1;			
		}
		else if (((x==2 || x==3 || x==4 || x==5)&& (y==1 | y==6)) || ((y==2 || y==3 || y==4 || y==5)&& (x==1 | x==6))) {
			pointvalue = R2;
		}
		else {
			pointvalue = R4;
		}
		
		return pointvalue;
    }
    
    //best values for the whole board, zero levels deep
    /*public int bestValue(int[][] board) { //returns best move for a given board of ints. For example,it might return 
		//point values for each region
    	//CHANGEABLE, MAYBE TEST IT OUT
		
		//initializing variables
		ArrayList<int[]> best = new ArrayList<int[]>(); //RETURNs BEST 3 MOVES!!!!! at most	
		int bestvalue = -100;
		int secondvalue = -100;
		int thirdvalue = -100;
		int pointvalue;
		int index = 0; 
		
		//main code
		ArrayList<int[]> legalMoves = returnMoves(board);
		for (int i=0; i<legalMoves.size(); i++) { 
			int[] move = legalMoves.get(i);
			pointvalue = pointValue(move);
			
			if (index == 0 && pointvalue>bestvalue) {
				bestvalue = pointvalue;
				best.set(index , move);
				index++;
			}
			else if (index == 1) {
				if (pointvalue>bestvalue) {
					secondvalue = bestvalue;
					bestvalue = pointvalue;
					best.set(1, new int[] {best.get(0)[0], best.get(0)[1]});
					best.set(0 , move);
					index++;
				}
				else {
					secondvalue = pointvalue;
					best.set(1 , move);
					index++;
				}
			}
			else {
				if (pointvalue>bestvalue) {
					thirdvalue = secondvalue;
					secondvalue = bestvalue;
					bestvalue = pointvalue;
					best.set(2, new int[] {best.get(1)[0], best.get(1)[1]});
					best.set(1, new int[] {best.get(0)[0], best.get(0)[1]});
					best.set(0 , move);
					index++;
				}
				else if (pointvalue>secondvalue) {
					thirdvalue = secondvalue;
					secondvalue = pointvalue;
					best.set(2, new int[] {best.get(1)[0], best.get(1)[1]});
					best.set(1, move);
					index++;
				}
				else {
					if (pointvalue > thirdvalue) {
						thirdvalue = pointvalue;
						best.set(2, move);
						index++;
					}
				}
			}
		}
		return bestvalue;	
    }*/
    
    public void makeMove(int[][] board, int player, int[] move) {
    	board[move[0]][move[1]]=player;
    }
    
    //checking win
    public boolean checkWin(int[][] board) { 
    	if (returnMoves(board).isEmpty()) {
    		int[][] newBoard = makeReverseBoard(board);
    		if (returnMoves(newBoard).isEmpty()) {
    			return true;
    		}
    	}
    	return false;
    }
    
    //lookAhead
    public int lookAhead(int [][] board, int level, int[] move) {//always minimizing
    	// returns the best value for any possible move
    	if (level==0) { 
    		return pointValue(move);
    	}
    	
    	else {
    		ArrayList<int[]> movelist = returnMoves(board);
    		int bestval = -998;
    		for (int[] myMove : movelist) {
    			int[][] COPY = makeBoardCopy(board);
    			makeReverseBoard(COPY);
    			makeMove(COPY,1,myMove);
    			if (checkWin(COPY)==true) {
    				return -1*1000;
    			}
    			int val = lookAhead(COPY,level-1,myMove);
    			if (val > bestval) {
    				bestval = val;
    			}
    		}
    		return -1*bestval;
    	}
    }
    
    public int[] smartTurn(int[][] board, int level, int token) {
    	// returns best legal move
    	ArrayList<int[]> moveList = returnMoves(board);
    	//random.shuffle(movelist)
    	int bestval = -1001;
    	int[] bestmove = new int[2];
    	for (int[] myMove : moveList) {
    		int[][] COPY = makeBoardCopy(board);
    		if (token==2) {
    			makeReverseBoard(COPY);
    		}
    		makeMove(COPY, 1 , myMove);
    		if (checkWin(COPY)==true) {
    			return myMove;
    		}
    		//val = calcboard(tempBoard,mymove)
    		int val = lookAhead(COPY,level,myMove);
	    	if (val > bestval) {
	    		bestval = val;
	    		bestmove = myMove;
	    	}
    	}
    	return bestmove;
    }
    