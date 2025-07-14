package org.unl.music.base.controller.arboles;



public class NodoArbol<E extends Comparable<E>>  {
    private E valor;
    private NodoArbol<E> nodoIzquierdo;
    private NodoArbol<E> nodoDerecho;

    public NodoArbol(E valor) {
        this.valor = valor;
        this.nodoIzquierdo = null;
        this.nodoDerecho = null;
    }

    public E getValor() {
        return valor;
    }
    public void setValor(E valor) {
        this.valor = valor;
    }

    public NodoArbol<E> getNodoIzquierdo() {
        return nodoIzquierdo;
    }

    public NodoArbol<E> getNodoDerecho() {
        return nodoDerecho;
    }

    public void insertar(E valor1) {

        char primerChar = valor1.toString().toLowerCase().charAt(0);

        System.out.println(valor1);
        System.out.println(this.valor);
        if (valor1.compareTo(this.valor)<0) {
            //PAra insetar en el lado izquierdo
            if (this.nodoIzquierdo == null) {
                this.nodoIzquierdo = new NodoArbol<>(valor1);
            }else  {
                this.nodoIzquierdo.insertar(valor1);
            }

        }else{
            //PARA insertar en el lado derecho
            if (this.nodoDerecho == null) {
                this.nodoDerecho = new NodoArbol<>(valor1);
            }else   {
                this.nodoDerecho.insertar(valor1);
            }
        }

    }
}
