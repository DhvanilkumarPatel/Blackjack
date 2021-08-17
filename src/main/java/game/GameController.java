package mvc;

import player.Player;
import view.GameView;

public class GameController {

    private GameModel game;
    private GameView view;

    public GameController(GameModel game, GameView view) {
        this.game = game;
        this.view = view;
        game.setController(this);
        view.setController(this);
    }

    public void initGames() {
        game.initGames();
    }

    public void updateView(Player player, Player dealer, GameState state) {
        view.updateView(player, dealer, state);
    }

    public int getBet(int money) {
        return view.getBet(money);
    }

    public boolean startRound() {
        return view.startRound();
    }

    public void updateDealerDeck() {

    }

    public void updatePlayerDeck() {

    }

    public void requestPlayerAceValue() {

    }

    public void requestHumanAceValue() {

    }

    public boolean getHumanDrawDecision() {
        return view.getHumanDrawDecision();
    }

    public int getHumanAceDecision() {
        return view.getHumanAceDecision();
    }

    public void drawCard() {

    }

}
