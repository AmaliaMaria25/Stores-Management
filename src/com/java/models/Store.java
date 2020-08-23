package com.java.models;

import java.util.Set;

public class Store {

    private int id;
    private String name;
    private Set<Section> sections;

    public Store(int id, String name, Set<Section> sections) {
        this.id = id;
        this.name = name;
        this.sections = sections;
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
}
