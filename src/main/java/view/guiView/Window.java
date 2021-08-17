package view.guiView;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

public class Window extends Canvas {

    int width;
    int height;
    String title;

    private JFrame frame;
    private MouseInput mouseInput;

    private LinkedList<GameObject> objects;
    private LinkedList<Button> buttons;

    public Window(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
        initWindow();
    }

    private void initWindow() {
        objects = new LinkedList<>();
        buttons = new LinkedList<>();
        mouseInput = new MouseInput(this);
        this.addMouseListener(mouseInput);

        frame = new JFrame();
        frame.setTitle(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        this.setPreferredSize(new Dimension(width, height));
        this.setMaximumSize(new Dimension(width, height));
        this.setMinimumSize(new Dimension(width, height));
        this.setFocusable(false);

        frame.add(this);
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        print(getGraphics());
    }

    public JFrame getFrame() {
        return frame;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(0, 102, 0));
        g.fillRect(0, 0, width, height);

        for (int i = 0; i < objects.size(); ++i) {
            objects.get(i).render(g);
        }
    }

    public LinkedList<GameObject> getObjects() {
        return objects;
    }

    public void addObject(GameObject obj) {
        objects.add(obj);
    }

    public void addButton(Button button) {
        objects.add(button);
        buttons.add(button);
    }

    public void removeObject(GameObject obj) {
        objects.remove(obj);
    }

    public void removeButton(Button button) {
        objects.remove(button);
        buttons.remove(button);
    }

    public void clearObject() {
        objects.clear();
        buttons.clear();
    }

    public void checkClick(int mouseX, int mouseY) {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).click(mouseX, mouseY);
        }
    }

}
