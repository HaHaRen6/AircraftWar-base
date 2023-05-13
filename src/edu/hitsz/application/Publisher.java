package edu.hitsz.application;

import java.util.ArrayList;
import java.util.List;

/**
 * 【模板模式】
 *
 * @author hhr
 */
public class Publisher {
    private List<Subscriber> subscriberList = new ArrayList<>();

    public void addSubscriber(Subscriber subscriber) {
        subscriberList.add(subscriber);
    }

    public void removeSubscriber(Subscriber subscriber) {
        subscriberList.remove(subscriber);
    }

    public void notifyAllEnemies() {
        for (Subscriber subscriber : subscriberList) {
            subscriber.update();
        }
        subscriberList.clear();
    }

    public void bombActive() {
        notifyAllEnemies();
    }
}
