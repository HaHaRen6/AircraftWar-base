package edu.hitsz.application;

import javax.swing.*;
import java.awt.*;

/**
 * 程序入口
 *
 * @author hitsz
 */
public class Main {
    static final CardLayout cardLayout = new CardLayout(0, 0);
    static final JPanel cardPanel = new JPanel(cardLayout);
    public static JFrame frame;
    public static boolean GameMusic;

    public static final int WINDOW_WIDTH = 512;
    public static final int WINDOW_HEIGHT = 768;

    public static void main(String[] args) {
        // 获得屏幕的分辨率，初始化 Frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame = new JFrame("AircraftWar");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setResizable(false);
        //设置窗口的大小和位置,居中放置
        frame.setBounds(((int) screenSize.getWidth() - WINDOW_WIDTH) / 2, 0,
                WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(cardPanel);

        Menu start = new Menu();
        cardPanel.add(start.getMainPanel());
        frame.setVisible(true);
    }
}
