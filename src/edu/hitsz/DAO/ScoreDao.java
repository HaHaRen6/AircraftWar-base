package edu.hitsz.DAO;

/**
 * @author hhr
 */
public interface ScoreDao {
    /**
     * 往文件内写入分数
     *
     * @param score 待写入的分数
     */
    void addItem(int score);

    /**
     * 从txt文件获取对象
     */
    void getAllItems();

    /**
     * 排序分数
     */
    void sortByScore();

    /**
     * 打印分数
     */
    void outPutItems();

}
