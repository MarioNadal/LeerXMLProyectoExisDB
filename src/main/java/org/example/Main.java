package org.example;

import database.SubirArchivos;

public class Main {
    public static void main(String[] args) {
        //Leer los archivos desde resources
        //Una vez que tengo todos los datos crudos en ExistDB el csv lo transformo a xml o antes
        //Cuando ejecute el programa me tiene que subir los XML a ExistDB en la coleccion de los datos sin tratar
        String guiones = "-".repeat(20);
        int menu;
        boolean salir = false;
        do {
            System.out.println(guiones);
            System.out.println("1. Subir todos los archivos");
            System.out.println("0. Salir");
            System.out.println(guiones);
            menu = libs.Leer.introduceEntero("Introduce el número del menú: ");
            System.out.println(guiones);

            //Igual se tendrá que hacer con if else si el jdk es menor al que necesita el switch
            switch (menu) {

                case 1 -> SubirArchivos.subir();
                case 0 -> salir = true;
                default -> System.out.println("Ese número no esta en el menú, introduzca un número del menu.");
            }
        } while (!salir);
    }
}