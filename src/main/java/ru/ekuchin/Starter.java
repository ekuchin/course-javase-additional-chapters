package ru.ekuchin;

import ru.ekuchin.files.Analyzer;
import ru.ekuchin.filetypes.json.JacksonCat;
import ru.ekuchin.filetypes.json.JsonPCat;
import ru.ekuchin.filetypes.json.JsonCatBuilder;
import ru.ekuchin.filetypes.xml.JaxbCatCollection;
import ru.ekuchin.filetypes.xml.XmlCat;
import ru.ekuchin.filetypes.xml.XmlCatBuilder;
import ru.ekuchin.javadoc.DocumendedCat;
import ru.ekuchin.lambda.Arithmetic;
import ru.ekuchin.lambda.Calculator;
import ru.ekuchin.multithreading.MyThread;
import ru.ekuchin.packages.HandlerQueue;
import ru.ekuchin.packages.HandlerStack;
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
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
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
        builder.addNode(new ArrayList<>());
        builder.addNode(new ArrayList<>(Arrays.asList(4, 5)));
        builder.addNode(new ArrayList<>());
        builder.addNode(new ArrayList<>());
        builder.addNode(new ArrayList<>());
        builder.linkNodes(1,4);
        builder.linkNodes(3,5);
        Tree tree = builder.serialize();
        System.out.println(tree.toString());
    }
    public static void demoPatternsProxy() throws Exception {
        CachedDataSource source = new CachedDataSource("???????????? ?? ??????????????");
        //?????????? ???????????????? ??????
        System.out.println(source.getData());
        source.setData("???????????? ?? ????????????????");
        //?????????? ????????????????
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

        //?????????????? ???????????? ???? ????????????
        Arithmetic action = Calculator.getAction(1);
        //???????????????? ???? ?? ???????????? ??????????
        System.out.println(Calculator.doAction(30,40, action));
        System.out.println(Calculator.doAction(30,40, action));
    }
    public static void demoStreamAPI(){

        /*
        for (Cat cat: CatCollection.getAsArray()){
            System.out.println(cat);
        }
        */

        Stream<Cat> streamFromList =  CatCollection.getAsList().stream();
        Stream<Cat> streamFromArray= Arrays.stream(CatCollection.getAsArray());
        Stream<Cat> streamFromStatic = Stream.of(CatCollection.getAsArray());

        Stream<Cat> streamSum = Stream.concat(streamFromList,Stream.concat(streamFromArray,streamFromStatic));

        ArrayList<Cat> cats = CatCollection.getAsList();
        ArrayList<Cat> emptyList = new ArrayList<>();

        //?????????? Collectors
        ArrayList<Cat> arrayListFromStream = cats.stream().collect(Collectors.toCollection(ArrayList::new));

        List<Cat> listFromStream = cats.stream().collect(Collectors.toList());
        Set<Cat> setFromStream = cats.stream().collect(Collectors.toSet());
        Map<String, Integer> mapFromStream = cats.stream().collect(Collectors.toMap(Cat::getName, Cat::getWeight));

        System.out.println("??????????????????????");
        Map<Boolean, List<Cat>> catMap = cats.stream().collect(Collectors.groupingBy(c->c.isAngry()));
        for(Map.Entry<Boolean, List<Cat>> item : catMap.entrySet()){
            System.out.println(item.getKey());
            for(Cat cat : item.getValue()){
                System.out.println(cat.getName());
            }
        }

        //?????????????????????????? ??????????????????
        System.out.println("distinct");
        cats.stream().distinct().forEach(System.out::println);

        System.out.println("skip");
        cats.stream().skip(1).forEach(System.out::println);

        System.out.println("limit");
        cats.stream().limit(1).forEach(System.out::println);

        System.out.println("takeWhile");
        cats.stream().takeWhile(Cat::isAngry).forEach(System.out::println);

        System.out.println("dropWhile");
        cats.stream().dropWhile(Cat::isAngry).forEach(System.out::println);

        System.out.println("filter");
        cats.stream().filter(c->c.getWeight()>5).forEach(System.out::println);

        System.out.println("map");
        cats.stream().map(Cat::getName).forEach(System.out::println);
        System.out.println("flatMap");
        cats.stream().flatMap(p->Stream.of(p.getName(),p.getBreed())).forEach(System.out::println);

        System.out.println("sorted");
        cats.stream().sorted((c,p)->c.getName().compareTo(p.getName())).forEach(System.out::println);
        cats.stream().sorted((c,p)->c.getWeight()-p.getWeight()).forEach(System.out::println);

        //???????????????????????? ??????????????????
        cats.stream().forEach(System.out::println);
        System.out.printf("?????????? ?? ?????? %d ??????????%n",cats.stream().count());

        System.out.println(cats.stream().allMatch(Cat::isAngry));
        System.out.println(cats.stream().anyMatch(c->c.getWeight()>5));
        System.out.println(cats.stream().noneMatch(c->c.getBreed().equals("??????????????????")));

        //?????????? Optional
        System.out.println("optional");

        System.out.println(emptyList.stream().findFirst().isPresent());
        System.out.println(emptyList.stream().findFirst().orElse(new Cat("????????????", "????????????????????", 4, false)));
        //System.out.println(emptyList.stream().findFirst().orElseThrow(Exception::new));
        emptyList.stream().findAny().ifPresent(System.out::println);
        emptyList.stream().findAny().ifPresentOrElse(System.out::println, ()->System.out.println("?????? ??????????"));

        //???????????????????????? ??????????????????, ???????????????????????? Optional
        System.out.println(cats.stream().min((c,p)->c.getWeight()-p.getWeight()));
        System.out.println(cats.stream().max((c,p)->c.getWeight()-p.getWeight()));

        cats.stream().min((c,p)->c.getWeight()-p.getWeight()).ifPresent(System.out::println);

        System.out.println("reduce");
        cats.stream().map(Cat::getWeight).reduce(Integer::sum).ifPresent(System.out::println);
    }
    public static void demoJavadoc(){
        DocumendedCat cat = new DocumendedCat("????????????", "??????????", 10,true);
        cat.sleep(1);
        cat.sleep(2);
    }
    public static void demoStandardPackages(){
        //Regexp
        // 10 ?????? 12 ????????
        String regexp = "\\d{10}|\\d{12}";
        System.out.println("123456789101".matches(regexp));
        System.out.println("1234567890".matches(regexp));

        //??????????, ?????????? ?? ??????????
        regexp = "[.A-Za-z??-????-??0-9]*";
        System.out.println("meekuchin.ru".matches(regexp));
        System.out.println("me@ekuchin.ru".matches(regexp));

        //Email
        regexp = "[A-Za-z0-9]*@[A-Za-z0-9]*.[A-Za-z0-9]{2}|[A-Za-z0-9]*@[A-Za-z0-9]*.[A-Za-z0-9]{3}";
        System.out.println("me@ekuchin.ru".matches(regexp));

        //??????????????????
        String[] arr = {"????????????","????????????","????????????","??????????????????","??????????","????????????"};
        HandlerQueue<String> queue = new HandlerQueue<>();
        for(int i=0;i<6;i++){
            queue.receive(arr[i]);
            if(i%2!=0){
                queue.handle();
            }
        }
        System.out.println(queue.toString());

        HandlerStack<String> stack = new HandlerStack<>();
        for(int i=0;i<6;i++){
            stack.receive(arr[i]);
            if(i%2!=0){
                stack.handle();
            }
        }
        System.out.println(stack.toString());

        //????????-??????????
        System.out.println("????????-??????????");
        LocalDate today = LocalDate.now();
        LocalDate myBirthday = LocalDate.of(1978, Month.NOVEMBER, 14);
        long myAge = ChronoUnit.DAYS.between(myBirthday, today);
        System.out.println(myAge);

        long nextThousand = ((myAge / 1000)+1)*1000;
        System.out.println(myBirthday.plus(nextThousand, ChronoUnit.DAYS));

        //Runtime & Reflection
        Runtime runtime = Runtime.getRuntime();
        System.out.println(runtime.availableProcessors());
        System.out.println(runtime.freeMemory());
        System.out.println(runtime.maxMemory());

        Cat cat = new Cat("????????????", "??????????",10, true);
        Class catClass = cat.getClass();
        for(Method method:catClass.getMethods()){
            System.out.println(method.toString());
        }
        System.out.println("Fields");
        for(Field field:catClass.getDeclaredFields()){
            System.out.println(field.toString());
        }
    }
    public static void demoFileTypesXML() throws Exception {
        XmlCat[] cats = XmlCatBuilder.readXML("src/main/resources/cats.xml");
        Arrays.stream(cats).forEach(System.out::println);

        XmlCatBuilder.writeXml(cats, "src/main/resources/newcat.xml");
        XmlCatBuilder.transformXml("src/main/resources/cats.xml",
                "src/main/resources/cats.xsl",
                "src/main/resources/cats.html");

        System.out.println(XmlCatBuilder.validateXml(
                "src/main/resources/cats.xml",
                "src/main/resources/cats.xsd"));

        //Jaxb
        System.out.println("Jaxb");
        JaxbCatCollection catsCollection = XmlCatBuilder.readJaxb("src/main/resources/cats.xml");
        catsCollection.getCats().forEach(System.out::println);

        XmlCatBuilder.writeJaxb(catsCollection,"src/main/resources/newcat.xml");
    }
    public static void demoFileTypesJSON() throws Exception {
        ArrayList<JsonPCat> cats = JsonCatBuilder.readJson("src/main/resources/cats.json");
        cats.forEach(System.out::println);

        JsonCatBuilder.writeJson(cats, "src/main/resources/newcat.json");

        System.out.println("Jackson");
        JacksonCat[] jacksonCats = JsonCatBuilder.readJackson("src/main/resources/cats.json");
        Arrays.stream(jacksonCats).forEach(System.out::println);
        JsonCatBuilder.writeJackson(jacksonCats, "src/main/resources/newcat.json");
    }
    public static void demoMultiThreading() throws InterruptedException {

        MyThread myThread = new MyThread();
        Thread second = new Thread(myThread);
        second.start();

        MyThread.doJob(1000);

        second.join();
        System.out.println("?????? ???????????? ??????????????????");
    }
}
