package edu.hitsz.aircraft;

import edu.hitsz.prop.AbstractProp;

import java.util.List;

/**
 * @author hhr
 */
public interface Enemy {

    /**
     * 前进方法
     */
    void forward();
    // public 和 abstract 都是隐式的

    /**
     * 道具掉落方法
     *
     * @return 返回道具
     */
    List<AbstractProp> dropProp();
}
