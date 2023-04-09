package edu.hitsz.shootStrategy;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

/**
 * @author hhr
 */
public class DirectShootStrategy implements ShootStrategy{
    /**
     * 通过射击产生子弹
     *
     * @return 射击出的子弹List
     */
    public List<BaseBullet> shoot(int aircraftX, int aircraftY, int aircraftSpeedY, int direction, int power) {
        List<BaseBullet> res = new LinkedList<>();
        int x = aircraftX;
        int y = aircraftY + direction * 2;
        int speedX = 0;
        int speedY = aircraftSpeedY + direction * 7;
        BaseBullet bullet;

        if (direction == -1) {
            bullet = new HeroBullet(x, y, speedX, -11, power);
        } else {
            bullet = new EnemyBullet(x, y, speedX, speedY, power);
            System.out.println("EneDic"+speedY);
        }
        res.add(bullet);

        return res;
    }
}
