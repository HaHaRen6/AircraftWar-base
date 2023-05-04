package edu.hitsz.aircraft;

import edu.hitsz.prop.AbstractProp;

import java.util.List;

/**
 * 【工厂模式】Enemy接口，充当产品角色
 *
 * 【观察者模式】充当订阅者角色
 *
 * @author hhr
 */
public interface Enemy {

    /**
     * @param props 子弹集合
     */
    void dropProp(List<AbstractProp> props);
}
