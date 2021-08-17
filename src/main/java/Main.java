import game.GameController;
import game.GameModel;
import view.GameView;
import view.guiView.GuiView;

public class Main {
    public static void main (String[] args) {
        GameView view = new GuiView();
        GameModel game = new GameModel();
        GameController controller = new GameController(game, view);
        controller.initGames();
    }
}
