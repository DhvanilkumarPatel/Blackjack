package player;

import cards.Card;
import cards.Deck;
import game.GameModel;

public class Player {
    private static int numPlayers = 0;

    private GameModel game;

    private int money;
    private Deck cards;
    private PlayerStrategy strategy;
    private int playerID;

    public Player(GameModel game, Strategies strategy, int initalMoney) {
        this.game = game;
        initStrategy(strategy);
        cards = new Deck();
        money = initalMoney;
        playerID = numPlayers;
        numPlayers++;

    }

    private void initStrategy(Strategies strategy) {
        if (strategy == Strategies.Human) this.strategy = new HumanPlayerStrategy(game, this);
        else if (strategy == Strategies.Bot) this.strategy = new BotPlayerStrategy(game, this);
    }

    public int getTotalVisibleValue() {
        return cards.getTotalVisibleValue();
    }

    public int getTotalValue() {
        return cards.getTotalValue();
    }

    public int getTotalValueIgnoreNotSet() {
        return cards.getTotalValueIgnoreNotSet();
    }

    public void addCard(Card card) {
        card.setOwner(this);
        cards.addCard(card);
    }

    public Deck getDeck() {
        return cards;
    }

    public boolean willDraw() {
        return strategy.willDraw();
    }

    public int getAceValue() {
        return strategy.getAceValue();
    }

    public int getID() {
        return playerID;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void incrementMoney(int increment) {
        this.money += increment;
    }

    public void clearHand() {
        cards.clearDeck();
    }


}
