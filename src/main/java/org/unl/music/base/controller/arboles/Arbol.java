package org.unl.music.base.controller.arboles;

public class Arbol {
    NodoArbol inicial;

    public Arbol() {
        this.inicial = null;
    }

    public void insertar(int valor) {
        if (inicial == null) {
            this.inicial = new NodoArbol(valor);
        }else  {
            this.inicial.insertar(valor);
        }
    }

    public void dispararPreorden() {
        this.preorden(this.inicial);
    }
    public void preorden(NodoArbol nodo) {
        if (nodo == null) {
            return;
        }else{
            System.out.print(nodo.getValor()+ ", ");
            preorden(nodo.getNodoIzquierdo());
            preorden(nodo.getNodoDerecho());

        }
    }


    public void dispararInorden() {
        this.inorden(this.inicial);
    }
    public void inorden(NodoArbol nodo) {
        if (nodo == null) {
            return;
        }else{
            inorden(nodo.getNodoIzquierdo());
            System.out.print(nodo.getValor()+ ", ");
            inorden(nodo.getNodoDerecho());

        }
    }

    public void dispararPostorden() {
        this.postOrden(this.inicial);
    }
    public void postOrden(NodoArbol nodo) {
        if (nodo == null) {
            return;
        }else{
            postOrden(nodo.getNodoIzquierdo());
            postOrden(nodo.getNodoDerecho());
            System.out.print(nodo.getValor()+ ", ");

        }
    }

    public void imprimirArbol() {
        imprimirArbol(this.inicial, 0);
    }

    private void imprimirArbol(NodoArbol nodo, int nivel) {
        if (nodo == null) {
            return;
        }

        imprimirArbol(nodo.getNodoDerecho(), nivel + 1);
        for (int i = 0; i < nivel; i++) {
            System.out.print("     ");
        }
        System.out.println(nodo.getValor()+"<");

        imprimirArbol(nodo.getNodoIzquierdo(), nivel + 1);
    }

}
