package ru.ekuchin.lambda;

public class Calculator {

    public static Arithmetic getAction(int num){
        switch (num){
            case 0:
                return (x, y) -> x-y;
            case 1:
                //return (x,y) ->x+y;
                return Integer::sum;
            default:
                return (x,y)->0;
        }
    }
    public static int doAction(int x, int y, Arithmetic action){
        return action.getResult(x,y);
    }
}
