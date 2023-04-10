package edu.hitsz.DAO;

/**
 * 【数据访问对象模式】数据对象实体类
 *
 * @author hhr
 */
public class ScoreInfo {
    private int score;
    private String name;
    private String date;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
