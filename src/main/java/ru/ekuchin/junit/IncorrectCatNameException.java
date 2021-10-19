package ru.ekuchin.junit;

public class IncorrectCatNameException extends Exception{
    public IncorrectCatNameException(String message) {
        super(message);
    }
}
