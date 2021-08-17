package cards;

import java.util.Collections;
import java.util.LinkedList;

public class Deck {

    private LinkedList<Card> cards;

    public Deck() {
        this.cards = new LinkedList<>();
    }

    public void setDefault() {
        char[] suites = {'S', 'D', 'C', 'H'};
        char[] faces = {'J', 'Q', 'K'};

        for (char suite : suites) {

            for (int val = 2; val <= 10; val++) {
                cards.add(new Card(Integer.toString(val), suite, val, false));
            }

            for (int faceIdx = 0; faceIdx < faces.length; faceIdx++) {
                cards.add(new Card(Character.toString(faces[faceIdx]), suite, 10, false));
            }

            int[] possibleAceValues = {1, 11};
            cards.add(new WildCard("A", suite, false, possibleAceValues));
        }

        Collections.shuffle(cards);
    }

    public int getTotalVisibleValue() {
        int sum = 0;
        for (int i = 0; i < cards.size(); i++) {
            if(cards.get(i).isVisible()) sum += cards.get(i).getValue();
        }
        return sum;
    }

    public int getTotalValue() {
        int sum = 0;
        for (int i = 0; i < cards.size(); i++) {
            sum += cards.get(i).getValue();
        }
        return sum;
    }

    public int getTotalValueIgnoreNotSet() {
        int sum = 0;
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).isValueSet()) sum += cards.get(i).getValue();
        }
        return sum;
    }

    public void addCard(Card card) {
        if (card != null) {
            cards.add(card);
        }
    }

    public Card popCard() {
        if (!cards.isEmpty()) {
            return cards.pop();
        } else {
            System.err.println("Deck popCard(): Deck is empty");
            return null;
        }
    }

    public Card getCard(int i) {
        return cards.get(i);
    }

    public int size() {
        return cards.size();
    }

    public void clearDeck() {
        cards.clear();
    }
}
