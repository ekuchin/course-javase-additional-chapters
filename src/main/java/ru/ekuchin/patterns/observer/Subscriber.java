package ru.ekuchin.patterns.observer;

public class Subscriber implements Receiver{
    private String name;
    @Override
    public void sendNotification(Sender sender) {
        System.out.println(String.format(
                "Я, %s, получил оповещение %s об %s, обдумываю и выполняю.",
                getName(),
                sender.getName(),
                sender.getData()

        ));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Subscriber(String name) {
        this.name = name;
    }
}