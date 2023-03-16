package edu.hitsz.prop;

public class PropBullet extends Prop {

    public PropBullet(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void active() {
        System.out.println("FireSupply active!");
    }
}
