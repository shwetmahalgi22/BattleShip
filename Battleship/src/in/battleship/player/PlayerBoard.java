package in.battleship.player;

import java.awt.Point;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import in.battleship.drawnboard.Board;
import in.battleship.drawnboard.BoardIndex;
import in.battleship.game.Result;

public class PlayerBoard {
	private int totalLivesLeft = 5;

	private Board board;
	private Scanner scanner;
	Set<BoardIndex> indexes = new HashSet<>();

	public PlayerBoard() {
		this.board = new Board();
		this.scanner = new Scanner(System.in);
	}

	public Board getBoard() {
		return board;
	}

	public void fireAt(GameBoard opponent) {
		System.out.printf("%nAlright now enter coordinates for your attack: ");

		boolean isPointValid = false;
		while (!isPointValid) {
			try {
				Point point = new Point(scanner.nextInt(), scanner.nextInt());
				int x = (int) point.getX() - 1;
				int y = (int) point.getY() - 1;

				
				BoardIndex index= new BoardIndex();
				index.x =x ;
				index.y = y;
				if(!indexes.contains(index)) {
					indexes.add(index);
					Result result = ((GameBoard) opponent).getBoard().getField(x, y).shootAt();
					
					((GameBoard) opponent).getBoard().printResults(x,y, indexes);
					
					if (result == Result.PARTIAL_HIT || result == Result.DESTROYED) {
						totalLivesLeft--;
					} else {
						System.out.printf("%n Sorry, you missed the shot %n");
					}
					
					
				}else {
					System.out.println("Sorry, duplicate found.");
					((GameBoard) opponent).getBoard().printResults(x,y, indexes);
				}
				isPointValid = true;	
			} catch (IllegalArgumentException e) {
				System.out.printf(e.getMessage());
			}
		}
	}

	public int getTotalLivesLeft() {
		return totalLivesLeft;
	}

}

