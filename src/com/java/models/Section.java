package com.java.models;

import java.io.Serializable;
import java.util.Set;

public class Section implements Serializable {

    private int id;
    private String name;
    private Set<Product> products;

    public Section() {
    }

    public Section(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Section(int id, String name, Set<Product> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    @Override
    public String toString() {
        return "Section{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", products=" + products +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }


}
