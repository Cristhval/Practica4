package org.unl.music.base.controller.arboles;

public class main {
    public static void main(String[] args) {
        Arbol arbol1 = new Arbol();
        arbol1.insertar("m");
        arbol1.insertar("g");
        arbol1.insertar("f");
        arbol1.insertar("l");
        arbol1.insertar("a");
        arbol1.insertar("n");
        arbol1.insertar("z");
        System.out.println("Arbol normal:");
        arbol1.imprimirArbol();
        System.out.println("........................................................");

        Arbol arbol = new Arbol();
        arbol.insertar("camion");
        arbol.insertar("avion");
        arbol.insertar("dog");
        arbol.insertar("cat");
        arbol.insertar("pezcado");
        arbol.insertar("ramon");

//        System.out.println("INORDEN: ");
//        arbol.dispararInorden();
//        System.out.println(" ");
//        System.out.println("PostORden: ");
//        arbol.dispararPostorden();
//        System.out.println(" ");
//         System.out.println("Preorden: ");
//        arbol.dispararPreorden();
//        System.out.println(" ");

        System.out.println("Arbol normal:");
        arbol.imprimirArbol();
    }
}
