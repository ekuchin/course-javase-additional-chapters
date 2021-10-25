package ru.ekuchin.filetypes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name = "cats")
public class JaxbCatCollection {

    private ArrayList<JaxbCat> cats;

    @XmlElement(name = "cat")
    public ArrayList<JaxbCat> getCats() {
        return cats;
    }

    public void setCats(ArrayList<JaxbCat> cats) {
        this.cats = cats;
    }
}
