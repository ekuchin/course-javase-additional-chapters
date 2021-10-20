package ru.ekuchin.packages;

import java.util.ArrayList;

abstract class AbstractHandler<T> {
    protected ArrayList<T> list;

    abstract void receive(T t);
    abstract void handle();

    public AbstractHandler() {
        this.list = new ArrayList<T>();
    }

    protected void add(int i, T t){
        list.add(i,t);
    }

    protected void remove(int i){
        list.remove(i);
    }
}
