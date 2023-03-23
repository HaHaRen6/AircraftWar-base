package edu.hitsz.factory;

import edu.hitsz.prop.AbstractProp;

/**
 * 【工厂模式】工厂接口，充当创建者角色
 *
 * @author hhr
 */
public interface PropFactory {
    /**
     * 创建道具
     *
     * @return 创建好的道具
     */
    AbstractProp createProp(int x, int y, int speedY);
}
