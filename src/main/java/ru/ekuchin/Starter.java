package ru.ekuchin;

import ru.ekuchin.patterns.builder.Tree;
import ru.ekuchin.patterns.builder.TreeBuilder;
import ru.ekuchin.patterns.factory.City;
import ru.ekuchin.patterns.factory.Transport;
import ru.ekuchin.patterns.factory.TransportFactory;
import ru.ekuchin.patterns.observer.Publisher;
import ru.ekuchin.patterns.observer.Subscriber;
import ru.ekuchin.patterns.proxy.CachedDataSource;

import java.util.ArrayList;
import java.util.Arrays;

public class Starter {
    public static void demoPatternsFactory(){
        City city1 = new City("Tokyo", 6100);
        City city2 = new City("Paris", 4600);

        Transport toTokio = TransportFactory.getTransport(city1);
        Transport toParis = TransportFactory.getTransport(city2);

        System.out.println(toTokio.getCalculation(city1));
        System.out.println(toParis.getCalculation(city2));
    }
    public static void demoPatternsBuilder(){
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
    public static void demoPatternsProxy() throws Exception {
        CachedDataSource source = new CachedDataSource("Данные о котиках");
        //Здесь задержки нет
        System.out.println(source.getData());
        source.setData("Данные о собачках");
        //Здесь задержка
        System.out.println(source.getData());
    }
    public static void demoPatternsObserver(){
        Publisher publisher = new Publisher("Publisher #1","Starting payload");
        Subscriber subscriber1 = new Subscriber("Subscriber #1");
        Subscriber subscriber2 = new Subscriber("Subscriber #2");

        publisher.addSubscriber(subscriber1);
        publisher.addSubscriber(subscriber2);
        publisher.setData("Changed payload");
    }

}
