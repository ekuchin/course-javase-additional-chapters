package ru.ekuchin.patterns.factory_method;

public class Plane extends Transport{
    public Plane(String name, int cost, int speed) {
        super(name, cost, speed);
    }

    @Override
    public String getCalculation(City city) {
        return String.format(
                "%s полетит в город %s и перевезет груз за %d часов за %d р.",
                getName(),
                city.getName(),
                getFinalPrice(city.getDistance()),
                getArriveTime(city.getDistance()));
    }
}
