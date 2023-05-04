package edu.hitsz.application;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.factory.BossEnemyFactory;
import edu.hitsz.factory.EnemyFactory;

import java.awt.*;
import java.io.File;

public class MediumGame extends Game {
    @Override
    protected Image getBackGround() {
        return ImageManager.BACKGROUND_IMAGE2;
    }

    @Override
    protected File getScoreFile() {
        return new File("score/MediumGameScore.txt");
    }

    @Override
    protected AbstractAircraft createBoss(MusicThread backGroundMusic, MusicThread bossMusic, Publisher publisher) {
        // 产生Boss机
        backGroundMusic.setLoop(false);
        backGroundMusic.stopMusic();
        bossMusic.start();
        bossMusic.setLoop(true);

        EnemyFactory enemyFactory = new BossEnemyFactory();
        AbstractAircraft newEnemy = enemyFactory.createEnemy();
        publisher.addSubscriber((Subscriber) newEnemy);

        return newEnemy;
    }
}
