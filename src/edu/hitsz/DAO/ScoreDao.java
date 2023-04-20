package edu.hitsz.DAO;

/**
 * 【数据访问对象模式】DAO接口
 *
 * @author hhr
 */
public interface ScoreDao {
    /**
     * 往txt文件内写入一条记录
     *
     * @param scoreInfo 数据对象
     */
    void addItem(ScoreInfo scoreInfo);

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
    String[][] outPutItems();

    void deleteByTime(String[][] str);

}
