package edu.hitsz.bullet;

import edu.hitsz.application.Subscriber;

/**
 * @Author hitsz
 */
public class EnemyBullet extends BaseBullet implements Subscriber {

    public EnemyBullet(int locationX, int locationY, int speedX, int speedY, int power) {
        super(locationX, locationY, speedX, speedY, power);
    }

    /**
     * 【观察者模式】对炸弹爆炸的反应
     */
    public void update() {
        vanish();
    }

}
