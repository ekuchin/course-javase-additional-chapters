package ru.ekuchin.patterns.proxy;

public class SlowDataSource implements DataSource {

    private String data;

    @Override
    public String getData() throws Exception {
        Thread.sleep(2500);
        return data;
    }

    public SlowDataSource(String data) {
        this.data = data;
    }

    @Override
    public void setData(String data) {
        this.data = data;
    }
}
