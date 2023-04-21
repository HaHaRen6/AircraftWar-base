package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.MusicThread;

/**
 * 火力道具
 *
 * @Author hhr
 */
public class BombProp extends AbstractProp {

    public BombProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void active(HeroAircraft heroAircraft) {
        MusicThread m = new MusicThread("src/videos/bomb_explosion.wav");
        m.start();
        System.out.println("BombSupply active!");
    }
}
