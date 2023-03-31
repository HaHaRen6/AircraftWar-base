package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.factory.EliteEnemyFactory;
import edu.hitsz.factory.EnemyFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EliteEnemyTest {
    private EnemyFactory enemyFactory;
    private AbstractAircraft enemyAircrafts;
    private List<BaseBullet> bullets;

    @BeforeEach
    void setUp() {
        System.out.println("**--- Executed before each test method in this class ---**");
        enemyFactory = new EliteEnemyFactory();
        enemyAircrafts = enemyFactory.createEnemy();
        bullets = new LinkedList<>();
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
        bullets = enemyAircrafts.shoot();
        assertEquals(1, bullets.size());
    }
}