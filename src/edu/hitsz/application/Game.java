package edu.hitsz.application;

import edu.hitsz.DAO.ScoreDao;
import edu.hitsz.DAO.ScoreDaoImpl;
import edu.hitsz.DAO.ScoreInfo;
import edu.hitsz.aircraft.*;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.factory.BossEnemyFactory;
import edu.hitsz.factory.EliteEnemyFactory;
import edu.hitsz.factory.EnemyFactory;
import edu.hitsz.factory.MobEnemyFactory;
import edu.hitsz.prop.AbstractProp;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;
import java.util.Random;

/**
 * 游戏主面板，游戏启动
 *
 * @author hitsz
 */
public abstract class Game extends JPanel {

    private int backGroundTop = 0;

    /**
     * Scheduled 线程池，用于任务调度
     */
    private final ScheduledExecutorService executorService;

    /**
     * 时间间隔(ms)，控制刷新频率
     */
    private final int timeInterval = 40;

    private final HeroAircraft heroAircraft;
    private final List<AbstractAircraft> enemyAircrafts;
    private final List<BaseBullet> heroBullets;
    private final List<BaseBullet> enemyBullets;
    private final List<AbstractProp> props;
    private MusicThread backGroundMusic;
    private MusicThread bossMusic;
    /**
     * 【数据访问对象模式】数据对象
     */
    private ScoreDao scoreDao = new ScoreDaoImpl();

    /**
     * 【观察者模式】发布者
     */
    private Publisher publisher = new Publisher();

    /**
     * 【工厂模式】敌机工厂
     */
    private EnemyFactory enemyFactory;

    /**
     * 屏幕中出现的敌机最大数量
     */
    private final int enemyMaxNumber = 5;

    /**
     * 当前得分
     */
    private int score = 0;
    /**
     * 当前时刻
     */
    private int time = 0;

    /**
     * 周期（ms)
     * 指示子弹的发射、敌机的产生频率
     */
    private final int cycleDuration = 600;
    private int cycleTime = 0;

    /**
     * 产生Boss机的分数阈值
     */
    private final int bossScore = 300;

    /**
     * 游戏结束标志0
     */
    private boolean gameOverFlag = false;

    private boolean addBoss = false;

    public Game() {
        // 创建英雄机
        heroAircraft = HeroAircraft.getHeroAircraft();

        enemyAircrafts = new LinkedList<>();
        heroBullets = new LinkedList<>();
        enemyBullets = new LinkedList<>();
        props = new LinkedList<>();

        /**
         * Scheduled 线程池，用于定时任务调度
         * 关于alibaba code guide：可命名的 ThreadFactory 一般需要第三方包
         * apache 第三方库： org.apache.commons.lang3.concurrent.BasicThreadFactory
         */
        this.executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("game-action-%d").daemon(true).build());

