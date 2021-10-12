package ru.ekuchin.patterns.factory;

public class Ship extends Transport{
    public Ship(String name, int cost, int speed) {
        super(name, cost, speed);
    }

    @Override
    public String getCalculation(City city) {
        return String.format(
                "%s поплывет в город %s и перевезет груз за %d часов за %d р.",
                getName(),
                city.getName(),
                getFinalPrice(city.getDistance()),
                getArriveTime(city.getDistance()));
    }
}
