package edu.hitsz.aircraft;

/**
 * @author hhr
 */
public interface EnemyFactory {
    /**
     * 创建敌机
     *
     * @return 创建好的敌机
     */
    AbstractAircraft createEnemy();
}
