import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class VisualView extends JFrame{
  private TTTPanel tttPanel;
  private JPanel buttonPanel;
  private ArrayList<ArrayList<TileStatus>> board;
  private JButton restart;
  private JButton quit;

  public VisualView(ArrayList<ArrayList<TileStatus>> board) {
    super();
    this.setTitle("Tic Tac Toe!");
    this.setSize(500, 550);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    //button panel
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    restart = new JButton("Restart");
    quit = new JButton("Quit");
    quit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });
    buttonPanel.add(restart);
    buttonPanel.add(quit);
    this.add(buttonPanel, BorderLayout.SOUTH);

    this.board = board;
    this.tttPanel = new TTTPanel(board);
    this.add(tttPanel, BorderLayout.CENTER);
   // tttPanel.addMouseListener(new Example());

  }


  public void refresh() {
    this.repaint();
  }

  public void makeVisible() {
    this.setVisible(true);
  }

  public TTTPanel getTttPanel() {
    return tttPanel;
  }

  public void setIsGameOver(boolean b, TileStatus t) {
    tttPanel.setGameOver(b, t);
  }

  public void setCounter(int i) {
    tttPanel.setCounter(i);
  }

  public JButton getRestart() {
    return restart;
  }

  public void setBoard(ArrayList<ArrayList<TileStatus>> board) {
    this.board = board;
  }
}
