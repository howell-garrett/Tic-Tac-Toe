import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class Restart implements ActionListener {
  IController controller;
  VisualView view;

  public Restart(IController controller, VisualView view) {
    this.controller = controller;
    this.view = view;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (controller instanceof VisualController) {
      TTTOperations model = new TTTModel(new ArrayList<ArrayList<TileStatus>>());
      String arg[] = new String[1];
      arg[0] = "visual";
      try {
        Main.main(arg);
      } catch (IOException i) {
        System.out.println(i.getMessage());
      }
    } else if (controller instanceof CPUController) {
      TTTOperations model = new CPUModel(new ArrayList<ArrayList<TileStatus>>());
      String arg[] = new String[1];
      arg[0] = "cpu";
      try {
        Main.main(arg);
      } catch (IOException i) {
        System.out.println(i.getMessage());
      }
    }
  }
}
