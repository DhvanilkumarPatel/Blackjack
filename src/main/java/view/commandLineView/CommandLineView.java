package view.commandLineView;

import cards.Card;
import cards.Deck;
import game.GameState;
import player.Player;
import view.GameView;

import java.util.Scanner;

public class CommandLineView extends GameView {

    Scanner scanner;

    public CommandLineView() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void updateView(Player player, Player dealer, GameState state) {
        if (state == GameState.PLAYER_WIN) {
            System.out.println("Player won");
            System.out.println("Money remaining: " + player.getMoney());
        } else if (state == GameState.DEALER_WIN) {
            System.out.println("Dealer won");
            System.out.println("Money remaining: " + player.getMoney());
        } else if (state == GameState.TIE) {
            System.out.println("Tie");
            System.out.println("Money remaining: " + player.getMoney());
        } else {
            updateInGameView(player, dealer);
        }
    }

    private void updateInGameView(Player player, Player dealer) {
        Deck playerDeck = player.getDeck();
        Deck dealerDeck = dealer.getDeck();

        System.out.print("Dealer Cards: ");
        for (int i = 0; i < dealerDeck.size(); i++) {
            Card card = dealerDeck.getCard(i);
            String name = card.getValueName() + card.getSuite() + " ";
            System.out.print(name);
        }
        System.out.print(" (Sum: " + dealerDeck.getTotalVisibleValue() + ")");
        System.out.println();

        System.out.print("Player Cards: ");
        for (int i = 0; i < playerDeck.size(); i++) {
            Card card = playerDeck.getCard(i);
            String name = card.getValueName() + card.getSuite() + " ";
            System.out.print(name);
        }
        System.out.print(" (Sum: " + playerDeck.getTotalVisibleValue() + ")");
        System.out.println();
    }

    public boolean getHumanDrawDecision() {
        System.out.println("Draw Card?");
        boolean draw = scanner.nextBoolean();
        return draw;
    }

    public int getBet(int money) {
        System.out.println("Money: " + money);
        System.out.println("Set bet");
        return scanner.nextInt();
    }

    public boolean startRound(int money) {
        System.out.println("Start game?");
        return scanner.nextBoolean();
    }

    public int getHumanAceDecision() {
        System.out.println("Ace value?");
        int value = 0;
        do {
            value = scanner.nextInt();
        } while (!(value == 1 || value == 11));
        return value;
    }
}