        //启动英雄机鼠标监听
        new HeroController(this, heroAircraft);

    }

    /**
     * 游戏启动入口，执行游戏逻辑
     */
    public void action() {
        // 背景音乐
        backGroundMusic = new MusicThread("src/videos/bgm.wav");
        backGroundMusic.start();
        backGroundMusic.setLoop(true);

        // 定时任务：绘制、对象产生、碰撞判定、击毁及结束判定
        Runnable task = () -> {

            time += timeInterval;

            // 周期性执行（控制频率）
            if (timeCountAndNewCycleJudge()) {
                System.out.println(time);

                // 产生随机数，用判断生成普通敌机还是精英敌机
                Random randEnemy = new Random();

                /**
                 * 新敌机产生
                 */
                if (addBoss) {
                    // 产生Boss机
                    backGroundMusic.setLoop(false);
                    backGroundMusic.stopMusic();
                    bossMusic = new MusicThread("src/videos/bgm_boss.wav");
                    bossMusic.start();
                    bossMusic.setLoop(true);

                    enemyFactory = new BossEnemyFactory();
                    AbstractAircraft newEnemy = enemyFactory.createEnemy();
                    enemyAircrafts.add(newEnemy);
                    publisher.addEnemy((Enemy) newEnemy);
                    addBoss = false;
                } else if (randEnemy.nextInt(100) < 15) {
                    // 产生精英敌机
                    if (enemyAircrafts.size() < enemyMaxNumber) {
                        enemyFactory = new EliteEnemyFactory();
                        AbstractAircraft newEnemy = enemyFactory.createEnemy();
                        enemyAircrafts.add(newEnemy);
                        publisher.addEnemy((Enemy) newEnemy);
                    }
                } else {
                    // 产生普通敌机
                    if (enemyAircrafts.size() < enemyMaxNumber) {
                        enemyFactory = new MobEnemyFactory();
                        AbstractAircraft newEnemy = enemyFactory.createEnemy();
                        enemyAircrafts.add(newEnemy);
                        publisher.addEnemy((Enemy) newEnemy);
                    }
                }

                // 飞机射出子弹
                shootAction();
            }

            // 子弹移动
            bulletsMoveAction();

            // 飞机移动
            aircraftsMoveAction();

            // 道具移动
            propMoveAction();

            // 撞击检测
            crashCheckAction();

            // 后处理
            postProcessAction();

            //每个时刻重绘界面
            repaint();

            // 游戏结束检查英雄机是否存活
            if (heroAircraft.getHp() <= 0) {
                // 游戏结束
                System.out.println("Game Over!\n");
                if (backGroundMusic != null) {
                    backGroundMusic.stopMusic();
                }
                if (bossMusic != null) {
                    bossMusic.stopMusic();
                }
                MusicThread m = new MusicThread("src/videos/game_over.wav");
                m.start();

                // 以设定格式获取当前时间
                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm");

                // 产生本次成绩
                ScoreInfo scoreInfo = new ScoreInfo();
                scoreInfo.setScore(score);
                scoreInfo.setDate(dateFormat.format(date));
                System.out.println("Your score: " + score);

                // 成绩处理
                AskName askName = new AskName(scoreInfo, scoreDao, getScoreFile(), this);
                Main.cardPanel.add(askName.getMainPanel());
                Main.frame.setSize(200, 200);
                Main.cardLayout.last(Main.cardPanel);

                executorService.shutdown();
                gameOverFlag = true;
            }
        };

        /**
         * 以固定延迟时间进行执行
         * 本次任务执行完成后，需要延迟设定的延迟时间，才会执行新的任务
         */
        executorService.scheduleWithFixedDelay(task, timeInterval, timeInterval, TimeUnit.MILLISECONDS);

    }

    //***********************
    //      Action 各部分
    //***********************

    private boolean timeCountAndNewCycleJudge() {
        cycleTime += timeInterval;
        if (cycleTime >= cycleDuration && cycleTime - timeInterval < cycleTime) {
            // 跨越到新的周期
            cycleTime %= cycleDuration;
            return true;
        } else {
            return false;
        }
    }

    private void shootAction() {
        // 敌机射击
        for (AbstractAircraft enemyAircraft : enemyAircrafts) {
            enemyBullets.addAll(enemyAircraft.shoot());
        }
        // 英雄射击
        heroBullets.addAll(heroAircraft.shoot());
    }

    private void bulletsMoveAction() {
        for (BaseBullet bullet : heroBullets) {
            bullet.forward();
        }
        for (BaseBullet bullet : enemyBullets) {
            bullet.forward();
        }
    }

    private void aircraftsMoveAction() {
        for (AbstractAircraft enemyAircraft : enemyAircrafts) {
            enemyAircraft.forward();
        }
    }

    private void propMoveAction() {
        for (AbstractProp prop : props) {
            prop.forward();
        }
    }

    /**
     * @return scoreFile 分数文件的地址
     */
    protected abstract File getScoreFile();

    private void outCheckAction() {
        for (AbstractAircraft enemyAircraft : enemyAircrafts) {
            if (enemyAircraft.getLocationY() >= Main.WINDOW_HEIGHT) {
                publisher.removeEnemy((Enemy) enemyAircraft);
                enemyAircraft.vanish();
            }
        }
    }

    /**
     * 碰撞检测：
     * 1. 敌机攻击英雄
     * 2. 英雄攻击/撞击敌机
     * 3. 英雄获得补给
     */
    private void crashCheckAction() {
        // 敌机子弹攻击英雄机
        for (BaseBullet bullet : enemyBullets) {
            if (bullet.notValid()) {
                continue;
            }
            if (heroAircraft.notValid()) {
                // 避免多个子弹重复击毁的判定
                continue;
            }
            if (heroAircraft.crash(bullet)) {
                // 英雄机撞击到敌机子弹
                // 英雄机损失一定生命值
                heroAircraft.decreaseHp(bullet.getPower());
                bullet.vanish();
            }
        }

        // 英雄子弹攻击敌机
        for (BaseBullet bullet : heroBullets) {
            if (bullet.notValid()) {
                continue;
            }
            for (AbstractAircraft enemyAircraft : enemyAircrafts) {
                if (enemyAircraft.notValid()) {
                    // 已被其他子弹击毁的敌机，不再检测，避免多个子弹重复击毁同一敌机的判定
                    continue;
                }

                if (enemyAircraft.crash(bullet)) {
                    // 播放音乐
                    MusicThread m = new MusicThread("src/videos/bullet_hit.wav");
                    m.start();

                    // 敌机撞击到英雄机子弹，敌机损失一定生命值
                    enemyAircraft.decreaseHp(bullet.getPower());
                    bullet.vanish();

                    enemyCheckDeath(enemyAircraft);
                }
                // 英雄机 与 敌机 相撞，均损毁
                if (enemyAircraft.crash(heroAircraft) || heroAircraft.crash(enemyAircraft)) {
                    enemyAircraft.vanish();
                    heroAircraft.decreaseHp(Integer.MAX_VALUE);
                }
            }
        }

        // 我方获得道具，道具生效
        for (AbstractProp prop : props) {
            if (prop.notValid()) {
                continue;
            }
            if (heroAircraft.crash(prop)) {
                prop.active(heroAircraft, publisher);
                prop.vanish();
            }
        }
    }

    public void enemyCheckDeath(AbstractAircraft enemyAircraft) {
        if (enemyAircraft.notValid()) {
            // 获得分数，获得道具补给
            score += 10;
            if (score % bossScore == 0) {
                addBoss = true;
            }
            if (enemyAircraft instanceof BossEnemy) {
                bossMusic.setLoop(false);
                bossMusic.stopMusic();
                backGroundMusic = new MusicThread("src/videos/bgm.wav");
                backGroundMusic.start();
                backGroundMusic.setLoop(true);
                props.addAll(((BossEnemy) enemyAircraft).dropProp());
            }
            if (enemyAircraft instanceof EliteEnemy) {
                props.addAll(((EliteEnemy) enemyAircraft).dropProp());
            }
            publisher.removeEnemy((Enemy) enemyAircraft);
        }
    }


    /**
     * 后处理：
     * 1. 删除无效的子弹
     * 2. 删除无效的敌机
     * <p>
     * 无效的原因可能是撞击或者飞出边界
     */
    private void postProcessAction() {
        enemyBullets.removeIf(AbstractFlyingObject::notValid);
        heroBullets.removeIf(AbstractFlyingObject::notValid);
        enemyAircrafts.removeIf(AbstractFlyingObject::notValid);
        props.removeIf(AbstractFlyingObject::notValid);
    }


    //***********************
    //      Paint 各部分
    //***********************

    protected abstract Image getBackGround();

    /**
     * 重写paint方法
     * 通过重复调用paint方法，实现游戏动画
     *
     * @param g 图片
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // 绘制背景,图片滚动
        g.drawImage(getBackGround(), 0, this.backGroundTop - Main.WINDOW_HEIGHT, null);
        g.drawImage(getBackGround(), 0, this.backGroundTop, null);
        this.backGroundTop += 1;
        if (this.backGroundTop == Main.WINDOW_HEIGHT) {
            this.backGroundTop = 0;
        }

        // 先绘制子弹，后绘制飞机
        // 这样子弹显示在飞机的下层
        paintImageWithPositionRevised(g, props);
        paintImageWithPositionRevised(g, enemyBullets);
        paintImageWithPositionRevised(g, heroBullets);

        paintImageWithPositionRevised(g, enemyAircrafts);

        g.drawImage(ImageManager.HERO_IMAGE, heroAircraft.getLocationX() - ImageManager.HERO_IMAGE.getWidth() / 2,
                heroAircraft.getLocationY() - ImageManager.HERO_IMAGE.getHeight() / 2, null);

        //绘制得分和生命值
        paintScoreAndLife(g);

    }

    private void paintImageWithPositionRevised(Graphics g, List<? extends AbstractFlyingObject> objects) {
        if (objects.size() == 0) {
            return;
        }

        for (AbstractFlyingObject object : objects) {
            BufferedImage image = object.getImage();
            assert image != null : objects.getClass().getName() + " has no image! ";
            g.drawImage(image, object.getLocationX() - image.getWidth() / 2,
                    object.getLocationY() - image.getHeight() / 2, null);
        }
    }

    private void paintScoreAndLife(Graphics g) {
        int x = 10;
        int y = 25;
        g.setColor(new Color(16711680));
        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        g.drawString("SCORE:" + this.score, x, y);
        y = y + 20;
        g.drawString("LIFE:" + this.heroAircraft.getHp(), x, y);
    }

}
