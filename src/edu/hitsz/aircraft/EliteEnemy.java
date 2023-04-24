package edu.hitsz.aircraft;

import edu.hitsz.application.Game;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.factory.BloodPropFactory;
import edu.hitsz.factory.BombPropFactory;
import edu.hitsz.factory.BulletPropFactory;
import edu.hitsz.factory.PropFactory;
import edu.hitsz.prop.AbstractProp;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * 精英敌机
 * <p>
 * 【工厂模式】实现接口的实体类，充当具体产品角色
 * <p>
 * 【策略模式】客户端(client)：根据 shootStrategy 产生特定的子弹
 *
 * @author hhr
 */
public class EliteEnemy extends AbstractAircraft implements Enemy {
    /**
     * 子弹伤害
     */
    private int power = 30;

    /**
     * 子弹射击方向 (向上发射：1，向下发射：-1)
     */
    private int direction = 1;

    /**
     * @param locationX 英雄机位置x坐标
     * @param locationY 英雄机位置y坐标
     * @param speedX    英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param speedY    英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param hp        初始生命值
     */
    public EliteEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    /**
     * 通过射击产生子弹
     *
     * @return 射击出的子弹List
     */
    public List<BaseBullet> shoot() {
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
        int speedX = 0;
        int speedY = this.getSpeedY() + direction;
        PropFactory propFactory = null;

        // 随机掉落一种道具（可能不掉）
        Random randomProp = new Random();
        int randomPropInt = randomProp.nextInt(10);

        if (randomPropInt < 3) {
            propFactory = new BloodPropFactory();
        } else if (randomPropInt < 6) {
            propFactory = new BombPropFactory();
        } else if (randomPropInt < 9) {
            propFactory = new BulletPropFactory();
        }
        if (propFactory != null) {
            propRes.add(propFactory.createProp(x, y, speedY));
        }
        return propRes;
    }

    @Override
    public void update() {
        vanish();
    }
}
