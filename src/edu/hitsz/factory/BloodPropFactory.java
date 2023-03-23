package edu.hitsz.factory;

import edu.hitsz.prop.BloodProp;

/**
 * BloodPropFactory
 *
 * @author hhr
 */
public class BloodPropFactory implements PropFactory {

    @Override
    public BloodProp createProp(int x, int y, int speedY) {
        return new BloodProp(x, y, 0, speedY);
    }
}
