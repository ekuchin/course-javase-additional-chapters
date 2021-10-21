package ru.ekuchin;

import ru.ekuchin.streamapi.Cat;

public class App
{
    public static void main( String[] args ){
        System.out.println( "Hello Java!" );

        //Фабрика
        //Starter.demoPatternsFactory();

        //Строитель
        //Starter.demoPatternsBuilder();

        //Заместитель(Proxy)
        //Starter.demoPatternsProxy();

        //Наблюдатель (Observer)
        //Starter.demoPatternsObserver();

        //Анализ файлов
        //Starter.demoFiles();

        //Лямбда-функции
        //Starter.demoLambda();

        //Stream API
        //Starter.demoStreamAPI();

        //Javadoc
        //Starter.demoJavadoc();

        //Стандартные пакеты и классы
        Starter.demoStandardPackages();
    }
}
