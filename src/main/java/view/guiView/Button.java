package view.guiView;

import java.awt.*;

public class Button implements GameObject {
    private int x, y, width, height;
    private String text;
    private boolean isClicked;

    public Button(String text, int x, int y, int width, int height) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        isClicked = false;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRoundRect(x, y, width, height, 10, 10);
        g.setColor(new Color(80, 41, 0));
        g.fillRoundRect(x + 1, y + 1, width - 2, height - 2, 10, 10);
        g.setColor(Color.WHITE);
        g.setFont(new Font("SansSerif", Font.PLAIN, 16));
        g.drawString(text, x + 4, y + 16);
    }

    public void click(int mx, int my) {
        if (x <= mx && x + width >= mx && y <= my && y + height >= my) {
            this.isClicked = true;
            onClick();
        }
    }

    public void onClick() {

    }

    public boolean isClicked() {
        return isClicked;
    }

    public void resetclick() {
        this.isClicked = false;
    }

    public void setText(String text) {
        this.text = text;
    }

}
