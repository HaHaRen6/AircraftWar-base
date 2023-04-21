package edu.hitsz.DAO;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 【数据访问对象模式】DAO实现类
 *
 * @author hhr
 */
public class ScoreDaoImpl implements ScoreDao {
    private List<ScoreInfo> scoreInfos;

    public ScoreDaoImpl() {
        scoreInfos = new ArrayList<>();
    }

    public void addItem(ScoreInfo scoreInfo, File scoreFile) {
        OutputStream fOut = null;
        OutputStreamWriter writer = null;

        try {
            // 构建FileOutputStream对象,文件不存在会自动新建
            fOut = new FileOutputStream(scoreFile, true);

            // 构建OutputStreamWriter对象,参数可以指定编码,默认为操作系统默认编码
            writer = new OutputStreamWriter(fOut, "UTF-8");

            // 写入到缓冲区
            writer.append(String.valueOf(scoreInfo.getScore())).append("\t");
            writer.append(scoreInfo.getName()).append("\t");
            writer.append(scoreInfo.getDate()).append("\n");

            // 刷新缓冲区（不知道有没有用）
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

    public void getAllItems(File scoreFile) {
        BufferedReader reader = null;
        FileReader fileReader = null;

        try {
            fileReader = new FileReader(scoreFile);
            reader = new BufferedReader(fileReader);
            String line = "";
            while ((line = reader.readLine()) != null) {
                // 按 \t 分割，存在parts里
                String[] parts = line.split("\t");

                // 写入数据对象
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
        scoreInfos.sort((b, a) -> {
            return a.getScore() - b.getScore();
        });
    }

    public String[][] outPutItems() {
        int cnt = 0;
        String[][] str = new String[scoreInfos.size() + 1][4];
        for (ScoreInfo s : scoreInfos) {
            str[cnt][0] = String.valueOf(cnt + 1);
            str[cnt][1] = String.valueOf(s.getScore());
            str[cnt][2] = s.getName();
            str[cnt][3] = s.getDate();
            cnt++;
        }
        return str;
    }

    public void deleteByTime(String[][] str, File scoreFile) {
        OutputStream fOut = null;
        OutputStreamWriter writer = null;

        try {
            // 构建FileOutputStream对象,文件不存在会自动新建
            fOut = new FileOutputStream(scoreFile);

            // 构建OutputStreamWriter对象,参数可以指定编码,默认为操作系统默认编码
            writer = new OutputStreamWriter(fOut, StandardCharsets.UTF_8);

            for (int i = 0; i < str.length - 1; i++) {
                if (!Objects.equals(str[i][3], "delete")) {
                    for (int j = 1; j < str[i].length; j++) {
                        writer.append(str[i][j]).append("\t");
                    }
                    writer.append("\n");
                }
            }

            // 刷新缓冲区（不知道有没有用）
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                //关闭写入流,同时会把缓冲区内容写入文件
                assert writer != null;
                writer.close();

                //关闭输出流,释放系统资源
                assert fOut != null;
                fOut.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
