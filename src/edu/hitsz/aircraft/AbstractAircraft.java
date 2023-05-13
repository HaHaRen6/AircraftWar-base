package edu.hitsz.aircraft;

import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.shootStrategy.ShootStrategy;

import java.util.List;

/**
 * 所有种类飞机的抽象父类：
 * 敌机（BOSS, ELITE, MOB），英雄飞机
 * <p>
 * 【策略模式】上下文(context)：维护指向具体策略的引用，且仅通过策略接口与该对象进行交流
 *
 * @author hitsz + hhr
 */
public abstract class AbstractAircraft extends AbstractFlyingObject {
    /**
     * 生命值
     */
    protected int maxHp;
    protected ShootStrategy shootStrategy;
    protected int hp;

    public AbstractAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY);
        this.hp = hp;
        this.maxHp = hp;
    }

    /**
     * 【策略模式】
     * 设置发射策略
     *
     * @param shootStrategy 射击策略
     */
    public void setShootStrategy(ShootStrategy shootStrategy) {
        this.shootStrategy = shootStrategy;
    }

    public void increaseHp(int increase) {
        hp = Math.min(hp + increase, maxHp);
    }

    public void decreaseHp(int decrease) {
        hp -= decrease;
        if (hp <= 0) {
            hp = 0;
            vanish();
        }
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp){
        this.hp = hp;
    }

    public void setSpeedY(int speedY){
        this.speedY = speedY;
    }

    /**
     * 【策略模式】
     * 飞机射击方法，可射击对象必须实现
     *
     * @return 可射击对象需实现，返回子弹
     * 非可射击对象空实现，返回null
     */
    public abstract List<BaseBullet> shoot();

}


