package edu.hitsz.application;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.factory.BossEnemyFactory;
import edu.hitsz.factory.EnemyFactory;

import java.awt.*;
import java.io.File;

public class EasyGame extends Game {
    @Override
    protected Image getBackGround() {
        return ImageManager.BACKGROUND_IMAGE1;
    }

    @Override
    protected File getScoreFile() {
        return new File("score/EasyGameScore.txt");
    }

    @Override
    protected AbstractAircraft createBoss(MusicThread backGroundMusic, MusicThread bossMusic, Publisher publisher) {
        return null;
    }
}
