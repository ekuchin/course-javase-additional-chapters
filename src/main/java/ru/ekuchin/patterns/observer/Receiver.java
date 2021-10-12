package ru.ekuchin.patterns.observer;

public interface Receiver {
    void sendNotification(Sender sender);
}
