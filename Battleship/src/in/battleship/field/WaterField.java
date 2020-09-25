package in.battleship.field;

import in.battleship.game.Result;


public class WaterField implements IGameField {
    private boolean isThisFieldHit = false;

    @Override
    public char getIcon() {
        return isThisFieldHit ? 'M' : '~';
    }

    @Override
    public Result shootAt() {
        isThisFieldHit = true;
		return Result.NO_HIT;
    }
}