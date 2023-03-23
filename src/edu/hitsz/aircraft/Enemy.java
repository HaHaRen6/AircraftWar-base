package edu.hitsz.aircraft;

import edu.hitsz.prop.AbstractProp;

import java.util.List;

/**
 * 【工厂模式】Enemy接口，充当产品角色
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
}
