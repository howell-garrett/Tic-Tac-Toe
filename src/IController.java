import java.io.IOException;

public interface IController {

  void playGame(TTTOperations model, int boardSize) throws IOException;
}
