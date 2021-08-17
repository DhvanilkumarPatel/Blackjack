import java.util.Collection;
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
            for (int val = 2; val <= 10; ++val) {
                cards.add(new Card(Integer.toString(val), suite, val));
            }

            for (int faceIdx = 0; faceIdx < faces.length; ++faceIdx) {
                cards.add(new Card(Character.toString(faces[faceIdx]), suite, 11 + faceIdx));
            }

            int[] possibleAceValues = {1, 11};
            cards.add(new WildCard("A", suite, possibleAceValues));
        }

        Collections.shuffle(cards);
    }

    public int getTotalValue() {
        int sum = 0;
        for (Card card : cards) {
            sum += card.getValue();
        }
        return sum;
    }

    public void addCard(Card card) {
        if (card != null) {
            cards.add(card);
        }
    }

    public Card getCard() {
        if (!cards.isEmpty()) {
            return cards.pop();
        }
        return null;
    }
}
