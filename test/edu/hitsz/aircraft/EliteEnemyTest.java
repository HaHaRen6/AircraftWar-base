package edu.hitsz.aircraft;

import edu.hitsz.factory.EliteEnemyFactory;
import edu.hitsz.factory.EnemyFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EliteEnemyTest {
    private EnemyFactory enemyFactory;

    private AbstractAircraft enemyAircrafts;

    @BeforeEach
    void setUp() {
        System.out.println("**--- Executed before each test method in this class ---**");
        enemyFactory = new EliteEnemyFactory();
        enemyAircrafts = enemyFactory.createEnemy();
    }

    @AfterEach
    void tearDown() {
        System.out.println("**--- Executed after each test method in this class ---**");
        enemyFactory = null;
        enemyAircrafts = null;
    }

    @Test
    void notValid() {
        System.out.println("**--- Test notValid method executed ---**");
        assertEquals(false, enemyAircrafts.notValid());
    }

    @Test
    void shoot() {
        System.out.println("**--- Test shoot method executed ---**");
        assertEquals(1, enemyAircrafts.shoot().size());
    }
}