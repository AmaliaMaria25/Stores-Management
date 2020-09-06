package com.java.services;

import com.java.models.Product;
import com.java.models.Section;
import com.java.models.Store;

import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @param <T> can be a Product, Section or Store
 *
 * @level may contain
 * -> no values (if the operation take place on a store)
 * -> Store.name (if the operation take place on a section)
 * -> Store.name and Section.name (if the operation take place on a product)
 *
 * @name is the unique id of the object
 * @K is the type of the unique id
 */

public class GenericService<T,K> {
    protected void add(final String FILE, T object,  K... level){
        try(FileOutputStream fileOutputStream = new FileOutputStream(new File(FILE));
            XMLEncoder xmlEncoder = new XMLEncoder(fileOutputStream)){

            if(object instanceof Store && level.length == 0){

            }else if(object instanceof Section && level.length == 1){

            }else if(object instanceof Product && level.length == 2){

            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    protected void update(final String FILE,T object, K... level ){

    }

    protected void delete(final String FILE,K name,K... level){

    }

    protected T searchFirst(final String FILE, K name,K... level){
        return null;
    }

    protected List<T> searchAll(final String FILE, K name,K... level) {
       return null;
    }

}
