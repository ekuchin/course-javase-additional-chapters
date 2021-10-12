package ru.ekuchin.patterns.factory;

public class City {
    private String name;
    private int distance;

    public City(String name, int distance) {
        setName(name);
        setDistance(distance);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getDistance() {
        return distance;
    }

    private void setDistance(int distance) {
        this.distance = distance;
    }
}
