package edu.hitsz.shootStrategy;

import edu.hitsz.bullet.BaseBullet;

import java.util.List;

/**
 * @author hhr
 */
public interface ShootStrategy {
    List<BaseBullet> shoot(int aircraftX, int aircraftY, int aircraftSpeedY, int direction, int power);
}
