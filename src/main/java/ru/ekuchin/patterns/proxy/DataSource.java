package ru.ekuchin.patterns.proxy;

public interface DataSource {

    String getData() throws Exception;
    void setData(String data);
}
