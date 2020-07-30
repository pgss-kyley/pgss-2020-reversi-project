public void aiplay() { //BLACK MOVE - goes first
        ArrayList<int[]> legalMoves = new ArrayList<>();
        
        int[][] reverseBoard = makeBoardCopy(arrayBoard);
        var aiMoveChoice = new int[2];
        int aiMoveX; int aiMoveY;

        if(!checkWin(arrayBoard) && aiCanMove) {
            //swaps player
            reverseBoard = makeReverseBoard(reverseBoard);

            //AI chooses a move: smartTurn
            //aiMoveChoice = smartTurn(reverseBoard,3,2);         
            //AI chooses a move: smartTurn with capture            
            //aiMoveChoice = smartTurnCapture(reverseBoard,3,2).get(0);
            //AI chooses a move: random move
            //legalMoves = returnMoves(reverseBoard);
            //aiMoveChoice = randomChoice(legalMoves);
            
            aiMoveX = aiMoveChoice[0];
            aiMoveY = aiMoveChoice[1];
            
            if (aiMoveX != -1) { //AI has a legal move available 

                //updates move choice on arrayBoard
                reverseBoard[aiMoveX][aiMoveY] = 2;

                //testing only - places marker on ai-chosen move
                boardList[aiMoveX][aiMoveY].setBackground(Color.RED);

                //update GUI with AI move
                boardList[aiMoveX][aiMoveY].setForeground(Color.BLACK);

                //swaps board again to account for previous reverse
                arrayBoard = makeReverseBoard(reverseBoard);
                //update board with AI's captured picees
                flipTiles(aiMoveChoice,1);
                //updates the JButton array to match that of the arrayBoard
                boardList = storeFromArrayBoard(arrayBoard);
                //updates the GUI based on new boardList
                updateBoard(boardList);

                updateScores();

                //set up for next user turn
                //gets all legal moves for user
                legalMoves = returnMoves(arrayBoard);
                if (!legalMoves.isEmpty()) { //user has legal moves available 
                    //updates GUI to show legal moves
                    showLegal(legalMoves, boardList);
                    
                    //swaps bold font to Player, indicating User's turn
                    scoreField2.setFont(new Font("Arial Black", Font.PLAIN, 24));
                    scoreField1.setFont(new Font("Arial Black", Font.BOLD+Font.ITALIC, 24));
                    
                    aiCanMove = false;
                    play2();
                    
                }
                else { //skip user turn
                    aiplay();
                }
            }
            else { //no legal moves left for AI
                //swaps board again to account for previous reverse
                arrayBoard = makeReverseBoard(reverseBoard);
                //updates the GUI based on new boardList
                updateBoard(boardList);
                    
                //set up for next user turn
                //gets all legal moves for user
                legalMoves = returnMoves(arrayBoard);
                //updates GUI to show legal moves
                showLegal(legalMoves, boardList);
                //swaps bold font to Player, indicating User's turn
                scoreField2.setFont(new Font("Arial Black", Font.PLAIN, 24));
                scoreField1.setFont(new Font("Arial Black", Font.BOLD+Font.ITALIC, 24));
                aiCanMove = false;

                play2();
            }
        }
        else { 
            if (checkWin(arrayBoard)) {//game has been won
                trials++;
                //decide winner
                if (getWhiteScore(arrayBoard) > getBlackScore(arrayBoard)) {    // white wins
                    whiteWins++;
                    if (trials == totalTrials) {
                        printResults();
                    }
                    else {
                        exitButton.setText(trials + "");
                        resetBoard();
                        //aiplay();
                    }
                }
                else if (getWhiteScore(arrayBoard) < getBlackScore(arrayBoard)) {   //black wins
                    blackWins++;
                    if (trials == totalTrials) {
                        printResults();
                    }
                    else {
                        exitButton.setText(trials + "");
                        resetBoard();
                        //aiplay();
                    }
                }
                else { //draw
                    draws++;
                    if (trials == totalTrials) {
                        printResults();
                    }
                    else {
                        exitButton.setText(trials + "");
                        resetBoard();
                        //aiplay();
                    }
                }
                aiCanMove = true;
            }
        }
    }
    
public void play2() { //WHITE MOVES - goes second
        ArrayList<int[]> legalMoves = new ArrayList<>();
        int[][] reverseBoard = makeBoardCopy(arrayBoard);
        int [] compareArray = new int[2];
        var aiMoveChoice = new int[2];
        int x; int y;
        
        //AI chooses smart turn
        //aiMoveChoice = smartTurn(reverseBoard,3,2); 
        //Ai chooses randomly
        //legalMoves = returnMoves(arrayBoard);
        //aiMoveChoice = randomChoice(legalMoves);
        //AI chooses a move: smartTurn with capture            
        //aiMoveChoice = smartTurnCapture(reverseBoard,3,2).get(0);
        
        x = aiMoveChoice[0];
        y = aiMoveChoice[1];
        if (x == 0 && y == 0) {
            arrayBoard[x][y] = 2;
        }
        if (x == 7 && y == 7) {
            arrayBoard[x][y] = 2;
        }
        //testArrayBoard();
        
        if (!checkWin(arrayBoard)) {
            if (x != -1) {
                boardList[x][y].setForeground(Color.BLACK); //reverse, will actually play white
                boardList[x][y].setText("●");
                arrayBoard[x][y] = 2;
                
                //flips captured pieces for player's move
                flipTiles(aiMoveChoice,2);
                updateScores();  //updates score on GUI
                //updates the JButton array to match that of the arrayBoard
                boardList = storeFromArrayBoard(arrayBoard);
                //resets legalmove indicator
                resetLegal(boardList);
                //updates the GUI based on new boardList
                updateBoard(boardList);
                
                //swaps bold font to Computer, indicating AI turn
                scoreField1.setFont(new Font("Arial Black", Font.PLAIN, 24));
                scoreField2.setFont(new Font("Arial Black", Font.BOLD+Font.ITALIC, 24));

                //allow AI to move
                aiCanMove = true;


                aiplay();
            }
            else { //skip other turn
                play2();
            }
        }
        else {
            if (checkWin(arrayBoard)) {//game has been won
                trials++;
                //decide winner
                if (getWhiteScore(arrayBoard) > getBlackScore(arrayBoard)) {    // white wins
                    whiteWins++;
                    if (trials == totalTrials) {
                        printResults();
                    }
                    else {
                        exitButton.setText(trials + "");
                        resetBoard();
                        //aiplay();
                    }
                }
                else if (getWhiteScore(arrayBoard) < getBlackScore(arrayBoard)) {   //black wins
                    blackWins++;
                    if (trials == totalTrials) {
                        printResults();
                    }
                    else {
                        exitButton.setText(trials + "");
                        resetBoard();
                        //aiplay();
                    }
                }
                else { //draw
                    draws++;
                    if (trials == totalTrials) {
                        printResults();
                    }
                    else {
                        exitButton.setText(trials + "");
                        resetBoard();
                        //aiplay();
                    }
                }
                aiCanMove = true;
            }
        }
    }
