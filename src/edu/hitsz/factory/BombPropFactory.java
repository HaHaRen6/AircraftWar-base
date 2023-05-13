package edu.hitsz.factory;

import edu.hitsz.prop.BombProp;

/**
 * BloodPropFactory
 *
 * @author hhr
 */
public class BombPropFactory implements PropFactory {

    @Override
    public BombProp createProp(int x, int y, int speedY) {
        return new BombProp(x, y, 0, speedY);
    }
}
