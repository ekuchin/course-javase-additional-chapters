package ru.ekuchin.patterns.factory;

public class TransportFactory {

    public static Transport getTransport(City city){
        switch (city.getName()){
            case "Tokyo":
            case "New York":
                return new Plane("Боинг",200, 900);
            default:
                return new Ship("Баржа",50, 80);
        }

    }
}
