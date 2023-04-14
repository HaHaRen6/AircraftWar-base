package edu.hitsz.application;


import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.aircraft.MobEnemy;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;
import edu.hitsz.prop.BloodProp;
import edu.hitsz.prop.BombProp;
import edu.hitsz.prop.BulletProp;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 综合管理图片的加载，访问
 * 提供图片的静态访问方法
 *
 * @author hitsz
 */
public class ImageManager {

    /**
     * 类名-图片 映射，存储各基类的图片 <br>
     * 可使用 CLASSNAME_IMAGE_MAP.get( obj.getClass().getName() ) 获得 obj 所属基类对应的图片
     */
    private static final Map<String, BufferedImage> CLASSNAME_IMAGE_MAP = new HashMap<>();

    public static BufferedImage BACKGROUND_IMAGE1;
    public static BufferedImage BACKGROUND_IMAGE2;
    public static BufferedImage BACKGROUND_IMAGE3;
    public static BufferedImage HERO_IMAGE;
    public static BufferedImage HERO_BULLET_IMAGE;
    public static BufferedImage ENEMY_BULLET_IMAGE;
    public static BufferedImage MOB_ENEMY_IMAGE;
    public static BufferedImage ELITE_ENEMY_IMAGE;
    public static BufferedImage BOSS_ENEMY_IMAGE;
    public static BufferedImage PROP_BLOOD_IMAGE;
    public static BufferedImage PROP_BOMB_IMAGE;
    public static BufferedImage PROP_BULLET_IMAGE;

    static {
        try {

            BACKGROUND_IMAGE1 = ImageIO.read(new FileInputStream("src/images/bg.jpg"));
            BACKGROUND_IMAGE2 = ImageIO.read(new FileInputStream("src/images/bg2.jpg"));
            BACKGROUND_IMAGE3 = ImageIO.read(new FileInputStream("src/images/bg3.jpg"));

            HERO_IMAGE = ImageIO.read(new FileInputStream("src/images/hero.png"));
            MOB_ENEMY_IMAGE = ImageIO.read(new FileInputStream("src/images/mob.png"));
            HERO_BULLET_IMAGE = ImageIO.read(new FileInputStream("src/images/bullet_hero.png"));
            ELITE_ENEMY_IMAGE = ImageIO.read(new FileInputStream("src/images/elite.png"));
            BOSS_ENEMY_IMAGE = ImageIO.read(new FileInputStream("src/images/boss.png"));
            ENEMY_BULLET_IMAGE = ImageIO.read(new FileInputStream("src/images/bullet_enemy.png"));
            PROP_BLOOD_IMAGE = ImageIO.read(new FileInputStream("src/images/prop_blood.png"));
            PROP_BOMB_IMAGE = ImageIO.read(new FileInputStream("src/images/prop_bomb.png"));
            PROP_BULLET_IMAGE = ImageIO.read(new FileInputStream("src/images/prop_bullet.png"));

            CLASSNAME_IMAGE_MAP.put(HeroAircraft.class.getName(), HERO_IMAGE);
            CLASSNAME_IMAGE_MAP.put(MobEnemy.class.getName(), MOB_ENEMY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(BossEnemy.class.getName(), BOSS_ENEMY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(EliteEnemy.class.getName(), ELITE_ENEMY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(HeroBullet.class.getName(), HERO_BULLET_IMAGE);
            CLASSNAME_IMAGE_MAP.put(EnemyBullet.class.getName(), ENEMY_BULLET_IMAGE);
            CLASSNAME_IMAGE_MAP.put(BloodProp.class.getName(), PROP_BLOOD_IMAGE);
            CLASSNAME_IMAGE_MAP.put(BombProp.class.getName(), PROP_BOMB_IMAGE);
            CLASSNAME_IMAGE_MAP.put(BulletProp.class.getName(), PROP_BULLET_IMAGE);

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static BufferedImage get(String className) {
        return CLASSNAME_IMAGE_MAP.get(className);
    }

    public static BufferedImage get(Object obj) {
        if (obj == null) {
            return null;
        }
        return get(obj.getClass().getName());
    }

}
