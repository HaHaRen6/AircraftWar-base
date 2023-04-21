package edu.hitsz.application;

import edu.hitsz.DAO.ScoreDao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * @author hhr
 */
public class ScoreTable {
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JLabel headerLabel;
    private JScrollPane tableScrollPane;
    private JButton deleteButton;
    private JButton closeButton;
    private JTable scoreTable;
    private int cnt = 0;

    public static final int WINDOW_WIDTH = 512;
    public static final int WINDOW_HEIGHT = 768;


    public ScoreTable(ScoreDao scoreDao, File scoreFile, Game game) {

        String[] columnName = {"名次", "成绩", "姓名", "时间"};
        String[][] tableData = scoreDao.outPutItems();

        if (game instanceof EasyGame) {
            headerLabel.setText("成绩表（简单难度）");
        } else {
            if (game instanceof MediumGame) {
                headerLabel.setText("成绩表（中等难度）");
            } else {
                headerLabel.setText("成绩表（困难难度）");
            }
        }

        //表格模型
        DefaultTableModel model = new DefaultTableModel(tableData, columnName) {

            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        //JTable并不存储自己的数据，而是从表格模型那里获取它的数据
        scoreTable.setModel(model);
        tableScrollPane.setViewportView(scoreTable);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int row = scoreTable.getSelectedRow();
                System.out.println("delete row:" + (row + 1));
                int result = JOptionPane.showConfirmDialog(deleteButton,
                        "是否确定中删除？");
                if (JOptionPane.YES_OPTION == result && row != -1) {
                    model.removeRow(row);
                    tableData[row + cnt][3] = "delete";
                    cnt++;
                    scoreDao.deleteByTime(tableData, scoreFile);
                }
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

}
