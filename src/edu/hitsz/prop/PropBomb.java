package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;

/**
 * 火力道具
 *
 * @Author hhr
 */
public class PropBomb extends AbstractProp {

    public PropBomb(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void active(HeroAircraft heroAircraft) {
        System.out.println("BombSupply active!");
    }
}
