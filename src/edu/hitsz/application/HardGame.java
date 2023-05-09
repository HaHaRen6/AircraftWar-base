package edu.hitsz.application;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.factory.BossEnemyFactory;
import edu.hitsz.factory.EliteEnemyFactory;
import edu.hitsz.factory.EnemyFactory;

import java.awt.*;
import java.io.File;

/**
 * @author hhr
 */
public class HardGame extends Game {
    private int bossHp = 240;

    @Override
    protected Image getBackGround() {
        return ImageManager.BACKGROUND_IMAGE3;
    }

    @Override
    protected File getScoreFile() {
        return new File("score/HardGameScore.txt");
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
        newEnemy.setHp(bossHp);
        System.out.println("Boss机血量：" + bossHp);
        bossHp += 60;
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
        return 7;
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
        return (float) Math.max(0.1 + (float) time / 200000, 50.0);
    }

    @Override
    protected AbstractAircraft createEliteEnemy(Publisher publisher, int time){
        EnemyFactory enemyFactory = new EliteEnemyFactory();
        AbstractAircraft newEnemy = enemyFactory.createEnemy();
        newEnemy.setHp(90 + time / 1000);
        publisher.addSubscriber((Subscriber) newEnemy);
        return newEnemy;
    }
}
