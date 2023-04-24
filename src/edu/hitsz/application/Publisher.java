package edu.hitsz.application;

import edu.hitsz.aircraft.Enemy;

import java.util.ArrayList;
import java.util.List;

public class Publisher {
    private List<Enemy> enemyList = new ArrayList<>();

    public void addEnemy(Enemy enemy){
        enemyList.add(enemy);
    }

    public void removeEnemy(Enemy enemy){
        enemyList.remove(enemy);
    }

    public void notifyAllEnemies(){
        for (Enemy enemy:enemyList){
            enemy.update();
        }
    }

    public void bombActive(){
        notifyAllEnemies();
    }
}
