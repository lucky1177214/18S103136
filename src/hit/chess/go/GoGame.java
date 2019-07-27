package hit.chess.go;

import hit.chess.adt.Game;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;


public class GoGame extends JFrame{

    public static  void playChess()
    {

        JFrame f = new JFrame();
        f.setTitle("Go Chess");
//        File file = new File("ico.png");
//        Image ico = null;
//        try{
//            ico = ImageIO.read(file);
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            if(ico==null)
//                System.out.print("ico read error");  //Debug flag 2018.12.19 7pm
//        }
//        f.setIconImage(ico);
        JPanel container = new JPanel();
        container.setBackground(Color.BLACK);
        container.setLayout(new BorderLayout());
        f.add(container);
        container.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        GoBoard board = new GoBoard();
        container.add(board);
        f.pack();
        f.setResizable(false); //移动会导致坐标失去有效性
        f.setLocationByPlatform(true);
        f.setVisible(true);
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
