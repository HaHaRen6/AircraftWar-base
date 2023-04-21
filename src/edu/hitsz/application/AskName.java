package edu.hitsz.application;

import edu.hitsz.DAO.ScoreDao;
import edu.hitsz.DAO.ScoreInfo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class AskName {
    private JPanel mainPanel;
    private JButton enterButton;
    private JTextField textField;
    private JLabel Label;
    private String name;

    public AskName(ScoreInfo scoreInfo, ScoreDao scoreDao, File scoreFile) {
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scoreInfo.setName(textField.getText());
                scoreDao.addItem(scoreInfo,scoreFile);
                scoreDao.getAllItems(scoreFile);
                scoreDao.sortByScore();

                // 成绩表
                ScoreTable scoreTable = new ScoreTable(scoreDao,scoreFile);
                Main.cardPanel.add(scoreTable.getMainPanel());
                Main.frame.setSize(512,768);
                Main.cardLayout.last(Main.cardPanel);
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
