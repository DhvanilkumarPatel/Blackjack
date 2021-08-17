package view.guiView;

import cards.Card;
import cards.Deck;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class GameObjects {

    public static GameObject cardToGameObject(CardSpriteSheet sheet, Card card, int x, int y, int width, int height) {
        return (Graphics g) -> {
            BufferedImage img = sheet.getImage(card.getName());
            g.setColor(Color.WHITE);
            g.fillRect(x, y, width, height);
            g.setColor(Color.BLACK);
            g.drawString(card.getValueName() + card.getSuite(), x + 10, y + 10);
            g.drawImage(img, x, y, width, height, null);
        };
    }

    public static void addDeck(CardSpriteSheet sheet, LinkedList<GameObject> objects, Deck deck, int x, int y, int width, int height) {

        GameObject background = (Graphics g) -> {
            g.setColor(new Color(80, 41, 0));
            g.fillRoundRect(x, y, GuiView.WIDTH - 10 - x, height, 10, 10);
        };
        objects.add(background);

        for (int i = 0; i < deck.size(); ++i) {
            GameObject cardObj = cardToGameObject(sheet, deck.getCard(i), x + i * (width + 10) + 5, y + 5, width, height - 10);
            objects.add(cardObj);
        }
    }

    public static void addLabel(LinkedList<GameObject> objects, String text, int x, int y, int width) {
        objects.add(getLabel(text, x, y, width));
    }

    public static GameObject getLabel(String text, int x, int y, int width) {
        return (Graphics g) -> {
            g.setColor(Color.BLACK);
            g.fillRoundRect(x, y, width, 22, 10, 10);
            g.setColor(new Color(80, 41, 0));
            g.fillRoundRect(x + 1, y + 1, width - 2, 20, 10, 10);
            g.setColor(Color.WHITE);
            g.setFont(new Font("SansSerif", Font.PLAIN, 16));
            g.drawString(text, x + 4, y + 16);
        };
    }
}
