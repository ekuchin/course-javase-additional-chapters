package ru.ekuchin;

import ru.ekuchin.files.Analyzer;
import ru.ekuchin.lambda.Arithmetic;
import ru.ekuchin.lambda.Calculator;
import ru.ekuchin.patterns.builder.Tree;
import ru.ekuchin.patterns.builder.TreeBuilder;
import ru.ekuchin.patterns.factory.City;
import ru.ekuchin.patterns.factory.Transport;
import ru.ekuchin.patterns.factory.TransportFactory;
import ru.ekuchin.patterns.observer.Publisher;
import ru.ekuchin.patterns.observer.Subscriber;
import ru.ekuchin.patterns.proxy.CachedDataSource;
import ru.ekuchin.streamapi.Cat;
import ru.ekuchin.streamapi.CatCollection;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.stream.Stream;

public class Starter {
    public static void demoPatternsFactory(){
        City city1 = new City("Tokyo", 6100);
        City city2 = new City("Paris", 4600);

        Transport toTokyo = TransportFactory.getTransport(city1);
        Transport toParis = TransportFactory.getTransport(city2);

        System.out.println(toTokyo.getCalculation(city1));
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
    public static void demoFiles(){
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try(InputStream input = classloader.getResourceAsStream("app.properties")){
        //try (InputStream input = new FileInputStream("app.properties")) {

            Properties props = new Properties();
            props.load(new InputStreamReader(input, StandardCharsets.UTF_8));
            Analyzer analyzer = new Analyzer(props);

            File file = new File(props.getProperty("path"));
            String report = analyzer.analyze(file, 0);
            analyzer.writeLog(report,file.getName()+".html");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void demoLambda(){
        Arithmetic summ = (x, y)->x+y;
        System.out.println( summ.getResult(10,20) );

        Arithmetic division = (x, y)->{
            return y == 0 ? 0 :x/y;
        };
        System.out.println( division.getResult(50,10) );

        //получим лямбду из метода
        Arithmetic action = Calculator.getAction(1);
        //отправим ее в другой метод
        System.out.println(Calculator.doAction(30,40, action));
        System.out.println(Calculator.doAction(30,40, action));
    }
    public static void demoStreamAPI(){

        //for (Cat cat: CatCollection.getAsArray()){
        //    System.out.println(cat);
        //}

        CatCollection.getAsList().stream().forEach(System.out::println);
        Arrays.stream(CatCollection.getAsArray()).forEach(System.out::println);
        Stream.of(CatCollection.getAsArray()).forEach(System.out::println);
    }
}
