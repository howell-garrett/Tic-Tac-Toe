import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class TitleScreen extends JFrame {

    public TitleScreen() {
        super();
        ArrayList<ArrayList<TileStatus>> board = new ArrayList<>();
        TTTOperations model = new TTTModel(board);
        TTTOperations CPUModel = new CPUModel(board);
        this.setTitle("Tic Tac Toe!");
        this.setSize(100, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        JButton p1 = new JButton("1 player");
        p1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VisualView view = new VisualView(CPUModel.getBoard());
                IController controller = new CPUController(CPUModel, view);
                try {
                    controller.playGame(CPUModel, 3);
                } catch (IOException io) {

                }
            }
        });
        buttonPanel.add(p1);
        JButton p2 = new JButton("2 player");
        p2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VisualView view = new VisualView(model.getBoard());
                IController controller = new VisualController(model, view);
                try {
                    controller.playGame(model, 3);
                } catch (IOException io) {

                }
            }
        });
        buttonPanel.add(p2);
        buttonPanel.setLayout(new FlowLayout());
        this.add(buttonPanel, BorderLayout.CENTER);

    }
}
