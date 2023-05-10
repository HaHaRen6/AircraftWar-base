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
public class EasyGame extends Game {
    @Override
    protected void outputInformation(){
        System.out.println("你选择的是简单模式");
        System.out.println("无boss机，无精英机，难度不随时间变化");
        System.out.println("最大飞机数为 4 个");
    }

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

    @Override
    protected int maxEnemyNumber() {
        return 4;
    }

    @Override
    protected float eliteProbability(int time) {
        return 0;
    }

    @Override
    protected AbstractAircraft createEliteEnemy(Publisher publisher, int time) {
        // 简单模式无精英机
        return null;
    }

    @Override
    protected AbstractAircraft createMobEnemy(Publisher publisher, int time) {
        EnemyFactory enemyFactory = new MobEnemyFactory();
        AbstractAircraft newEnemy = enemyFactory.createEnemy();
        publisher.addSubscriber((Subscriber) newEnemy);
        return newEnemy;
    }

    @Override
    protected int bossScore() {
        // 简单模式无boss
        return 300;
    }
}
