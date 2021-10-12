package ru.ekuchin.patterns.observer;

import java.util.ArrayList;

public class Publisher implements Sender{
    private ArrayList<Receiver> subscribers;
    private String data;
    private String name;

    @Override
    public void addSubscriber(Receiver receiver) {
        subscribers.add(receiver);
    }

    @Override
    public void removeSubscriber(Receiver receiver) {
        subscribers.remove(receiver);
    }

    @Override
    public void notifySubscribers() {
        for (Receiver sub:subscribers) {
            sub.sendNotification(this);
        }
    }

    public Publisher(String name, String data) {
        this.data = data;
        this.name = name;
        subscribers = new ArrayList<Receiver>();
    }

    @Override
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
        notifySubscribers();
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
