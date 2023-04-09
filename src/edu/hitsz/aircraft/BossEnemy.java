package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.factory.BloodPropFactory;
import edu.hitsz.factory.BombPropFactory;
import edu.hitsz.factory.BulletPropFactory;
import edu.hitsz.factory.PropFactory;
import edu.hitsz.prop.AbstractProp;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Boss敌机
 * <p>
 * 【工厂模式】实现接口的实体类，充当具体产品角色
 *
 * @author hhr
 */
public class BossEnemy extends AbstractAircraft implements Enemy {

    /* 攻击方式 */

    /**
     * 子弹一次发射数量
     */
    private final int shootNum = 3;

    /**
     * 道具掉落数量
     */
    private final int dropNum = 3;

    /**
     * 子弹伤害
     */
    private final int power = 30;

    /**
     * 子弹射击方向 (向上发射：1，向下发射：-1)
     */
    private final int direction = 1;

    /**
     * @param locationX 英雄机位置x坐标
     * @param locationY 英雄机位置y坐标
     * @param speedX    英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param speedY    英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param hp        初始生命值
     */
    public BossEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    /**
     * 可飞行对象根据速度移动
     * 若飞行对象触碰到横向边界，横向速度反向
     */
    public void forward() {
        locationX += speedX;
        locationY += speedY;
        if (locationX <= 140 || locationX >= Main.WINDOW_WIDTH - 140) {
            // 横向超出边界后反向
            speedX = -speedX;
        }
    }

    @Override
    /**
     * 通过射击产生子弹
     *
     * @return 射击出的子弹List
     */
    public List<BaseBullet> shoot() {
//        List<BaseBullet> res = new LinkedList<>();
//        int x = this.getLocationX();
//        int y = this.getLocationY() + direction * 60;
//        int speedX = 0;
//        BaseBullet bullet;
//        for (int i = 0; i < shootNum; i++) {
//            // 子弹发射位置相对飞机位置向前偏移
//            // 多个子弹横向分散
//            bullet = new EnemyBullet(x + (i * 2 - shootNum + 1) * 3, y, speedX + (i * 2 - shootNum + 1), 12, power);
//            res.add(bullet);
//        }
//        return res;
        return shootStrategy.shoot(getLocationX(), getLocationY(), getSpeedY(), direction, power);
    }

    @Override
    /**
     * 通过掉落产生道具
     * @return 掉落的道具List
     */
    public List<AbstractProp> dropProp() {
        List<AbstractProp> propRes = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() + direction * 2;
        PropFactory propFactory = null;

        // 随机掉落三种道具
        for (int i = 0; i < dropNum; i++) {
            Random randomProp = new Random();
            int randomPropInt = randomProp.nextInt(10);
            if (randomPropInt < 4) {
                propFactory = new BloodPropFactory();
            } else if (randomPropInt < 7) {
                propFactory = new BombPropFactory();
            } else {
                propFactory = new BulletPropFactory();
            }
            propRes.add(propFactory.createProp(x + (i * 2 - shootNum + 1) * 30, y + 95, 7));
        }
        return propRes;
    }
}
