package ru.ekuchin;

import ru.ekuchin.patterns.builder.Tree;
import ru.ekuchin.patterns.builder.TreeBuilder;
import ru.ekuchin.patterns.factory_method.Transport;
import ru.ekuchin.patterns.factory_method.City;
import ru.ekuchin.patterns.factory_method.TransportFactory;

import java.util.ArrayList;
import java.util.Arrays;

public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Hello Java!" );

/*
        //Фабрика
        City city1 = new City("Tokyo", 6100);
        City city2 = new City("Paris", 4600);

        Transport toTokio = TransportFactory.getTransport(city1);
        Transport toParis = TransportFactory.getTransport(city2);

        System.out.println(toTokio.getCalculation(city1));
        System.out.println(toParis.getCalculation(city2));
*/

        //Строитель
        TreeBuilder builder = new TreeBuilder();
        builder.addNode(new ArrayList<>(Arrays.asList(1, 2, 3)));
        builder.addNode(new ArrayList<Integer>());
        builder.addNode(new ArrayList<>(Arrays.asList(4, 5)));
        builder.addNode(new ArrayList<Integer>());
        builder.addNode(new ArrayList<Integer>());
        builder.addNode(new ArrayList<Integer>());
        builder.linkNodes(1,4);
        builder.linkNodes(3,5);
        Tree tree = builder.serialize();
        System.out.println(tree.toString());
    }
}
