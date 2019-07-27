package hit.chess.go;

import hit.chess.adt.Board;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class GoBoard extends JPanel {

    private State current_player;
    private GoPosition position;
    private Point lastMove;
    public static final int RowColon_Total = 19;
    public static final int Blocko_Number = RowColon_Total - 1;  // Number of square block
    public static final int Blocko_size = 40; // TODO: Change here (20,60) to adapt your screen size : best for 1080p is 40
    public static final int BORDER_SIZE = Blocko_size; //默认 Border layout 边框等于一个格的宽度
    public enum State {
        BLACK, WHITE
    }
    public static boolean show_reddot = true;
    public static boolean show_step_count = false;
    Font yahei = new Font("微软雅黑",Font.PLAIN,Blocko_size / 2);
    Font yahei_small = new Font("微软雅黑",Font.PLAIN,Blocko_size/3);
    public GoBoard() {
        this.setBackground(Color.ORANGE);
        position = new GoPosition(RowColon_Total);
        // Black always starts
        current_player = State.BLACK;

        this.addMouseListener(new MouseAdapter() {
            //Override released function help the click event chaos
            @Override
            public void mouseReleased(MouseEvent e) {
                // generate the cordinate of the click action provide cross point
                int row = Math.round((float) (e.getY() - BORDER_SIZE) / Blocko_size);   // trans row and col into basic blocko point
                int col = Math.round((float) (e.getX() - BORDER_SIZE) / Blocko_size);
                if (row >= RowColon_Total || col >= RowColon_Total || row < 0 || col < 0) {
                    return;
                }
                if (position.isOccupied(row, col)) {
                    return;
                }
//                if (position.issuicide(row,col,current_player)){
//                    GoGame.statusbar.setText("检测到自杀棋");
//                    return;
//                }
                position.addStone(row, col, current_player);
                lastMove = new Point(col, row);
                // if black next white   it white next black
                current_player = (current_player==State.BLACK) ? State.WHITE:State.BLACK;
                // Already been switched player :call back control panel's statusbar
                // Call back for ControlPanel
//                if(current_player==State.BLACK)
//                {
//                    Control_GUI_Panel.statusbar.setText("等待黑棋");
//                }
//                else
//                {
//                    Control_GUI_Panel.statusbar.setText("等待白棋");
//                }
                repaint();
            }
        });
    }

    @Override
    public void paintComponent(Graphics g)
    {
        File f_black = new File("goblack.png");
        File f_white = new File("gowhite.png");
        Image black_stone = null;Image white_stone = null;
        try
        {
            black_stone = ImageIO.read(f_black);
            white_stone = ImageIO.read(f_white);
        }catch (IOException e) {
            e.printStackTrace();
            System.out.print("Stone image error"+" 棋子图片读取错误");  // Debug usage!!! mijazz 18.12.19  18.32
        }
        finally {
            if (black_stone == null || white_stone == null)
            {
                System.out.print("Stone image error"+" 棋子图片读取错误");  // Debug usage!!! mijazz 18.12.19  18.35
            }
        }


        //up are stone img read and polygon(triangle) make   Debug IOExcept
        // below are draw code

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Anti aliasing (抗锯齿)
        g2.setColor(Color.BLACK);
        g2.setFont(yahei);
        // Draw row lines here
        for (int i = 0; i < RowColon_Total; i++) {
            g2.drawLine(BORDER_SIZE, i * Blocko_size + BORDER_SIZE, Blocko_size * Blocko_Number + BORDER_SIZE, i * Blocko_size + BORDER_SIZE);
        }
        // Draw colon line here
        for (int i = 0; i < RowColon_Total; i++) {
            g2.drawLine(i * Blocko_size + BORDER_SIZE, BORDER_SIZE, i * Blocko_size + BORDER_SIZE, Blocko_size * Blocko_Number + BORDER_SIZE);
        }
        for (int row = 0; row < RowColon_Total; row++) {
            for (int col = 0; col < RowColon_Total; col++) {
                int x = col * Blocko_size + BORDER_SIZE - Blocko_size / 2;
                int y = row * Blocko_size + BORDER_SIZE - Blocko_size / 2;

                if (row==3 || row==9 || row==15){
                    if (col==3 || col==9 || col==15){
                        g2.setColor(Color.BLACK);
                        g2.fillOval(x + Blocko_size / 2 - 4,y + Blocko_size / 2 - 4,8,8);
                    }
                }
                // Draw the black point in the chess board
                State state = position.getState(row, col);
                if (state != null) {
                    if (state == State.BLACK) {
                        g2.setColor(Color.WHITE); // use to draw the string (steps), not affecting the stone image.
                        g2.drawImage(black_stone,x,y,Blocko_size,Blocko_size,null,null);
                    } else {
                        g2.setColor(Color.BLACK);  // use to draw the string (steps), not affecting the stone image.
                        g2.drawImage(white_stone,x,y,Blocko_size,Blocko_size,null,null);
                    }
                    // TODO: to prettify: Change here into go chess stone pic!!!
                    // draw stone pic
                }
                int steps = position.getsteps(row, col);
                if (show_step_count && steps != 0){
                    // in order to set Font always in the middle of stone.
                    if (steps < 10){
                        g2.setFont(yahei);
                        g2.drawString(String.valueOf(steps), x + Blocko_size/2 - Blocko_size/6, y + Blocko_size/2 + Blocko_size/6);
                    }
                    else if (steps < 100){
                        g2.setFont(yahei);
                        g2.drawString(String.valueOf(steps), x + Blocko_size/2 - Blocko_size/4, y + Blocko_size/2 + Blocko_size/6);
                    }
                    else {
                        g2.setFont(yahei_small);
                        g2.drawString(String.valueOf(steps), x + Blocko_size/2 - Blocko_size/4, y + Blocko_size/2 + Blocko_size/6);
                    }
                }
            }
        }
        // Highlight last move
        if (show_reddot){
            if (lastMove != null) {
                g2.setColor(Color.RED);
                int x = lastMove.x * Blocko_size + BORDER_SIZE;   // - Blocko_size / 2;
                int y = lastMove.y * Blocko_size + BORDER_SIZE;   // - Blocko_size / 2;
                //TODO: Radius is changable best on 4
                g2.fillOval(x,y,Blocko_size/4,Blocko_size/4);
            }
        }
    }

    @Override()
    public Dimension getPreferredSize() {
        return new Dimension(Blocko_Number * Blocko_size + BORDER_SIZE * 2, Blocko_Number * Blocko_size + BORDER_SIZE * 2);
    }

    public static void setShow_reddot(boolean flag){
        show_reddot = flag;
    }

    public static void setShow_step_count(boolean flag){
        show_step_count = flag;
    }
}
