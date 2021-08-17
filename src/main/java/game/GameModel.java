package game;

import cards.Deck;
import player.Player;
import player.Strategies;

public class GameModel {

    private final static int INITIAL_PLAYER_MONEY = 100;

    enum GameResult {PLAYER_WIN, DEALER_WIN, TIE}

    GameController controller;
    Deck deck;

    Player dealer;
    Player player;

    GameState state;

    public GameModel() {
        deck = new Deck();
        deck.setDefault();
        dealer = new Player(this, Strategies.Bot, 0);
        player = new Player(this, Strategies.Human, INITIAL_PLAYER_MONEY);
        state = GameState.WAITING_FOR_GAME_INIT;
    }

    public void initGames() {
        if (controller == null) {
            System.err.println("GameModel: Game initalized without controller initialized");
            throw new NullPointerException();
        }

        // start games
        while (player.getMoney() > 0) {
            if (!singleRound()) break;
        }
    }

    private boolean singleRound() {
        state = GameState.WAITING_FOR_ROUND_START;
        if (!controller.startRound(player.getMoney())) return false;

        int bet = getBet();
        GameResult result = startGame();
        if (result == GameResult.PLAYER_WIN) player.incrementMoney(bet);
        else if (result == GameResult.DEALER_WIN) player.incrementMoney(-bet);
        controller.updateView(player, dealer, state);
        return true;
    }

    private GameResult startGame() {
        if (dealer == null || player == null) {
            System.err.println("GameModel: Game started without players initialized");
            throw new NullPointerException();
        }

        state = GameState.GAME_STARTED;

        deck.setDefault();
        player.clearHand();
        dealer.clearHand();


        // Add initial cards
        dealer.addCard(deck.popCard().setVisible(true));
        dealer.addCard(deck.popCard().setVisible(false));
        controller.updateView(player, dealer, state);

        player.addCard(deck.popCard().setVisible(true));
        controller.updateView(player, dealer, state);
        player.addCard(deck.popCard().setVisible(true));
        controller.updateView(player, dealer, state);

        if (hasBlackjack(dealer) || hasBlackjack(player)) {
            dealer.getDeck().getCard(1).setVisible(true);
            controller.updateView(player, dealer, state);
            if (hasBlackjack(dealer) && hasBlackjack(player)) {
                state = GameState.TIE;
                return GameResult.TIE;
            } else if (hasBlackjack(dealer)) {
                state = GameState.DEALER_WIN;
                return GameResult.DEALER_WIN;
            } else {
                state = GameState.PLAYER_WIN;
                return GameResult.PLAYER_WIN;
            }
        }


        // Add cards till player holds or is busted
        state = GameState.WAITING_FOR_DRAW_END;
        while (player.willDraw()) {
            state = GameState.WAITING_FOR_DRAW_END;
            player.addCard(deck.popCard().setVisible(true));
            controller.updateView(player, dealer, state);
            if (isBust(player)) {
                state = GameState.DEALER_WIN;
                return GameResult.DEALER_WIN;
            }
        }

        // make 2nd card visible
        dealer.getDeck().getCard(1).setVisible(true);
        controller.updateView(player, dealer, state);

        // Add cards till dealer golds or is busted
        state = GameState.WAITING_FOR_DRAW_END;
        while (dealer.willDraw()) {
            state = GameState.WAITING_FOR_DRAW_END;
            dealer.addCard(deck.popCard().setVisible(true));
            controller.updateView(player, dealer, state);
            if (isBust(dealer)) {
                state = GameState.PLAYER_WIN;
                return GameResult.PLAYER_WIN;
            }
        }

        if (player.getTotalValue() == dealer.getTotalValue()) {
            state = GameState.TIE;
            return GameResult.TIE;
        } else if (player.getTotalValue() > dealer.getTotalVisibleValue()) {
            state = GameState.PLAYER_WIN;
            return GameResult.PLAYER_WIN;
        } else {
            state = GameState.DEALER_WIN;
            return GameResult.DEALER_WIN;
        }
    }

    public int getBet() {
        int bet = 0;
        while (bet <= 0 || bet > player.getMoney()) {
            bet = controller.getBet(player.getMoney());
        }
        return bet;
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }

    public GameState getState() {
        return state;
    }

    public boolean getHumanDrawDecision() {
        state = GameState.WAITING_FOR_DRAW;
        return controller.getHumanDrawDecision();
    }

    public int getHumanAceDecision() {
        return controller.getHumanAceDecision();
    }

    public Player getPlayer(int playerID) {
        if (dealer.getID() == playerID) return dealer;
        if (player.getID() == playerID) return player;
        return null;
    }

    public Player getPlayer() {
        return player;
    }

    public Player getDealer() {
        return dealer;
    }

    public boolean isBust(Player player) {
        return player.getTotalVisibleValue() > 21;
    }

    public boolean hasBlackjack(Player player) {
        return player.getDeck().size() == 2 && player.getTotalValue() == 21;
    }

}
