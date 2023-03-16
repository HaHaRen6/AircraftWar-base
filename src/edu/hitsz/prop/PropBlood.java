package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;

/**
 * 回血道具
 *
 * @Author hhr
 */
public class PropBlood extends Prop {

    private int hpIncrease = 100;

    public PropBlood(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void active(HeroAircraft heroAircraft) {
        heroAircraft.increaseHp(hpIncrease);
    }

}
