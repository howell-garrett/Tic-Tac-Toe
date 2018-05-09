
import java.util.ArrayList;
import java.util.List;

public class TTTModel implements TTTOperations {
  protected ArrayList<ArrayList<TileStatus>> board;

  public TTTModel(ArrayList<ArrayList<TileStatus>> board) {
    this.board = board;
  }

  @Override
  public void startGame(int boardSize) {
    for (int i = 0; i < boardSize; i++) {
      board.add(new ArrayList<TileStatus>());
    }
    for (int i = 0; i < board.size(); i++) {
      for (int j = 0; j < board.size(); j++) {
        board.get(i).add(TileStatus.E);
      }
    }
  }

  @Override
  public void placeMove(TileStatus XorO, int columnValue, int rowValue) throws IllegalArgumentException {
    if (columnValue > board.size() || rowValue > board.size()) {
      System.out.print(board.size());
      throw new IllegalArgumentException("That space does not exist, try again.");
    }

    if (XorO.equals(TileStatus.E)) {
      throw new IllegalArgumentException("Can't input an empty space.");
    }

    if (board.get(columnValue - 1).get(rowValue - 1).equals(TileStatus.E)) {
      this.board.get(columnValue - 1).remove(rowValue - 1);
      this.board.get(columnValue - 1).add(rowValue - 1, XorO);

    } else {
      throw new IllegalArgumentException("that space is full.");
    }
  }


  @Override
  public String getGameState() {
    StringBuilder b = new StringBuilder();
    for (int i = 0; i < board.size(); i++) {
      b.append(" ___  ");
    }
    b.append("\n");

    for (int i = 0; i < board.size(); i++) {
      for (int j = 0; j < board.size(); j++) {
        b.append("| " + this.formatEnum(board.get(i).get(j)) + " | ");

      }
      b.append("\n");
      for (int j = 0; j < board.size(); j++) {
        b.append("|___| ");
      }
      //b.append("|___| " + "|___|" + " |___|" + "");
      b.append("\n");

    }
    return b.toString();
  }

  @Override
  public ArrayList<ArrayList<TileStatus>> getBoard() {
    return board;
  }

  @Override
  public void doComputerMove(Example counter) {
    //irrelevant for this implementation
  }


  private String formatEnum(TileStatus t) {
    if (t.equals(TileStatus.E)) {
      return " ";
    } else if (t.equals(TileStatus.O)) {
      return "O";
    } else return "X";
  }

  @Override
  public boolean isGameOver() {
    if (this.columnComplete() || this.rowComplete() || this.diagonalComplete()) {
      return true;
    } else {
      for (int i = 0; i < board.size(); i++) {
        for (int j = 0; j < board.size(); j++) {
          if (board.get(i).get(j).equals(TileStatus.E)) {
            return false;
          }
        }
      }
    }
    return true;
  }


  public boolean diagonalComplete() {
    List<TileStatus> temp = new ArrayList<>();
    for (int i = 0; i < board.size(); i++) {
      temp.add(board.get(i).get(i));
    }

    if (this.allTheSame(temp)) {
      return true;
    } else {
      temp.clear();
    }

    for (int i = 0; i < board.size(); i++) {
      temp.add(board.get(i).get((board.size() - 1) - i));
    }

    if (this.allTheSame(temp)) {
      return true;
    }
    return false;
  }

  public boolean rowComplete() {
    for (int i = 0; i < board.size(); i++) {
      if (this.allTheSame(board.get(i))) {
        return true;
      }
    }
    return false;
  }

  public boolean columnComplete() {
    List<TileStatus> temp = new ArrayList<>();
    for (int i = 0; i < board.size(); i++) {
      for (int j = 0; j < board.size(); j++) {
        temp.add(this.board.get(j).get(i));
      }
      if (this.allTheSame(temp)) {
        return true;
      } else {
        temp.clear();
      }
    }
    return false;
  }

  public boolean allTheSame(List<TileStatus> temp) {
    TileStatus check = temp.get(0);
    if (check.equals(TileStatus.E)) {
      return false;
    }
    for (TileStatus t : temp) {
      if (!t.equals(check)) {
        return false;
      }
    }
    return true;
  }
}
