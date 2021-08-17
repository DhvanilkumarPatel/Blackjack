package view.guiView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

    private Window window;

    public MouseInput(Window window) {
        this.window = window;
    }

    public void mouseClicked(MouseEvent e) {
        window.checkClick(e.getX(), e.getY());
    }

}
