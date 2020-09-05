package com.java.models;

import java.io.Serializable;
import java.util.Set;

public class Store implements Serializable {

    private int id;
    private String name;
    private Set<Section> sections;

    public Store(int id, String name, Set<Section> sections) {
        this.id = id;
        this.name = name;
        this.sections = sections;
    }

    public Store(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Store() {
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sections=" + sections +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Section> getSections() {
        return sections;
    }

    public void setSections(Set<Section> sections) {
        this.sections = sections;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
