import java.util.Scanner;

public class TicTacToe {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		//create 2-d string array
		String[][] mainBoard = new String[3][3];
		
		// We have two player symbols, which alternate
		String[] symbols = {" X ", " O "};

		// current is used to flip player symbols, plays and win are used to tack the game being over
		int current = 0, plays = 0; boolean win = false;

		// We initially fill the board with spaces
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++)
				mainBoard [i][j] = "   ";
		}

		// Our main loop is a while loop, although it could also be a for
		while (true) {
			// output the current board
			printBoard(mainBoard);

			// get input from the user, using the symbols array and the current marker
			System.out.print("Enter a row (0, 1, or 2) for player" + symbols[current] + ": ");
			int row = Integer.parseInt(input.nextLine());
			System.out.print("Enter a column (0, 1, or 2) for player" + symbols[current] + ": ");
			int col = Integer.parseInt(input.nextLine());

			// we only advance the game if the user makes a legal play
			if (mainBoard[row][col].equals("   ")) {
				// advance the play clocka and update the board
				mainBoard[row][col] = symbols[current]; plays++;

				// did the user just win? is the play clock up?
				win = winCheck(mainBoard);
				if (win || plays == 9)
					break;

				// flip current player symbols
				current = (current + 1) % 2;
			}
			else
				System.out.println("You cannot play in that square - it already has a symbol");
		}

		//display the final board & message
		printBoard(mainBoard);
		if (win)
			System.out.println(symbols[current] + "player wins!");
		else
			System.out.println("Tie game - no winner!");
	}

	//This method ouputs the boards
	public static void printBoard(String[][] board) {
		System.out.print("-------------------\n");

		for (int i = 0; i < board.length; i++) {
			// separate each cell with |
			for (int j = 0; j < board [i].length; j++) {
				System.out.print("| " + board[i][j] + " ");
			} System.out.println("|");
			// separate each line with dashes
			System.out.print("-------------------\n");
		}
		System.out.println();
	}
	
	// This method checks for 3 in a row
	public static boolean winCheck(String[][] board) {
		// assume no win
		boolean win = false;

		for (int i = 0; i < board.length; i++) {
			// check for row-based wins
			if (board[i][0].equals(board[i][1]) && board[i][0].equals(board[i][2]) && !board[i][0].equals("   "))
				win = true;
			//check for col-based wins
			else if (board[0][i].equals(board[1][i]) && board[0][i].equals(board[2][i]) && !board[0][i].equals("   "))
				win = true;
		}
		
		// check for wins on the two diagonals
		if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && !board[1][1].equals("   "))
			win = true;
		else if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && !board[1][1].equals("   "))
			win = true;

		return win;
	}
}