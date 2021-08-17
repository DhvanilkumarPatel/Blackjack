package view.guiView;

import cards.Card;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;

public class CardSpriteSheet {

    private static final String PATH = "/deckSpriteSheet.png";
    public static final int SPRITE_WIDTH = 71;
    public static final int SPRITE_HEIGHT = 96;

    private BufferedImage spriteSheet;
    private BufferedImage error;
    private HashMap<String, BufferedImage> cards;

    public CardSpriteSheet() {
        initSheet();
    }

    private void initSheet() {
        try {
            URL url = this.getClass().getResource(PATH);
            spriteSheet = ImageIO.read(url);
        } catch (Exception e) {
            System.err.println("CardSpiteSheet: \"" + PATH + "\" could not be read.");
            e.printStackTrace();
        }

        cards = new HashMap<>();
        // Order based on sprite sheet.
        String suites[] = {"S", "H", "C", "D"};
        String values[] = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        for (int suiteIdx = 0; suiteIdx < suites.length; suiteIdx++) {
            for (int valueIdx = 0; valueIdx < values.length; valueIdx++) {
                BufferedImage img = spriteSheet.getSubimage(SPRITE_WIDTH * valueIdx + 1, SPRITE_HEIGHT * suiteIdx, SPRITE_WIDTH - 1, SPRITE_HEIGHT);
                String name = values[valueIdx] + suites[suiteIdx];
                cards.put(name, img);
            }
        }

        BufferedImage hidden = spriteSheet.getSubimage(1, SPRITE_HEIGHT * 4, SPRITE_WIDTH - 1, SPRITE_HEIGHT);
        cards.put(Card.DEFAULT_HIDDEN_NAME + Card.DEFAULT_HIDDEN_SUITE, hidden);

        error = spriteSheet.getSubimage(SPRITE_WIDTH + 1, SPRITE_HEIGHT * 4, SPRITE_WIDTH - 1, SPRITE_HEIGHT);


    }

    public BufferedImage getImage(String name) {
        return cards.getOrDefault(name, error);
    }

}
