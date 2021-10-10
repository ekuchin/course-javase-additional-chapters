package ru.ekuchin.patterns.factory_method;

public class Transport {

    private String name;
    private int cost;
    private int speed;

    public Transport(String name, int cost, int speed) {
        setName(name);
        setCost(cost);
        setSpeed(speed);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getFinalPrice(int distance){
        return getCost() * distance;
    }
    public int getArriveTime(int distance){
        return distance * getSpeed();
    }

    public String getCalculation(City city){
        return String.format(
                "%s поедет в город %s и перевезет груз за $d часов за %d р.",
                getName(),
                city.getName(),
                getArriveTime(city.getDistance()),
                getFinalPrice(city.getDistance())
                );
    }

}
