package edu.hitsz.application;

import edu.hitsz.prop.AbstractProp;

import java.util.ArrayList;
import java.util.List;

/**
 * 【模板模式】
 *
 * @author hhr
 */
public class Publisher {
    public List<Subscriber> subscriberList = new ArrayList<>();

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
