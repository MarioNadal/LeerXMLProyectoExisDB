package org.example;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="centros")
public class Centros {
    public ArrayList<Centro> centros = new ArrayList<Centro>();

    public Centros() {
    }

    public void setCentros(ArrayList<Centro> centros) {
        this.centros = centros;
    }
}
