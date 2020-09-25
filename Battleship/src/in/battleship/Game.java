package in.battleship;

import in.battleship.player.GameBoard;
import in.battleship.player.PlayerBoard;

public class Game {
	private PlayerBoard player;
	private GameBoard gameBoard;

	public Game() {
		this.player = new PlayerBoard();
		this.gameBoard = new GameBoard();
	}

	public void start() {
		this.gameBoard.placeShips();
		int attempts = 0;
		while (player.getTotalLivesLeft() > 0) {
			player.fireAt(gameBoard);
			
			attempts++;
		}
		System.out.printf("%nGreat, you hit all the ships in %s attempts ..!", attempts);
	}
}