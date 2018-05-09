import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class Restart implements ActionListener {
  VisualController controller;
  VisualView view;

  public Restart(VisualController controller, VisualView view) {
    this.controller = controller;
    this.view = view;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    TTTOperations model = new TTTModel(new ArrayList<ArrayList<TileStatus>>());
    String arg[] = new String[1];
    arg[0] = "visual";
    try {
      Main.main(arg);
    } catch (IOException i) {
      System.out.println(i.getMessage());
    }
  }
}
