package edu.hitsz.prop;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.factory.BloodPropFactory;
import edu.hitsz.factory.EliteEnemyFactory;
import edu.hitsz.factory.EnemyFactory;
import edu.hitsz.factory.PropFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BloodPropTest {
    private PropFactory propFactory;
    private AbstractProp prop;
    private HeroAircraft heroAircraft;

    @BeforeEach
    void setUp() {
        System.out.println("**--- Executed before each test method in this class ---**");
        propFactory = new BloodPropFactory();
        prop = propFactory.createProp(10, 10, 7);
        heroAircraft = HeroAircraft.getHeroAircraft();
        heroAircraft.decreaseHp(500);
    }

    @AfterEach
    void tearDown() {
        System.out.println("**--- Executed after each test method in this class ---**");
        propFactory = null;
        prop = null;
        heroAircraft = null;
    }

    @Test
    void getSpeedY() {
        System.out.println("**--- Test getSpeedY method executed ---**");
        assertEquals(7, prop.getSpeedY());
    }

    @Test
    void active() {
        System.out.println("**--- Test active method executed ---**");
        prop.active(heroAircraft);
        assertEquals(600, heroAircraft.getHp());
    }
}