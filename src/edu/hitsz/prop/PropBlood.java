package edu.hitsz.prop;

/**
 * 回血道具
 *
 * @Author hhr
 */
public class PropBlood extends Prop {

    private int Hp_increase = 100;

    public PropBlood(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public int getIncreaseHp() {
        return Hp_increase;
    }

}
