package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractAircraft;

/**
 * 【工厂模式】工厂接口，充当创建者角色
 *
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
