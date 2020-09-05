package com.java;

import com.java.models.Product;
import com.java.models.Section;
import com.java.models.Store;
import com.java.services.ProductService;
import com.java.services.SectionService;
import com.java.services.StoreService;
import com.java.services.UtilService;
import javafx.fxml.FXML;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.*;

public class Main {

    private static final String FILE_NAME = "section.xml";
    private static List<Store> stores;
    public static void main(String[] args) {
        init();
        //UtilService.mainMenuSelection();

    }
    private static void init(){
        stores = UtilService.getAllData(FILE_NAME);
        if(stores == null) stores = new ArrayList<>();
    }
}
