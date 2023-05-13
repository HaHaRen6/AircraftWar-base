package edu.hitsz.factory;

import edu.hitsz.prop.BulletProp;

/**
 * BloodPropFactory
 *
 * @author hhr
 */
public class BulletPropFactory implements PropFactory {

    @Override
    public BulletProp createProp(int x, int y, int speedY) {
        return new BulletProp(x, y, 0, speedY);
    }
}
