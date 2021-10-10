package ru.ekuchin;

import ru.ekuchin.patterns.factory_method.Transport;
import ru.ekuchin.patterns.factory_method.City;
import ru.ekuchin.patterns.factory_method.TransportFactory;

public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Hello Java!" );

        //Фабричный метод
        City city1 = new City("Tokyo", 6100);
        City city2 = new City("Paris", 4600);

        Transport toTokio = TransportFactory.getTransport(city1);
        Transport toParis = TransportFactory.getTransport(city2);

        System.out.println(toTokio.getCalculation(city1));
        System.out.println(toParis.getCalculation(city2));


    }
}
