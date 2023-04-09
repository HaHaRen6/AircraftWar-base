package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.HeroBullet;
import edu.hitsz.prop.AbstractProp;
import edu.hitsz.shootStrategy.DirectShootStrategy;
import edu.hitsz.shootStrategy.ShootStrategy;

import java.util.LinkedList;
import java.util.List;

/**
 * 英雄飞机，游戏玩家操控
 *
 * @author hitsz
 */
public class HeroAircraft extends AbstractAircraft {

    /**攻击方式 */

    /**
     * 子弹一次发射数量
     */
    private int shootNum = 1;

    /**
     * 子弹伤害
     */
    private int power = 30;

    /**
     * 子弹射击方向 (向下发射：1，向上发射：-1)
     */
    private int direction = -1;

    private volatile static HeroAircraft heroAircraft;

    /**
     * @param locationX 英雄机位置x坐标
     * @param locationY 英雄机位置y坐标
     * @param speedX    英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param speedY    英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param hp        初始生命值
     */
    private HeroAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }


    /**
     * 【单例模式】创建英雄机
     *
     * @return 创建好的英雄机
     */
    public static HeroAircraft getHeroAircraft() {
        if (heroAircraft == null) {
            synchronized (HeroAircraft.class) {
                if (heroAircraft == null) {
                    heroAircraft = new HeroAircraft(
                            Main.WINDOW_WIDTH / 2,
                            Main.WINDOW_HEIGHT - ImageManager.HERO_IMAGE.getHeight(),
                            0, 0, 1000);
                    heroAircraft.setShootStrategy(new DirectShootStrategy());
                }
            }
        }
        return heroAircraft;
    }

    @Override
    public void forward() {
        // 英雄机由鼠标控制，不通过forward函数移动
    }


    @Override
    /**
     * 通过射击产生子弹
     *
     * 【策略模式】客户端(client)：利用 shootStrategy 产生特定的子弹
     *
     * @return 射击出的子弹List
     */
    public List<BaseBullet> shoot() {
        return shootStrategy.shoot(getLocationX(), getLocationY(), getSpeedY(), direction, power);
    }

}
