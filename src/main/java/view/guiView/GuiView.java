package view.guiView;

import cards.Deck;
import game.GameState;
import player.Player;
import view.GameView;


public class GuiView extends GameView {

    final static public int WIDTH = 700;
    final static public int HEIGHT = 450;
    final static private String TITLE = "Blackjack";
    private int bet = 0;

    private Window window;
    private CardSpriteSheet spriteSheet;
    private Player emptyPlayer;

    public GuiView() {
        spriteSheet = new CardSpriteSheet();
        window = new Window(WIDTH, HEIGHT, TITLE);
        emptyPlayer = new Player(null, null, 0);
    }


    @Override
    public void updateView(Player player, Player dealer, GameState state) {
        if (state == GameState.PLAYER_WIN) {
            System.out.println("Player won");
            System.out.println("Money remaining: " + player.getMoney());
            GameObject label = GameObjects.getLabel("Player Won", WIDTH / 2 - 35, 350, 95);
            window.addObject(label);
        } else if (state == GameState.DEALER_WIN) {
            System.out.println("Dealer won");
            System.out.println("Money remaining: " + player.getMoney());
            GameObject label = GameObjects.getLabel("Dealer Won", WIDTH / 2 - 40, 350, 100);
            window.addObject(label);
        } else if (state == GameState.TIE) {
            System.out.println("Tie");
            System.out.println("Money remaining: " + player.getMoney());
            GameObject label = GameObjects.getLabel("Tie", WIDTH / 2 - 5, 350, 30);
            window.addObject(label);
        } else {
            updateInGameView(player, dealer);
        }
    }

    private void updateInGameView(Player player, Player dealer) {
        window.clearObject();

        GameObjects.addDeck(spriteSheet, window.getObjects(), dealer.getDeck(), 10, 10, (int) (CardSpriteSheet.SPRITE_WIDTH * 1.2), (int) (CardSpriteSheet.SPRITE_HEIGHT * 1.2 + 10));
        GameObjects.addDeck(spriteSheet, window.getObjects(), player.getDeck(), 10, 165, (int) (CardSpriteSheet.SPRITE_WIDTH * 1.2), (int) (CardSpriteSheet.SPRITE_HEIGHT * 1.2 + 10));

        String dealerSum = "Total: " + dealer.getTotalVisibleValue();
        String playerSum = "Total: " + player.getTotalVisibleValue();
        GameObjects.addLabel(window.getObjects(), "Dealer", 10, 140, 60);
        GameObjects.addLabel(window.getObjects(), "Player", 10, 295, 60);
        GameObjects.addLabel(window.getObjects(), dealerSum, 80, 140, 72);
        GameObjects.addLabel(window.getObjects(), playerSum, 80, 295, 72);
        String money = "Money: " + player.getMoney();
        GameObjects.addLabel(window.getObjects(), money, 162, 295, 200);

        window.repaint();
    }

    public boolean getHumanDrawDecision() {
        Button draw = new Button("Draw", WIDTH / 2 - 50, 380, 45, 22);
        Button hold = new Button("Hold", WIDTH / 2 + 30, 380, 45, 22);
        window.addButton(draw);
        window.addButton(hold);

        while (!draw.isClicked() && !hold.isClicked()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        window.removeButton(draw);
        window.removeButton(hold);

        if (draw.isClicked()) {
            return true;
        } else {
            return false;
        }
    }

    private void setBet() {
        bet = 0;
        Button betLabel = new Button("Bet: " + bet, 10, 350, 100, 22);
        Button setBet = new Button("Set Bet", 10, 380, 70, 22);
        window.addButton(setBet);
        window.addObject(betLabel);

        Button add1 = new Button("Add 1", 90, 380, 70, 22) {
            public void onClick() {
                bet += 1;
                betLabel.setText("Bet: " + bet);
                window.repaint();
            }
        };
        window.addButton(add1);

        Button add10 = new Button("Add 10", 170, 380, 70, 22) {
            public void onClick() {
                bet += 10;
                betLabel.setText("Bet: " + bet);
                window.repaint();
            }
        };
        window.addButton(add10);

        Button add100 = new Button("Add 100", 250, 380, 70, 22) {
            public void onClick() {
                bet += 100;
                betLabel.setText("Bet: " + bet);
                window.repaint();
            }
        };
        window.addButton(add100);

        while (!setBet.isClicked()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getBet(int money) {
        emptyPlayer.setMoney(money);
        updateInGameView(emptyPlayer, emptyPlayer);
        setBet();
        return bet;
    }

    public boolean startRound(int money) {
        Button setBet = new Button("Click to start new game", WIDTH / 2 - 80, 400, 180, 22);
        window.addButton(setBet);
        while (!setBet.isClicked()) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        window.clearObject();
        return true;
    }

    public int getHumanAceDecision() {
        GameObject label = GameObjects.getLabel("Pick Ace Value", WIDTH / 2 - 50, 350, 120);
        window.addObject(label);

        Button select1 = new Button("1", WIDTH / 2 - 50, 380, 40, 22);
        Button select11 = new Button("11", WIDTH / 2 + 30, 380, 40, 22);
        window.addButton(select1);
        window.addButton(select11);

        while (!select1.isClicked() && !select11.isClicked()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        window.removeObject(label);
        window.removeButton(select1);
        window.removeButton(select11);

        if (select1.isClicked()) {
            return 1;
        } else {
            return 11;
        }
    }

}
