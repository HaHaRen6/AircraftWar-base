package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.MusicThread;
import edu.hitsz.application.Publisher;
import edu.hitsz.shootStrategy.DirectShootStrategy;
import edu.hitsz.shootStrategy.ScatterShootStrategy;


/**
 * @author hhr
 */
public class BulletProp extends AbstractProp {
    private boolean flag = false;

    public BulletProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void active(HeroAircraft heroAircraft, Publisher publisher) {
        MusicThread m = new MusicThread("src/videos/get_supply.wav");
        m.start();
        Runnable r = () -> {
            try {
                for (int i = 0; i < 10; i++) {
                    heroAircraft.setShootStrategy(new ScatterShootStrategy());
                    Thread.sleep(500);
                }
                heroAircraft.setShootStrategy(new DirectShootStrategy());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        };
        new Thread(r, "BulletPropThread").start();

    }

}
