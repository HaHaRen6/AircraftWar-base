package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;

/**
 * 回血道具
 *
 * @Author hhr
 */
public class BloodProp extends AbstractProp {

    private int hpIncrease = 100;

    public BloodProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void active(HeroAircraft heroAircraft) {
        heroAircraft.increaseHp(hpIncrease);
    }

}
