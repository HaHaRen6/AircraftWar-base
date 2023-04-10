package edu.hitsz.DAO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author hhr
 */
public interface ScoreDao {
    /**
     * 往文件内写入分数
     *
     * @param score 待写入的分数
     * @throws IOException
     */
    void addItem(int score);

    /**
     * 获取全部分数，将其排序后输出
     *
     * @throws FileNotFoundException
     */
    void getAllItem();

}
