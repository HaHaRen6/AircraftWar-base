package edu.hitsz.factory;

import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

/**
 * EliteEnemyFactory
 *
 * @author hhr
 */
public class BossEnemyFactory implements EnemyFactory {

    @Override
    public BossEnemy createEnemy() {
        return new BossEnemy((int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.ELITE_ENEMY_IMAGE.getWidth())),
                (int) (ImageManager.BOSS_ENEMY_IMAGE.getHeight() / 2),
                5,
                0,
                240);
    }
}