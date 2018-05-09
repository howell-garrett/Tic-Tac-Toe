import java.io.IOException;
import java.util.Scanner;

public class TTTController implements IController{
  private final Readable rd;
  private final Appendable ap;

  public TTTController(Readable rd, Appendable ap) {
    if (rd == null || ap == null) {
      throw new IllegalArgumentException("neither readable or appendable can be null");
    }
    this.rd = rd;
    this.ap = ap;
  }

  public void playGame(TTTOperations model, int boardSize) throws IOException {
    model.startGame(boardSize);
    ap.append(model.getGameState());
    Scanner scanner = new Scanner(rd);
    int counter = 1;
    int counter2 = 0;
    int cVal = 0;
    int rVal = 0;
    boolean foundCVal = false;
    boolean foundRVal = false;
    try {
      while (!model.isGameOver()) {
        ap.append(this.whichTurn(counter) + " Player's Turn \n");
        try {
          if (!foundCVal) {
            String next = scanner.next();
            cVal = Integer.parseInt(next);
            foundCVal = true;
          }
        } catch (NumberFormatException e) {
          ap.append("invalid argument \n");
        }

        try {
          if (!foundRVal) {
            String next = scanner.next();
            rVal = Integer.parseInt(next);
            foundRVal = true;
          }
        } catch (NumberFormatException e) {
          ap.append("invalid argument \n");
        }

        if (foundCVal && foundRVal) {
          try {
            model.placeMove(this.whichTurn(counter), cVal, rVal);
            counter2++;
          } catch (IllegalArgumentException e) {
            ap.append(e.getMessage() + "\n");
            counter++;
          }
          ap.append(model.getGameState());
          foundCVal = false;
          foundRVal = false;
          counter++;

        }
      }
    } catch (IOException e) {
      ap.append("Could not start game");
    }

    if (counter2 >= boardSize * boardSize && !model.diagonalComplete() &&
            !model.rowComplete() && !model.columnComplete()) {
      ap.append("It's a draw, Tic Tac Toe is a dumb game");
    } else
      ap.append(this.whichTurn(counter + 1) + " Player wins");

  }

  private TileStatus whichTurn(int i) {
    if (i % 2 == 1) {
      return TileStatus.X;
    } else return TileStatus.O;
  }


}
