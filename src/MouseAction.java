import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class  MouseAction implements MouseListener {
  TTTOperations model;
  int counter;
  VisualView view;

  public MouseAction(TTTOperations model, VisualView view) {
    this.model = model;
    this.counter = 0;
    this.view = view;

  }


  @Override
  public void mouseClicked(MouseEvent e) {

  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {
    if (model instanceof CPUModel) {
      if (!model.isGameOver()) {
        if (counter % 2 == 0) {
          try {
            if (e.getX() < 151 && e.getY() < 151) {
              model.placeMove(this.correctTurn(counter), 1, 1);
              counter++;
            } else if (e.getX() > 150 && e.getX() < 325 && e.getY() < 151) {
              model.placeMove(this.correctTurn(counter), 1, 2);
              counter++;
            } else if (e.getX() > 325 && e.getX() < 500 && e.getY() < 151) {
              model.placeMove(this.correctTurn(counter), 1, 3);
              counter++;
            } else if (e.getX() < 150 & e.getY() > 150 & e.getY() < 325) {
              model.placeMove(this.correctTurn(counter), 2, 1);
              counter++;
            } else if (e.getX() > 150 && e.getX() < 325 & e.getY() > 150 & e.getY() < 325) {
              model.placeMove(this.correctTurn(counter), 2, 2);
              counter++;
            } else if (e.getX() > 325 & e.getY() > 150 & e.getY() < 325) {
              model.placeMove(this.correctTurn(counter), 2, 3);
              counter++;
            } else if (e.getX() < 150 & e.getY() > 325) {
              model.placeMove(this.correctTurn(counter), 3, 1);
              counter++;
            } else if (e.getX() > 150 && e.getX() < 325 && e.getY() > 325) {
              model.placeMove(this.correctTurn(counter), 3, 2);
              counter++;
            } else if (e.getX() > 325 & e.getY() > 325) {
              model.placeMove(this.correctTurn(counter), 3, 3);
              counter++;
            }
          } catch (IllegalArgumentException i) {
            System.out.println(i.getMessage());
          }
        } else {
          try {
            model.doComputerMove(this);
          } catch (IllegalArgumentException iae) {
            iae.getMessage();
          }
        }
      }
      } else if (model instanceof TTTModel) {
        System.out.print("here");
        if (!model.isGameOver()) {
          try {
            if (e.getX() < 151 && e.getY() < 151) {
              model.placeMove(this.correctTurn(counter), 1, 1);
              counter++;
            } else if (e.getX() > 150 && e.getX() < 325 && e.getY() < 151) {
              model.placeMove(this.correctTurn(counter), 1, 2);
              counter++;
            } else if (e.getX() > 325 && e.getX() < 500 && e.getY() < 151) {
              model.placeMove(this.correctTurn(counter), 1, 3);
              counter++;
            } else if (e.getX() < 150 & e.getY() > 150 & e.getY() < 325) {
              model.placeMove(this.correctTurn(counter), 2, 1);
              counter++;
            } else if (e.getX() > 150 && e.getX() < 325 & e.getY() > 150 & e.getY() < 325) {
              model.placeMove(this.correctTurn(counter), 2, 2);
              counter++;
            } else if (e.getX() > 325 & e.getY() > 150 & e.getY() < 325) {
              model.placeMove(this.correctTurn(counter), 2, 3);
              counter++;
            } else if (e.getX() < 150 & e.getY() > 325) {
              model.placeMove(this.correctTurn(counter), 3, 1);
              counter++;
            } else if (e.getX() > 150 && e.getX() < 325 && e.getY() > 325) {
              model.placeMove(this.correctTurn(counter), 3, 2);
              counter++;
            } else if (e.getX() > 325 & e.getY() > 325) {
              model.placeMove(this.correctTurn(counter), 3, 3);
              counter++;
            }
          } catch (IllegalArgumentException i) {
            System.out.println(i.getMessage());
          }
        }
      }

    if (model.isGameOver()) {
      if (counter >= 9 && !model.diagonalComplete() && !model.rowComplete() && !model.columnComplete()) {
        view.setIsGameOver(true , TileStatus.E);
      } else {
        view.setIsGameOver(true, this.correctTurn(counter + 1));

      }
    }

    this.view.setCounter(counter);
    this.view.refresh();
  }



  private TileStatus correctTurn(int i) {
    if (i % 2 == 0) {
      return TileStatus.X;
    } else {
      return TileStatus.O;
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }

  public int getCounter() {
    return counter;
  }

  public void itterateCounter() {
    counter++;
  }
}
