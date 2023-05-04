package edu.hitsz.bullet;

import edu.hitsz.application.Main;
import edu.hitsz.application.Subscriber;
import edu.hitsz.basic.AbstractFlyingObject;

/**
 * 子弹类。
 * 也可以考虑不同类型的子弹
 *
 * @author hitsz
 */
public abstract class BaseBullet extends AbstractFlyingObject implements Subscriber {

    private int power = 10;

    public BaseBullet(int locationX, int locationY, int speedX, int speedY, int power) {
        super(locationX, locationY, speedX, speedY);
        this.power = power;
    }

    @Override
    public void forward() {
        super.forward();
    }

    public int getPower() {
        return power;
    }

    /**
     * 【观察者模式】对炸弹爆炸的反应
     */
    public abstract void update();
}
