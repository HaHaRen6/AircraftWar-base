package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;

public class PropBullet extends AbstractProp {

    public PropBullet(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void active(HeroAircraft heroAircraft) {
        System.out.println("FireSupply active!");
    }
}
