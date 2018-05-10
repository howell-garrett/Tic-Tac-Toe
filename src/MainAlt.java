import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainAlt {

    public static void main(String[] arg) throws IOException {
        ArrayList<ArrayList<TileStatus>> board = new ArrayList<>();
        TTTOperations model = new TTTModel(board);
        TTTOperations CPUModel = new CPUModel(board);

        if (arg[0].equals("console")) {
            new TTTController(new InputStreamReader(System.in),
                    System.out).playGame(model, 3);
        } else if (arg[0].equals("visual")) {
            TitleScreen ts = new TitleScreen();
            ts.setVisible(true);
        }
    }
}
