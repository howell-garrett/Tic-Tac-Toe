import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.*;

import static java.lang.Character.isUpperCase;
import static java.lang.Character.isWhitespace;
import static java.lang.Character.toLowerCase;
import static java.lang.Character.toUpperCase;

public class Main {

  public static void main(String[] arg) throws IOException {
        ArrayList<ArrayList<TileStatus>> board = new ArrayList<>();
    TTTOperations model = new TTTModel(board);

    if (arg[0].equals("visual")) {

      VisualView view = new VisualView(model.getBoard());
      IController controller = new VisualController(model, view);
      controller.playGame(model, 3);

    } else if (arg[0].equals("console")) {
      new TTTController(new InputStreamReader(System.in),
              System.out).playGame(model, 3);
    } else {
      JOptionPane.showMessageDialog(null,
              "Invalid Argument",
              "Inane error",
              JOptionPane.ERROR_MESSAGE);
      System.exit(0);
    }
  }
}
