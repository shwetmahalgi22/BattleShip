package in.test;

import java.util.Scanner;

import in.battleship.field.IGameField;
import in.battleship.field.WaterField;
import in.battleship.ship.Ship;
import in.battleship.ship.ShipSize;

public class Test {
	
	
	
	public static void main(String args[]) {
		
		Test1 t = new Test1();
		t.testMethod();
	
	}
}
class Test1{
	

	private static final char WATER = '~';
	private static final int BOARD_SIZE = 10;
	private static final String[] BOARD_LETTERS = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
	private static final String HORIZONTAL = "H";
	private static final String[] DIRECTIONS = { "H", "V" };
	private Scanner scanner;
	private IGameField[][] board;
	private static final Ship[] ships;
	
	static {
		ships = new Ship[] { new Ship("Carrier", ShipSize.CARRIER), new Ship("Battleship", ShipSize.BATTLESHIP),
				new Ship("Destroyer", ShipSize.DESTROYER), new Ship("Submarine", ShipSize.SUBMARINE),
				new Ship("Patrol Boat", ShipSize.PATROLBOAT) };
	}

	

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}
	
	public void testMethod() {
		this.setScanner(new Scanner(System.in));
		this.board = new IGameField[BOARD_SIZE][BOARD_SIZE];
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				board[i][j] = new WaterField();
				System.out.println(board);
			}
		}
	}
}
