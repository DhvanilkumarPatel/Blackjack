package view;

import game.GameController;
import game.GameState;
import player.Player;

public abstract class GameView {
    private GameController controller;

    public void setController(GameController controller) {
        this.controller = controller;
    }

    public abstract void updateView(Player player, Player dealer, GameState state);

    public abstract boolean getHumanDrawDecision();

    public abstract int getBet(int money);

    public abstract boolean startRound(int money);

    public abstract int getHumanAceDecision();

}
