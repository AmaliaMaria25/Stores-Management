package com.java.services;

import com.java.Main;
import com.java.exceptions.InvalidNumericInputException;

public class MenuService {

    public static void clearTrick(){
        for(int i=1;i<=20;i++)
            System.out.println("");
    }

    public static void pause(){
        System.out.println("");
        System.out.println("Press any key to go back to the menu..");
        UtilService.readValue();
    }

    public static void exit(){
        System.out.println("");
        System.out.println("Thank you for using our app!");
    }

    public static void secondaryMenuSelection(String option){
        try{
            final String selected = UtilService.readValue();
            if( UtilService.isNumeric(selected)) {
                final int selectedValue = Integer.parseInt(selected);
                if (selectedValue == 1) {
                    //Option "Display" is selected
                    switch (option) {
                        case "Stores": StoreService.displayStores(); break;
                        case "Sections": SectionService.displaySections(); break;
                        case "Products": ProductService.displayProducts(); break;
                    }
                    pause();
                    secondaryMenu(option);
                } else if (selectedValue == 2) {
                    //Option "Create" is selected
                    switch (option) {
                        case "Stores": StoreService.createStore(); break;
                        case "Sections": SectionService.createSection(); break;
                        case "Products": ProductService.createProduct(); break;
                    }
                    pause();
                    secondaryMenu(option);
                } else if (selectedValue == 3) {
                    //Option "Edit" is selected
                     switch (option) {
                         case "Stores": StoreService.editStore(); break;
                         case "Sections": SectionService.editSection(); break;
                         case "Products": ProductService.editProduct(); break;
                       }
                    pause();
                    secondaryMenu(option);
                } else if (selectedValue == 4) {
                    //Option "Delete" is selected
                    switch (option) {
                         case "Stores": StoreService.deleteStore(); break;
                         case "Sections": SectionService.deleteSection(); break;
                         case "Products": ProductService.deleteProduct(); break;
                     }
                     pause();
                     secondaryMenu(option);
                } else if (selectedValue == 5) {
                    //Option "Back to Main Menu" is selected
                    mainMenu();
                }else throw new InvalidNumericInputException();
            }
        }catch(InvalidNumericInputException e) {
            System.out.println(e.getMessage());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            secondaryMenuSelection(option);
        }
    }

    public static void secondaryMenu(String option){
        clearTrick();
        System.out.println(option+" Menu:");
        System.out.println("1 - Display");
        System.out.println("2 - Create");
        System.out.println("3 - Edit");
        System.out.println("4 - Delete");
        System.out.println("5 - Back to Main Menu");
        secondaryMenuSelection(option);
    }

    public static void mainMenuSelection(){
        try{
            final String selected = UtilService.readValue();
            if( UtilService.isNumeric(selected)) {
                final int selectedValue = Integer.parseInt(selected);
                if (selectedValue == 1) {
                    //Option "Stores" is selected
                    secondaryMenu("Stores");
                    // loadStores(); createNewStore();
                } else if (selectedValue == 2) {
                    //Option "Sections" is selected
                    secondaryMenu("Sections");
                } else if (selectedValue == 3) {
                    //Option "Products" is selected
                    secondaryMenu("Products");
                } else if (selectedValue == 4) {
                    //Option "Display" is selected
                    //displayAll();
                    //System.out.println(Main.getStores());
                    UtilService.displayStock(Main.getStores());
                    pause();
                    mainMenu();
                } else if (selectedValue == 5) {
                    //Option "Export CSV" is selected
                    //exportCSV();
                    pause();
                    mainMenu();
                } else if (selectedValue == 6) {
                    //Option "Archive CSV as Zip" is selected
                    UtilService.archiveAsZip();
                    pause();
                    mainMenu();
                } else if (selectedValue == 7) {
                    //Option "Exit" is selected
                    exit();
                }else throw new InvalidNumericInputException();
            }
        }catch(InvalidNumericInputException e) {
            System.out.println(e.getMessage());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            mainMenuSelection();
        }
    }

    public static void mainMenu(){
        clearTrick();
        System.out.println("Welcome to Stores Management App");
        System.out.println("Choose an option:");
        System.out.println("1 - Stores");
        System.out.println("2 - Sections");
        System.out.println("3 - Products");
        System.out.println("4 - Display all data");
        System.out.println("5 - Save CSV");
        System.out.println("6 - Archive File as Zip");
        System.out.println("7 - Exit");
        mainMenuSelection();
    }
}
