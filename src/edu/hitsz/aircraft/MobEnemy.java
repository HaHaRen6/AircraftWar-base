package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.application.Publisher;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.prop.AbstractProp;
import edu.hitsz.shootStrategy.ShootStrategy;

import java.util.LinkedList;
import java.util.List;

/**
 * 普通敌机
 * 不可射击
 *
 * @author hitsz
 */
public class MobEnemy extends AbstractAircraft implements Enemy {

    public MobEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }


    @Override
    public List<BaseBullet> shoot() {
        return new LinkedList<>();
    }

    @Override
    public List<AbstractProp> dropProp() {
        return new LinkedList<>();
    }

    @Override
    public void update() {
        vanish();
    }
}
