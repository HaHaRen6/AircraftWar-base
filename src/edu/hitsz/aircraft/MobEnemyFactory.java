package edu.hitsz.aircraft;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

/**
 * @author hhr
 */
public class MobEnemyFactory implements EnemyFactory {

    @Override
    public MobEnemy createEnemy() {
        return new MobEnemy((int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.03),
                0,
                6,
                30);
    }
}
