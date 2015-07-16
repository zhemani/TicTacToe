//Class to insert configurations into a dictionary and play the game
//TicTacToe class
public class TicTacToe {
//Declaring instance variables to be used throughout the game
	private int board_size;
	private int inline;
	private char [][] gameBoard;
	//No use for max_levels in this class
	
	public TicTacToe (int board_size, int inline, int max_levels) {
		this.board_size = board_size;
		this.inline = inline;
		//Putting an empty space to start for each location on the gameBoard
		gameBoard = new char [board_size] [board_size];
		for ( int i = 0; i < board_size; i++) {
			for ( int j = 0; j < board_size; j++) {
			gameBoard[i][j] = ' ';
			}	
		}
	}
	
	//Method to create new Dictionary object
	public Dictionary createDictionary() {
		//Using 4799 as a prime number under 5000 for the size of the dictionary 
		Dictionary dict = new Dictionary(4799);
		return dict;
	}
	
	//Method to ensure the configuration isn't repeated
	public int repeatedConfig(Dictionary configurations) {
		String contents = "";
		//Puts the given configuration into a string 
		for ( int i = 0; i <board_size; i++) {
			for ( int j = 0; j < board_size; j++) {
				contents = contents + gameBoard[i][j];
			}
		}
		//Returns either the score of the given config(String) or -1 if not found
		return configurations.find(contents);
	}

	//Method to insert various configurations into the dictionary 
	public void insertConfig(Dictionary configurations, int score) {
		String contents = "";
		//Puts the given configuration into a string
		for ( int i = 0; i < board_size; i++) {
			for ( int j = 0; j < board_size; j++) {
				contents = contents + gameBoard[i][j];
			}
		}
		//Makes a DictEntry object and then inserts it into the Dictionary 
		DictEntry pair = new DictEntry(contents, score);
		//Throws exception as declared in the Dictionary Class
		try {
			configurations.insert(pair);
		} catch (DictionaryException e) {
		System.out.println(e.getMessage());	
		}
	}
	
	//Method to store a symbol in the given row and col coordinate 
	public void storePlay(int row, int col, char symbol) {
		gameBoard[row][col] = symbol;
	}
	
	//Method to check if the location in the parameters contains a symbol
	public boolean squareIsEmpty (int row, int col) {
		if (gameBoard[row][col] == ' ') {
			return true;
	    }
	    else {
	    	return false;
	    }
	}
	
	//Wins method to determine when there a 'k' occurances in a line of the same symbol
	public boolean wins (char symbol) {
		int count = 0;
		boolean win = false;
		//Check vertical
		for ( int i = 0; i < board_size && count != inline; i++) {
			//Count is set to 0 multiple times to ensure that when at a new column, count begins at 0
			count=0;
			for ( int j = 0; j < board_size && count != inline; j++) {
				//When the same symbol is found, count is incremented
				if ( gameBoard[i][j] == symbol) {
					count++;
				}
				else {
					count = 0;
				}	
			}
		}
		//Return value for the method if there is a winning combo
		if (count == inline) win = true;
		
		//Check horizontal
		//If statement to ensure that the game hasn't already been won
		if (count != inline){
		   for ( int i = 0; i < board_size && count != inline; i++ ) {
			   //Setting counter to zero, if jumps to new row
			   count = 0;
			  for ( int j = 0; j < board_size && count!= inline; j ++) {
				  //If there are occurances of the same symbol then increment count
				 if ( gameBoard[j][i] == symbol) {
					count++;
				 }
				 else {
					count = 0;
				 }
			  }
		   }
		   //Return value for the method if there is a winning combo
		if (count == inline) win = true;
		}
		
		
		//Count diagonal1
		//Ensure the game hasn't already been won
		if(count != inline) {
		   for ( int i = 0; i < board_size && count != inline; i++) {
			   //Resetting the counter to 0 if jumps to new diagonal
			  count = 0;
			  for ( int j = 0; j < board_size && count != inline; j++) {
				 count = 0;
			     if(gameBoard[i][j] == symbol) {
			    	 //Checks if there is occurance of the same symbol
			    	 //Loop to get diagonal values, x+1 y+1 etc 
			        for (int x = i, y = j; x < board_size && y < board_size && count != inline && gameBoard[x][y] == symbol; x++,y++){
			        	count++;
			        }
			     }
			     else { 
			        count = 0;
			     }
			  }
		   }
		   //Return value for the method if there is a winning combo
		if (count == inline) win = true;
		}
					
		//Count diagonal2
		//Check to ensure the game hasn't already been won
		if(count != inline) {
			for (int i = board_size -1; i >= 0 && count != inline; i--) {
				count = 0;
				//Resetting count to 0, if jumps to new diagonal 
				for (int j = 0; j < board_size && count != inline; j++) {
					count = 0;
					if (gameBoard[i][j] == symbol) {
						//Check to see if there is a match to symbol
						//For loop to get diagonal, x, y values 
						for (int x = i, y = j; x >= 0 && y < board_size && count!= inline && gameBoard[x][y] == symbol; x--, y++){
							count++;
						}
					}
					else { 
					   count = 0; 
					}
				}
			}
			//Return value for the method if there is a winning combo
			if (count == inline) win = true;
		}
		//Method returns 
		return win;
	}
	
//Method to determine whether there is a draw during the game
	public boolean isDraw() {
		boolean draw = false;
		//Ensure that neither symbol X or O has won the game
		if (!wins('X') && !wins('O')) {
		  for ( int i = 0; i < board_size && !draw; i++) {
			  for ( int j = 0; j < board_size && !draw; j++) {
				  //Checks to ensure there are no empty spaces left on the gameBoard
				  if(squareIsEmpty(i,j)) {
					  draw = true;
				  }
				  else {
					  draw = false;
				  }
			  }
		  }
		}
		else {
		}
		//Method returns
		return !draw;
	 }	
	
	//Method to assign scores to the different combinations
	public int evalBoard() {
		//If O wins then return the value 3
		if (wins('O')) {
			return 3;
		}
		//If X wins then return the value 0
		else if (wins('X')) {
			return 0;
		}
		//If there is a draw then return the value 2
		else if (isDraw()) {
			return 2;
		}//If there are still empty spaces and nothing has occured return 1
		else {
			return 1;	
		}
	}
	
}//End of class



