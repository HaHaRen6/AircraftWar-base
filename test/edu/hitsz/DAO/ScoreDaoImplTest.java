package edu.hitsz.DAO;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ScoreDaoImplTest {
    ScoreDaoImpl test = null;
    @BeforeEach
    void setUp() {
        test = new ScoreDaoImpl();
    }

    @AfterEach
    void tearDown() {
        test = null;
    }

    @Test
    void addItem() throws IOException {
        test.addItem(1);
    }

    @Test
    void getAllItem() {
        test.getAllItem();
    }
}