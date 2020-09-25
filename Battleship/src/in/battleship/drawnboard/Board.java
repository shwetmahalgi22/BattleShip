package in.battleship.drawnboard;

import java.awt.Point;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import in.battleship.field.IGameField;
import in.battleship.field.ShipField;
import in.battleship.field.WaterField;
import in.battleship.ship.Ship;
import in.battleship.ship.ShipSize;

public class Board {
	private static final char WATER = '~';
	private static final int BOARD_SIZE = 10;
	private static final String[] BOARD_LETTERS = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
	private static final String HORIZONTAL = "H";
	private static final String[] DIRECTIONS = { "H", "V" };
	private Scanner scanner;
	private IGameField[][] board;
	private static final Ship[] ships;
	

	static {
		ships = new Ship[] { 
					new Ship("Carrier",ShipSize.CARRIER), 
					new Ship("Battleship", ShipSize.BATTLESHIP),
					new Ship("Destroyer", ShipSize.DESTROYER), 
					new Ship("Submarine", ShipSize.SUBMARINE),
					new Ship("Patrol Boat", ShipSize.PATROLBOAT) 
					};
	}

	public Board() {
		this.setScanner(new Scanner(System.in));
		this.board = new IGameField[BOARD_SIZE][BOARD_SIZE];
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				board[i][j] = new WaterField();
			}
		}
	}

	public void placeShipsOnBoard() {
		for (Ship ship : ships) {
			boolean horizontal = askValidShipDirection();
			Point startingPoint = askValidStartingPoint(ship, horizontal);
			placeValidShip(ship, startingPoint, horizontal);
		}
		printBoard();
	}

	public IGameField getField(int x, int y) {
		if (!isInsideBoard(x, y)) {
			throw new IllegalArgumentException("Outside board - try again: ");
		}
		return board[y][x];
	}

	public void printBoard() {
		System.out.print("\t");

		for (int i = 0; i < BOARD_SIZE; i++) {
			System.out.print(BOARD_LETTERS[i] + "\t");
		}

		System.out.println();

		for (int i = 0; i < BOARD_SIZE; i++) {
			System.out.print((i + 1) + "\t");
			for (int j = 0; j < BOARD_SIZE; j++) {
				System.out.print(board[i][j].getIcon() + "\t");
			}
			System.out.println();
		}
	}

	public void printResults(int x, int y, Set<BoardIndex> indexes) {
		
		System.out.print("\t");

		for (int i = 0; i < BOARD_SIZE; i++) {
			System.out.print(BOARD_LETTERS[i] + "\t");
		}

		System.out.println();

		for (int i = 0; i < BOARD_SIZE; i++) {
			System.out.print((i + 1) + "\t");
			for (int j = 0; j < BOARD_SIZE; j++) {
				char c = board[i][j].getIcon();
				if (c == 'H') {
					boolean isPrint = false;
					for(BoardIndex indexe : indexes) {
						if (indexe.y == i && indexe.x == j) {
							isPrint = true;
							System.out.print(c + "\t");
						}
					}
					 if(!isPrint) {
						System.out.print('~' + "\t");
					}
				}else {
					if(c=='X') {
						c='~';
						System.out.print(c + "\t");
					}else {
						System.out.print(c + "\t");
					}
					
				}
			}
			System.out.println();
		}
	}

	private boolean askValidShipDirection() {
		Random random = new Random();
		int index = random.nextInt(DIRECTIONS.length);
		return HORIZONTAL.equals(DIRECTIONS[index]);
	}

	private Point askValidStartingPoint(Ship ship, boolean horizontal) {
		Point from;
		do {
			int x = (int) (Math.random() * 10);
			int y = (int) (Math.random() * 10);
			from = new Point(x, y);
		} while (!isValidStartingPoint(from, ship.getSize(), horizontal));

		return from;
	}

	private boolean isValidStartingPoint(Point from, int length, boolean horizontal) {
		int xDiff = 0;
		int yDiff = 0;
		if (horizontal) {
			xDiff = 1;
		} else {
			yDiff = 1;
		}

		int x = (int) from.getX() - 1;
		int y = (int) from.getY() - 1;
		if (!isInsideBoard(x, y) || (!isInsideBoard(x + length, y) && horizontal)
				|| (!isInsideBoard(x, y + length) && !horizontal)) {
			return false;
		}

		for (int i = 0; i < length; i++) {
			if (board[(int) from.getY() + i * yDiff - 1][(int) from.getX() + i * xDiff - 1].getIcon() != WATER) {
				return false;
			}
		}
		return true;
	}

	private void placeValidShip(Ship ship, Point startingPoint, boolean horizontal) {
		int xDiff = 0;
		int yDiff = 0;
		if (horizontal) {
			xDiff = 1;
		} else {
			yDiff = 1;
		}
		for (int i = 0; i < ship.getSize(); i++) {
			board[(int) startingPoint.getY() + i * yDiff - 1][(int) startingPoint.getX() + i * xDiff
					- 1] = new ShipField(ship);
		}
	}

	private boolean isInsideBoard(int x, int y) {
		return x <= BOARD_SIZE && x >= 0 && y <= BOARD_SIZE && y >= 0;
	}

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}
}
