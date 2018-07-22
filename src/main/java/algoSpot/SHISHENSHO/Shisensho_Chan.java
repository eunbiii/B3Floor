package algoSpot.SHISHENSHO;
import java.util.ArrayList;
import java.util.Scanner;

public class Shisensho_Chan {

	public static void main(String[] args) {

		Scanner scn = new Scanner(System.in);

		int loop = scn.nextInt();
		int[] answer = new int[loop];

		for (int T = 0; T < loop; T++) {

			int H = scn.nextInt();
			int W = scn.nextInt();

			scn.nextLine();

			char[][] board = new char[H][];
			ArrayList<tile> letters = new ArrayList<tile>();

			for (int i = 0; i < H; i++) {
				board[i] = scn.nextLine().toCharArray();
				for (int j = 0; j < W; j++) {
					if (board[i][j] != '.') {
						letters.add(new tile(i, j, board[i][j]));
					}

				}
			}

			int size = letters.size();
			
			for (int i = 0; i < size - 1; i++) {
				
				for (int j = i + 1; j < size; j++) {
					
					if (letters.get(i).letter == letters.get(j).letter) {
						
						char backup1 = board[letters.get(i).row][letters.get(i).col];
						char backup2 = board[letters.get(j).row][letters.get(j).col];
						
						board[letters.get(i).row][letters.get(i).col] = '.';
						board[letters.get(j).row][letters.get(j).col] = '.';
						
						if (findPath(board, letters.get(i), letters.get(j))) {
							answer[T]++;
						}
						
						board[letters.get(i).row][letters.get(i).col] = backup1;
						board[letters.get(j).row][letters.get(j).col] = backup2;
					}
				}
			}
		}
		
		scn.close();
		
		for (int i = 0; i < loop; i++) {
			System.out.println(answer[i]);
		}
	}

	static boolean findPath(char[][] board, tile t1, tile t2) {
		// RCC
		for (int i = 0; i < board.length; i++) {
			if ( isRow(board, i, t1.col, t2.col) 
					&& isCol(board, t1.col, t1.row, i) 
					&& isCol(board, t2.col, t2.row, i) ) {
				return true;
			}
		}
		
		// CRR
		for (int i = 0; i < board[0].length; i++) {
			if ( isCol(board, i, t1.row, t2.row)
					&& isRow(board, t1.row, t1.col, i)
					&& isRow(board, t2.row, t2.col, i) ) {
				return true;
			}
		}
		
		return false;
	}

	static boolean isRow(char[][] board, int row, int col1, int col2) {
		
		int _col1 = (col1 < col2) ? col1 : col2;
		int _col2 = (col1 < col2) ? col2 : col1;
		
		for (int i = _col1; i <= _col2; i++) {
			if (board[row][i] != '.')
				return false;
		}
		
		return true;
	}

	static boolean isCol(char[][] board, int col, int row1, int row2) {
		
		int _row1 = (row1 < row2) ? row1 : row2;
		int _row2 = (row1 < row2) ? row2 : row1;
		
		for (int i = _row1; i <= _row2; i++) {
			if (board[i][col] != '.')
				return false;
		}
		
		return true;
	}

}

class tile {
	int row;
	int col;
	char letter;

	public tile(int i, int j, char c) {
		row = i;
		col = j;
		letter = c;
	}
}
