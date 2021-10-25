package ru.ekuchin.filetypes.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JacksonCat {
    private String name;
    private String breed;
    private int weight;
    private boolean isAngry;

    public JacksonCat(String name, String breed, int weight, boolean isAngry) {
        this.name = name;
        this.breed = breed;
        this.weight = weight;
        this.isAngry = isAngry;
    }

    @Override
    public String toString() {
        return "JacksonCat{" +
                "name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", weight=" + weight +
                ", isAngry=" + isAngry +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isAngry() {
        return isAngry;
    }

    @JsonProperty("isAngry")
    public void setAngry(boolean angry) {
        isAngry = angry;
    }

    public JacksonCat() {
    }
}
