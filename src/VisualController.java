import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class VisualController implements IController {
  private TTTOperations model;
  private VisualView view;

  VisualController(TTTOperations model, VisualView view) {
    this.model = model;
    this.view = view;
  }


  @Override
  public void playGame(TTTOperations Newmodel, int boardSize) throws IOException {
    model.startGame(boardSize);
    this.view.getRestart().addActionListener(new Restart(this, view));
      this.view.makeVisible();
      this.view.getTttPanel().addMouseListener(new Example(model, view));
  }
}
