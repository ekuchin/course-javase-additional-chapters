package ru.ekuchin.streamapi;

import java.util.ArrayList;

public class CatCollection {
    public static ArrayList<Cat> getAsList(){
        ArrayList<Cat> result = new ArrayList<>();
        result.add(new Cat("Мурзик", "Манул", 10, true));
        result.add(new Cat("Рамзес", "Сфинкс", 2, true));
        result.add(new Cat("Эдуард", "Британец", 5, false));
        result.add(new Cat("Мурка", "Беспородная", 4, false));
        result.add(new Cat("Барсик", "Манул", 6, false));
        return result;
    }

    public static Cat[] getAsArray(){
        Cat[] cats = new Cat[getAsList().size()];
        return getAsList().toArray(cats);
    }

}
