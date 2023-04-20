package edu.hitsz.DAO;

import edu.hitsz.application.ScoreTable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class ScoreDaoImplTest {
    ScoreDao test = null;
    ScoreTable scoreTable=null;
    @BeforeEach
    void setUp() {
        test = new ScoreDaoImpl();
        scoreTable = new ScoreTable(test.outPutItems());
    }

    @AfterEach
    void tearDown() {
        test = null;
        scoreTable = null;
    }

    @Test
    void addItem() throws IOException {
//        test.addItem(1);
    }

    @Test
    void getAllItem() {
        test.getAllItems();
        System.out.println("------------------------");
        test.sortByScore();
//        scoreTable.setTableData(test.outPutItems());

        System.out.println("!23");
    }
}