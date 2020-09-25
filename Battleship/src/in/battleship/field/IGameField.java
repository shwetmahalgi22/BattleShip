package in.battleship.field;

import in.battleship.game.Result;


public interface IGameField {
    char getIcon();

	Result shootAt();
}
