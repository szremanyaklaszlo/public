package com.training.restaurant.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable<E> {

    private List<Observer<E>> observers = new ArrayList<Observer<E>>();

    public Observable() {
        super();
    }

    public boolean addObserver(Observer<E> e) {
        return observers.add(e);
    }

    public void notifyObservers(E e) {
        for (Observer observer : observers) {
            observer.update(e);
        }
    }
}
