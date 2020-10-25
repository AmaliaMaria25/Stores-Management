package com.java.services;

import com.java.Main;
import com.java.models.Section;
import com.java.models.Store;

//import javax.rmi.CORBA.Util;
import java.util.*;

public class SectionService {
    public static void add(final String FILE_NAME, Section section, String storeName, List<Store> storeList){
        Optional<Store> store = StoreService.searchStore(storeName,storeList);
        if(!store.isPresent()) return;
        if(store.get().getSections() == null) store.get().setSections(new HashSet<>());

        if(searchSection(section.getName(),store.get().getSections()) != null){
            System.out.println("Section already exist!");
            return;
        }
        store.get().getSections().add(section);

        UtilService.writeInXML(FILE_NAME.concat(".csv"),storeList);
        UtilService.writeInCSV(FILE_NAME.concat(".csv"), storeList);
    }

    public static void update(final String FILE_NAME, Section section, String storeName, String sectionName, List<Store> storeList){
        Optional<Store> store = StoreService.searchStore(storeName,storeList);
        if(!store.isPresent()) return;

        Section searchedSection = searchSection(sectionName,store.get().getSections());
        if(searchedSection == null) return;

        searchedSection.setId(section.getId());
        searchedSection.setName(section.getName());

        UtilService.writeInXML(FILE_NAME.concat(".xml"),storeList);
        UtilService.writeInCSV(FILE_NAME.concat(".csv"), storeList);
    }

    public static void delete(final String FILE_NAME, String sectionName, String storeName, List<Store> storeList){
        Optional<Store> store = StoreService.searchStore(storeName,storeList);
        if(!store.isPresent()) return;

        Section searchedSection = searchSection(sectionName,store.get().getSections());
        if(searchedSection == null) return;

        store.get().getSections().remove(searchedSection);

        UtilService.writeInXML(FILE_NAME.concat(".xml"),storeList);
        UtilService.writeInCSV(FILE_NAME.concat(".csv"), storeList);
    }


    public static Section searchSection(String sectionName, Set<Section> sectionList) {
        try {
            return sectionList.stream().filter(section -> section.getName().compareTo(sectionName) == 0).findFirst().get();
        } catch (NoSuchElementException exception) {
            //System.out.println("Could not find the section with the specified name;");
        }
        return null;
    }

    public static void createSection(){
        String chosenStore = "";
        String sectionName="";

        chosenStore = StoreService.readStore();
        if(!StoreService.searchStore(chosenStore,Main.getStores()).isPresent()) return;

        System.out.println("Choose a section name");
        sectionName = UtilService.getScanner().next();
        add(Main.getFileName(),new Section(0,sectionName),chosenStore,Main.getStores() );
    }

    public static String readSection(Store store){
        String chosenSection = "";
        System.out.println("Type the wanted section: ");
        for(Section loopSection:store.getSections()){
            System.out.println(loopSection.getName()+"\t");
        }
        chosenSection = UtilService.getScanner().next();
        return chosenSection;
    }

    public static void editSection(){
        String newData = "";
        String chosenStore = StoreService.readStore();
        Optional<Store> store = StoreService.searchStore(chosenStore,Main.getStores());
        if(!store.isPresent()) return;

        String chosenSection = readSection(store.get());

        System.out.println("Choose a new name:");
        newData =  UtilService.getScanner().next();

        update(Main.getFileName(),new Section(0,newData),chosenStore,chosenSection,Main.getStores());
    }

    public static void deleteSection(){
        String chosenSection = "";
        String chosenStore = StoreService.readStore();
        Optional<Store> store = StoreService.searchStore(chosenStore,Main.getStores());
        if(!store.isPresent()) return;

        chosenSection = readSection(store.get());
        delete(Main.getFileName(),chosenSection,chosenStore,Main.getStores());
    }

    public static void display(Store store){
        try {
            store.getSections().forEach(System.out::println);
        }catch(NullPointerException nullPointerException){
        System.out.println("Couldn't find any sections of this storage");
    }
    }

    public static void displaySections(){
        String chosenStore = StoreService.readStore();
        Optional<Store> store = StoreService.searchStore(chosenStore,Main.getStores());
        display(store.get());
    }
}
