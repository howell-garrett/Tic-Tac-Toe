import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.*;

public class TTTPanel extends JPanel {
  private ArrayList<ArrayList<TileStatus>> board;
  private boolean isGameOver;
  JLabel label1;
  JLabel whichTurn;
  TileStatus winner;
  int counter;

  public TTTPanel(ArrayList<ArrayList<TileStatus>> board) {
    this.board = board;
    this.isGameOver = false;
    this.label1 = new JLabel("");
    label1.setFont(new Font("", Font.BOLD, 56));
    label1.setForeground(Color.GRAY);
    label1.setVerticalTextPosition(JLabel.BOTTOM);
    label1.setHorizontalTextPosition(JLabel.CENTER);
    label1.setMinimumSize(new Dimension(100, 100));
    this.add(label1);
    this.whichTurn = new JLabel("");
    whichTurn.setFont(new Font("", Font.BOLD, 18));
    whichTurn.setVerticalTextPosition(JLabel.BOTTOM);
    whichTurn.setHorizontalTextPosition(JLabel.CENTER);
    whichTurn.setMinimumSize(new Dimension(100, 100));
    this.add(whichTurn);
    this.winner = TileStatus.X;
    counter = 0;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(Color.BLACK);
    g2d.fill(new Rectangle2D.Double(166, 0, 5, 500));
    g2d.fill(new Rectangle2D.Double(332, 0, 5, 500));
    g2d.fill(new Rectangle2D.Double(0, 166, 500, 5));
    g2d.fill(new Rectangle2D.Double(0, 332, 500, 5));
    for (int i = 0; i < board.size(); i++) {
      for (int j = 0; j < board.size(); j++) {
        g2d.fill(this.paintCorrectShape(board.get(i).get(j),
                ((j + 1) * 166) - 88, ((i + 1) * 166) - 88, 30, 30));

      }
    }

    if (counter % 2 == 0) {
      whichTurn.setText("■ Player's Turn");
    } else {
      whichTurn.setText("● Player's Turn");
    }

    if (isGameOver) {
      label1.setSize(new Dimension(390, 390));
      whichTurn.setText("");
      if (winner.equals(TileStatus.X)) {
        label1.setText("■ player wins");

      } else if (winner.equals(TileStatus.O)) {
        label1.setText("● player wins");

      } else {
        label1.setText("its a tie");
      }
    }

  }

  private Shape paintCorrectShape(TileStatus t, int x, int y, int w, int h) {
    if (t.equals(TileStatus.X)) {
      return new Rectangle2D.Double(x, y, w, h);
    } else if (t.equals(TileStatus.O)) {
      return new Ellipse2D.Double(x, y, w, h);
    } else return new Ellipse2D.Double(3000, 3000, 1, 1);
  }


  public void setGameOver(boolean b, TileStatus t) {
    isGameOver = b;
    winner = t;

  }

  public void setCounter(int i) {
    counter = i;
  }
}
