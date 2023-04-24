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
     * 道具掉落方法
     *
     * @return 返回道具
     */
    List<AbstractProp> dropProp();

    /**
     * 【观察者模式】对炸弹爆炸的反应
     */
    void update();
}
