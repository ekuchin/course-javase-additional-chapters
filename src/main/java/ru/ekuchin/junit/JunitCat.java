package ru.ekuchin.junit;

public class JunitCat {

    private final String DEFAULT_BREED = "Беспородный";

    private String name;
    private String breed;
    private int weight;
    private boolean isAngry;

    public JunitCat(String name, String breed, int weight, boolean isAngry) throws IncorrectCatNameException, IncorrectCatWeightException {
        setName(name);
        setBreed(breed);
        setWeight(weight);
        setAngry(isAngry);
    }

    public JunitCat(String name, int weight) throws IncorrectCatNameException, IncorrectCatWeightException {
        setName(name);
        setBreed(DEFAULT_BREED);
        setWeight(weight);
        setAngry(false);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) throws IncorrectCatNameException {
        if (name.equals("") || name==null){
            throw new IncorrectCatNameException("У кота должно быть имя. Всегда.");
        }
        this.name = name;
    }
    public String getBreed() {
        return breed;
    }
    public void setBreed(String breed) {
        if (breed.equals("") || breed==null){
            this.breed = DEFAULT_BREED;
        }
        else{
            this.breed = breed;
        }
    }
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) throws IncorrectCatWeightException {
        if (weight<=0 || weight>=100){
            throw new IncorrectCatWeightException("Коты не могут столько весить");
        }
        this.weight = weight;
    }
    public boolean isAngry() {
        return isAngry;
    }
    public void setAngry(boolean angry) {
        isAngry = angry;
    }
}
