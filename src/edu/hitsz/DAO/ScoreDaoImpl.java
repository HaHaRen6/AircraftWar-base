package edu.hitsz.DAO;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * @author hhr
 */
public class ScoreDaoImpl implements ScoreDao {
    public void addItem(int score) {

        File scoreFile = new File("score/score.txt");
        OutputStream fOut = null;
        OutputStreamWriter writer = null;

        try {
            // 构建FileOutputStream对象,文件不存在会自动新建
            fOut = new FileOutputStream(scoreFile, true);

            // 构建OutputStreamWriter对象,参数可以指定编码,默认为操作系统默认编码
            writer = new OutputStreamWriter(fOut, "UTF-8");

            Date date = new Date();
            String string = "testUser";
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd\tHH:mm");

            // 写入到缓冲区
            writer.append(String.valueOf(score)).append("\t").append(string).append("\t");
            writer.append(dateFormat.format(date)).append("\n");

            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                //关闭写入流,同时会把缓冲区内容写入文件
                writer.close();

                //关闭输出流,释放系统资源
                fOut.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void getAllItem() {
        File scoreFile = new File("score/score.txt");

        BufferedReader reader = null;
        FileReader fileReader = null;
        ArrayList<String> items = new ArrayList<String>();
        String[][] data = new String[1000][4];
        int cnt;
        cnt = 0;

        try {
            fileReader = new FileReader(scoreFile);
            reader = new BufferedReader(fileReader);
            String line = "";
            while ((line = reader.readLine()) != null) {
                // 将所有记录添加到items中
                items.add(line);
                String[] parts = line.split("\t");

                data[cnt] = parts;
                int i = cnt;
                while (i > 0 && Integer.parseInt(data[i][0]) > Integer.parseInt(data[i - 1][0])) {
                    parts = data[i];
                    data[i] = data[i - 1];
                    data[i - 1] = parts;
                    i--;
                }
                cnt++;
            }

            // 打印排序好的记录
            for (int i = 0; i < cnt; i++) {
                System.out.print("第"+(i+1)+"名\t");
                for (String s : data[i]) {
                    System.out.print(s+"\t");
                }
                System.out.println();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

}
