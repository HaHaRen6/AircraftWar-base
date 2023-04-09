package edu.hitsz.shootStrategy;

import edu.hitsz.bullet.BaseBullet;

import java.util.List;

/**
 * 【策略模式】策略(strategy)：接口是所有具体策略的通用接口
 *
 * @author hhr
 */
public interface ShootStrategy {
    List<BaseBullet> shoot(int aircraftX, int aircraftY, int aircraftSpeedY, int direction, int power);
}
