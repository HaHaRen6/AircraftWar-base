package edu.hitsz.application;

/**
 * 【观察者模式】订阅者接口
 *
 * @author hhr
 */
public interface Subscriber {
    /**
     * 【观察者模式】对炸弹爆炸的反应
     */
    void update();
}
