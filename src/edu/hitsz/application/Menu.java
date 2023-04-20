package edu.hitsz.application;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    private JPanel menuPanel;
    private JButton easyModeButton;
    private JButton mediumModeButton;
    private JButton hardModeButton;
    private JCheckBox music;
    private static JFrame frame;

    public static final int WINDOW_WIDTH = 512;
    public static final int WINDOW_HEIGHT = 768;

    public Menu() {
        easyModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game game = new EasyGame();
                Main.cardPanel.add(game);
                game.action();
                Main.cardLayout.last(Main.cardPanel);
            }
        });
        mediumModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game game = new MediumGame();
                Main.cardPanel.add(game);
                game.action();
                Main.cardLayout.last(Main.cardPanel);
            }
        });
        hardModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game game = new HardGame();
                Main.cardPanel.add(game);
                game.action();
                Main.cardLayout.last(Main.cardPanel);
            }
        });
    }

    public JPanel getMainPanel() {
        return menuPanel;
    }
}
