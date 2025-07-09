package org.unl.music.base.controller.arboles;

public class main {
    public static void main(String[] args) {
        Arbol arbol = new Arbol();
        arbol.insertar(43);
        arbol.insertar(10);
        arbol.insertar(8);
        arbol.insertar(54);
        arbol.insertar(15);
        arbol.insertar(50);
        arbol.insertar(60);

        System.out.println("INORDEN: ");
        arbol.dispararInorden();
        System.out.println(" ");
        System.out.println("PostORden: ");
        arbol.dispararPostorden();
        System.out.println(" ");
         System.out.println("Preorden: ");
        arbol.dispararPreorden();
        System.out.println(" ");

        System.out.println("Arbol normal:");
        arbol.imprimirArbol();
    }
}
