package in.battleship.player;

import java.util.Scanner;

import in.battleship.drawnboard.Board;

public class GameBoard {

	private Board board;
	private Scanner scanner;

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public GameBoard() {
		this.board = new Board();
		this.scanner = new Scanner(System.in);
	}

	public Board getBoard() {
		return board;
	}


	public void placeShips() {
		board.placeShipsOnBoard();
	}

}