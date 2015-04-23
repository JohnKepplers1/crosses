package ru.ifmo.kravchenko;

import java.util.ArrayList;

public class Model implements ModelInterface {
    ArrayList beatObservers = new ArrayList();

    public void registerObserver(Observer o) {
        beatObservers.add(o);
    }

    public void notifyBeatObservers() {
        for (int i = 0; i < beatObservers.size(); i++) {
            Observer observer = (Observer) beatObservers.get(i);
            observer.update();
        }
    }
    public void removeObserver(Observer o){
        int i = beatObservers.indexOf(o);
        if (i>=0) beatObservers.remove(i);
    }

}
