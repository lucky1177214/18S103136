package hit.chess.go;

import hit.chess.adt.Game;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;


public class GoGame extends Game{

    public static  void playChess()
    {
        JFrame jFrame = new JFrame();
        //设置标题
        jFrame.setTitle("Go Chess");
        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.BLACK);
        jPanel.setLayout(new BorderLayout());
        jFrame.add(jPanel);
        jPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        GoBoard board = new GoBoard();
        jPanel.add(board);
        jFrame.pack();
        jFrame.setResizable(false);
        jFrame.setLocationByPlatform(true);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
