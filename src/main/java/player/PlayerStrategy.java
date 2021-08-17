package player;

import game.GameModel;

abstract class PlayerStrategy {

    protected GameModel game;
    protected Player player;

    public PlayerStrategy(GameModel game, Player player) {
        this.game = game;
        this.player = player;
    }

    public abstract int getAceValue();
    public abstract boolean willDraw();

}
