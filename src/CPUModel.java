import java.util.ArrayList;
import java.util.List;

public class CPUModel extends TTTModel {
    private int rowIndex;
    private int columnIndex;
    private TileStatus ts;

    public CPUModel(ArrayList<ArrayList<TileStatus>> board) {
        super(board);
        rowIndex = 0;
        columnIndex = 0;
        ts = null;
    }

    /**
     * organizes the logic of how a computer player would decide how to play and
     * places the move accordingly.
     */
    public void doComputerMove(MouseAction ex) {
        if (this.nextMoveWins() && ts.equals(TileStatus.O)) {
            super.placeMove(TileStatus.O, rowIndex, columnIndex);
            ex.itterateCounter();
        } else if (this.nextMoveWins() && ts.equals(TileStatus.X)) {
            super.placeMove(TileStatus.O, rowIndex, columnIndex);
            ex.itterateCounter();
        } else if (this.centerIsOpen()) {
            super.placeMove(TileStatus.O, 2, 2);
            ex.itterateCounter();
        } else if ((this.isOpenCorner())) {
            super.placeMove(TileStatus.O, rowIndex, columnIndex);
            ex.itterateCounter();
        } else {
            this.pickFirstOpen();
            ex.itterateCounter();
        }


    }

    private boolean centerIsOpen() {
        return board.get(1).get(1).equals(TileStatus.E);
    }

    private void pickFirstOpen() {
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.size(); j++) {
                if (board.get(i).get(j).equals(TileStatus.E)) {
                    super.placeMove(TileStatus.O, i + 1, j + 1);
                    return;
                }
            }
        }
    }

    private boolean nextMoveWins() {
        if (rowAlmostComplete()) {
            return true;
        } else if (columnAlmostComplete()) {
            return true;
        } else if (diagonalAlmostComplete()) {
            return true;
        } else {
            return false;
        }
    }


    private boolean isOpenCorner() {
        if (board.get(0).get(0).equals(TileStatus.E)) {
            rowIndex = 1;
            columnIndex = 1;
            return true;
        } else if (board.get(0).get(2).equals(TileStatus.E)) {
            rowIndex = 1;
            columnIndex = 3;
            return true;
        } else if (board.get(2).get(0).equals(TileStatus.E)) {
            rowIndex = 3;
            columnIndex = 1;
            return true;
        } else if (board.get(2).get(2).equals(TileStatus.E)) {
            rowIndex = 3;
            columnIndex = 3;
            return true;
        } else return false;
    }

    private boolean rowAlmostComplete() {
        for (int i = 0; i < board.size(); i++) {
            if (this.almostAllTheSame(board.get(i))) {
                for (int j = 0; j < board.size(); j++) {
                    if (board.get(i).get(j).equals(TileStatus.E)) {
                        columnIndex = j + 1;
                    }
                }
                rowIndex = i + 1;
                this.setAlmostWinner(board.get(i));
                return true;
            }
        }
        return false;
    }

    private boolean columnAlmostComplete() {
        List<TileStatus> temp = new ArrayList<>();
        for (int i = 0; i < board.size(); i++) {

            for (int j = 0; j < board.size(); j++) {
                columnIndex = i + 1;
                temp.add(this.board.get(j).get(i));

            }
            if (this.almostAllTheSame(temp)) {
                for (int j = 0; j < board.size(); j++) {
                    if (board.get(j).get(i).equals(TileStatus.E)) {
                        rowIndex = j + 1;
                    }
                }
                this.setAlmostWinner(temp);
                return true;
            } else {
                temp.clear();
            }
        }
        return false;
    }

    private boolean diagonalAlmostComplete() {
        List<TileStatus> temp = new ArrayList<>();
        for (int i = 0; i < board.size(); i++) {
            temp.add(board.get(i).get(i));

        }

        if (this.almostAllTheSame(temp)) {
            for (int i = 0; i < temp.size(); i++) {
                if (temp.get(i).equals(TileStatus.E)) {
                    rowIndex = i + 1;
                    columnIndex = i + 1;
                }
            }
            this.setAlmostWinner(temp);
            return true;
        } else {
            temp.clear();
        }

        for (int i = 0; i < board.size(); i++) {
            temp.add(board.get(i).get((board.size() - 1) - i));
        }

        if (this.almostAllTheSame(temp)) {
            for (int i = 0; i < temp.size(); i++) {
                if (temp.get(i).equals(TileStatus.E)) {
                    rowIndex = i + 1;
                    columnIndex = 3 - i;
                }
            }
            this.setAlmostWinner(temp);
            return true;
        }
        return false;
    }

    private boolean almostAllTheSame(List<TileStatus> temp) {
        if (temp.get(0).equals(TileStatus.X) &&
                temp.get(1).equals(TileStatus.X) &&
                temp.get(2).equals(TileStatus.E)) {
            return true;
        } else if (temp.get(0).equals(TileStatus.O) &&
                temp.get(1).equals(TileStatus.O) &&
                temp.get(2).equals(TileStatus.E)) {
            return true;
        } else if (temp.get(0).equals(TileStatus.X) &&
                temp.get(1).equals(TileStatus.E) &&
                temp.get(2).equals(TileStatus.X)) {
            return true;
        } else if (temp.get(0).equals(TileStatus.O) &&
                temp.get(1).equals(TileStatus.E) &&
                temp.get(2).equals(TileStatus.O)) {
            return true;

        } else if (temp.get(0).equals(TileStatus.E) &&
                temp.get(1).equals(TileStatus.X) &&
                temp.get(2).equals(TileStatus.X)) {
            return true;
        } else if (temp.get(0).equals(TileStatus.E) &&
                temp.get(1).equals(TileStatus.O) &&
                temp.get(2).equals(TileStatus.O)) {
            return true;
        } else {
            return false;
        }
    }

    private void setAlmostWinner(List<TileStatus> temp) {
        for (TileStatus t: temp) {
            if (t.equals(TileStatus.X)) {
                ts = TileStatus.X;
                break;
            }
        }
        for (TileStatus t: temp) {
            if (t.equals(TileStatus.O)) {
                ts = TileStatus.O;
                break;
            }
        }
    }
}
