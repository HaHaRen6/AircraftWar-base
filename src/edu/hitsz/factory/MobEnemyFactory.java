package edu.hitsz.factory;

import edu.hitsz.aircraft.MobEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

/**
 * 【工厂模式】实现工厂接口的具体工厂类，充当具体创建者角色
 *
 * @author hhr
 */
public class MobEnemyFactory implements EnemyFactory {

    @Override
    public MobEnemy createEnemy() {
        return new MobEnemy((int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth()) + ImageManager.MOB_ENEMY_IMAGE.getWidth()),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.03),
                0,
                6,
                30);
    }
}

