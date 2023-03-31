package edu.hitsz.factory;

import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

/**
 * EliteEnemyFactory
 *
 * @author hhr
 */
public class EliteEnemyFactory implements EnemyFactory {

    @Override
    public EliteEnemy createEnemy() {
        return new EliteEnemy((int) (Math.random() * (Main.WINDOW_WIDTH - 2 * ImageManager.ELITE_ENEMY_IMAGE.getWidth()) + ImageManager.ELITE_ENEMY_IMAGE.getWidth()),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.03),
                0,
                6,
                90);
    }
}
