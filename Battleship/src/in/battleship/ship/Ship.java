package in.battleship.ship;

import in.battleship.game.Result;

public class Ship {
	private final String name;
	private final int size;
	private int lives;

	public Ship(String name, int size) {
		this.name = name;
		this.size = size;
		this.lives = size;
	}

	public void hit() {
		if (lives > 1) {
			System.out.printf("%nGood shot! The %s was hit %n", name);
			lives--;
		} else {
			System.out.printf("%nBoom! You sunk the ship - %s %n", name);
			lives--;
		}
	}

	public Result getState() {
		if (lives == 0) {
			return Result.DESTROYED;
		} else if (lives < size) {
			return Result.PARTIAL_HIT;
		} else {
			return Result.NO_HIT;
		}
	}

	public String getName() {
		return name;
	}

	public int getSize() {
		return size;
	}
}