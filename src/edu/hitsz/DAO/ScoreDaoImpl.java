package edu.hitsz.DAO;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author hhr
 */
public class ScoreDaoImpl implements ScoreDao {
    private List<ScoreInfo> scoreInfos;

    public ScoreDaoImpl() {
        scoreInfos = new ArrayList<>();
    }

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
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm");

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

    public void getAllItems() {
        File scoreFile = new File("score/score.txt");
        BufferedReader reader = null;
        FileReader fileReader = null;

        try {
            fileReader = new FileReader(scoreFile);
            reader = new BufferedReader(fileReader);
            String line = "";
            while ((line = reader.readLine()) != null) {
                // 按 \t 分割
                String[] parts = line.split("\t");

                ScoreInfo temp = new ScoreInfo();
                temp.setScore(Integer.parseInt(parts[0]));
                temp.setName(parts[1]);
                temp.setDate(parts[2]);
                scoreInfos.add(temp);
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


    public void sortByScore() {
        Collections.sort(scoreInfos, (b, a) -> {
            return a.getScore() - b.getScore();
        });
    }

    public void outPutItems() {
        int i = 1;
        for (ScoreInfo s : scoreInfos) {
            System.out.println("第" + i++ + "名\t" + s.getScore() + "\t" + s.getName() + "\t" + s.getDate());
        }
    }
}
