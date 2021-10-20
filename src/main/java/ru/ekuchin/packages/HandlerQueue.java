package ru.ekuchin.packages;

import java.util.ArrayList;

public class HandlerQueue<T> extends AbstractHandler<T>{

    public HandlerQueue() {
        super();
    }

    @Override
    public void receive(T t) {
        list.add(t);
    }

    @Override
    public void handle() {
        list.remove(0);
    }

    @Override
    public String toString() {
        return "HandlerQueue{" +
                "list=" + list +
                '}';
    }
}
