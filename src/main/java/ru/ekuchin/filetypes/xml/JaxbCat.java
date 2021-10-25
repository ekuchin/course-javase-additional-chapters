package ru.ekuchin.filetypes.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cat")
public class JaxbCat {

    private String name;
    private String breed;
    private int weight;
    private boolean isAngry;

    public JaxbCat() {
    }

    @Override
    public String toString() {
        return "XmlCat{" +
                "name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", weight=" + weight +
                ", isAngry=" + isAngry +
                '}';
    }

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "breed")
    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @XmlElement(name = "weight")
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @XmlElement(name = "isAngry")
    public boolean isAngry() {
        return isAngry;
    }

    public void setAngry(boolean angry) {
        isAngry = angry;
    }

    public JaxbCat(String name, String breed, int weight, boolean isAngry) {
        this.name = name;
        this.breed = breed;
        this.weight = weight;
        this.isAngry = isAngry;
    }




}