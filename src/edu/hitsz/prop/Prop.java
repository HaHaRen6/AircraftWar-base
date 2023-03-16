package edu.hitsz.prop;

import edu.hitsz.application.Main;
import edu.hitsz.basic.AbstractFlyingObject;

/**
 * 道具类。
 *
 * @author hhr
 */
public abstract class Prop extends AbstractFlyingObject {

//    private int power = 10;

    public Prop(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
//        this.power = power;
    }

    @Override
    public void forward() {
        super.forward();

        // 判定 x 轴出界
        if (locationX <= 0 || locationX >= Main.WINDOW_WIDTH) {
            vanish();
        }

        // 判定 y 轴出界
        if (speedY > 0 && locationY >= Main.WINDOW_HEIGHT) {
            // 向下飞行出界
            vanish();
        } else if (locationY <= 0) {
            // 向上飞行出界
            vanish();
        }
    }

    public void active() {}
}
