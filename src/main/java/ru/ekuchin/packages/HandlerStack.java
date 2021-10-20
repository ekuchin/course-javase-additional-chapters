package ru.ekuchin.packages;

import java.util.ArrayList;

public class HandlerStack<T> extends AbstractHandler<T>{

    public HandlerStack() {
        super();
    }

    @Override
    public void receive(T t) {
        list.add(t);
    }

    @Override
    public void handle() {
        list.remove(list.size()-1);
    }

    @Override
    public String toString() {
        return "HandlerStack{" +
                "list=" + list +
                '}';
    }
}