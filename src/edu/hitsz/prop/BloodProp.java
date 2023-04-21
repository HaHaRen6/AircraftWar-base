package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.MusicThread;

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
        MusicThread m = new MusicThread("src/videos/get_supply.wav");
        m.start();
        heroAircraft.increaseHp(hpIncrease);
    }

}
