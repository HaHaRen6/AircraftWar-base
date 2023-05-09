package edu.hitsz.application;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.factory.BossEnemyFactory;
import edu.hitsz.factory.EliteEnemyFactory;
import edu.hitsz.factory.EnemyFactory;
import edu.hitsz.factory.MobEnemyFactory;

import java.awt.*;
import java.io.File;

/**
 * @author hhr
 */
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

    /**
     * 同时存在的最大敌机数量
     *
     * @return 最大敌机数量
     */
    @Override
    protected int maxEnemyNumber() {
        return 5;
    }

    /**
     * 产生精英敌机的概率
     * <p>
     *
     * @param time 随时间变化
     * @return 概率（小数）
     */
    @Override
    protected float eliteProbability(int time) {
        return (float) Math.min(0.1 + (float) time / 200000, 0.5);
    }

    @Override
    protected AbstractAircraft createEliteEnemy(Publisher publisher, int time) {
        EnemyFactory enemyFactory = new EliteEnemyFactory();
        AbstractAircraft newEnemy = enemyFactory.createEnemy();
        newEnemy.setHp(90 + time / 2000);
        publisher.addSubscriber((Subscriber) newEnemy);
        return newEnemy;
    }

    @Override
    protected AbstractAircraft createMobEnemy(Publisher publisher, int time) {
        EnemyFactory enemyFactory = new MobEnemyFactory();
        AbstractAircraft newEnemy = enemyFactory.createEnemy();
        newEnemy.setSpeedY(6 + time / 10000);
        publisher.addSubscriber((Subscriber) newEnemy);
        return newEnemy;
    }

    @Override
    protected int bossScore(){
        return 400;
    }
}
