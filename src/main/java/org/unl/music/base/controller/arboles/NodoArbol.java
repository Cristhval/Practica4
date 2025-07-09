package org.unl.music.base.controller.arboles;



public class NodoArbol {
    private int valor;
    private NodoArbol nodoIzquierdo;
    private NodoArbol nodoDerecho;

    public NodoArbol(int valor) {
        this.valor = valor;
        this.nodoIzquierdo = null;
        this.nodoDerecho = null;
    }

    public int getValor() {
        return valor;
    }
    public void setValor(int valor) {
        this.valor = valor;
    }

    public NodoArbol getNodoIzquierdo() {
        return nodoIzquierdo;
    }

    public NodoArbol getNodoDerecho() {
        return nodoDerecho;
    }

    public void insertar(int valor1) {
        if (valor1 < this.valor) {
            //PAra insetar en el lado izquierdo
            if (this.nodoIzquierdo == null) {
                this.nodoIzquierdo = new NodoArbol(valor1);
            }else  {
                this.nodoIzquierdo.insertar(valor1);
            }
        }else{
            //PARA insertar en el lado derecho
            if (this.nodoDerecho == null) {
                this.nodoDerecho = new NodoArbol(valor1);
            }else   {
                this.nodoDerecho.insertar(valor1);
            }
        }
    }
}
