package org.unl.music.base.controller;

import org.unl.music.base.controller.data_struct.grafos.DirectLableGraph;
import org.unl.music.base.controller.data_struct.grafos.DirectedGraph;
import org.unl.music.base.controller.data_struct.grafos.Graph;
import org.unl.music.base.controller.data_struct.list.LinkedList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class Practica {

    private LinkedList<Integer> lista;

    public void cargar() {
        String pathArchivo = "C:\\Users\\HP\\Tercer Ciclo proyectos\\Nueva carpeta\\unl-music\\src\\main\\java\\org\\unl\\music\\base\\controller\\data.txt";

        Integer datos[] = new Integer[15000];

        try {
            BufferedReader br = new BufferedReader(new FileReader(pathArchivo));
            String linea;
            int i = 0;

            while ((linea = br.readLine()) != null) {
                datos[i] = Integer.parseInt(linea);
                i++;
            }

            Integer[] copiaDatos = new Integer[i];
            System.arraycopy(datos, 0, copiaDatos, 0, i);
            contadorNumeros(copiaDatos);
            lista = new LinkedList<>();

            for (int j = 0; j < i; j++) {
                if (datos[j] != null)
                    lista.add(datos[j]);
            }

        } catch (Exception e) {
            System.out.println("El error es: " + e.getMessage());
        }
    }

    private static void contadorNumeros(Integer[] numeros) {
        int repetidosTotales = 0;
        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i] == null) continue;
            int numero = numeros[i];
            int cont = 0;
            for (int j = i + 1; j < numeros.length; j++) {
                if (numeros[j] != null && numero == numeros[j]) {
                    cont++;
                    numeros[j] = null;
                }
            }
            if (cont > 0) {
                //System.out.println("El numero: " + numero + " se repite " + cont);
                repetidosTotales++;
                numeros[i] = null;
            }

        }
        //System.out.println("Los numeros totales repetidos es de: " + repetidosTotales);

    }


    public LinkedList<Integer> verificar_lista() {
        LinkedList<Integer> resp = new LinkedList<>();
        HashMap<Integer, Integer> contador = new HashMap<>();
        int contadorL = 0;

        for (int i = 0; i < lista.getLength(); i++) {
            Integer valor = lista.get(i);
            if (valor == null) continue;


            contador.put(valor, contador.getOrDefault(valor, 0) + 1);
        }


        for (Integer clave : contador.keySet()) {
            if (contador.get(clave) > 1) {
                contadorL++;
                resp.add(clave);
            }
        }
        System.out.println("Los numeros repetidos son: " + contadorL);

        return resp;
    }

    // PRACTICA ORDER
    private void quickSort(Integer arr[], int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private int partition(Integer arr[], int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                int swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }
        int swapTemp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = swapTemp;

        return i + 1;
    }

    public void q_order() {
        cargar();
        if (!lista.isEmpty()) {
            Integer arr[] = lista.toArray();
            Integer cont = 0;
            long startTime = System.currentTimeMillis();
            quickSort(arr, 0, arr.length - 1);
            long endTime = System.currentTimeMillis() - startTime;
            System.out.println("se ha demorado quicksort " + endTime + " ms");
            lista.toList(arr);
        }
    }

    public void s_order() {
        cargar();
        if (!lista.isEmpty()) {
            Integer arr[] = lista.toArray();
            Integer cont = 0;
            long startTime = System.currentTimeMillis();
            shell_sort(arr);
            long endTime = System.currentTimeMillis() - startTime;
            System.out.println("se ha demorado shell " + endTime + " ms");
            lista.toList(arr);
        }
    }

    public void shell_sort(Integer arrayToSort[]) {
        int n = arrayToSort.length;

        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int key = arrayToSort[i];
                int j = i;
                while (j >= gap && arrayToSort[j - gap] > key) {
                    arrayToSort[j] = arrayToSort[j - gap];
                    j -= gap;
                }
                arrayToSort[j] = key;
            }
        }
    }





    public static void main(String[] args) {
        //Practica pa = new Practica();


//        System.out.println("PROCESO CON ARREGLO");
//        System.out.println("---------------------------------------------------------------------");
//        long inicioArreglo = System.nanoTime();
//        pa.cargar();
//        long finArreglo = System.nanoTime();
//        long duracionArreglo = finArreglo - inicioArreglo;
//        System.out.println("Tiempo de ejecución (arreglo): " + duracionArreglo + " ns");
//
//
//        System.out.println("PROCESO CON LINKEDLIST");
//        System.out.println("----------------------------------------------------------------------");
//        long inicioLista = System.nanoTime();
//        LinkedList<Integer> repetidos = pa.verificar_lista();
//        long finLista = System.nanoTime();
//        long duracionLista = finLista - inicioLista;
//        System.out.println(repetidos.print());
//        System.out.println("Tiempo de ejecución (lista enlazada): " + duracionLista + " ns");

//        System.out.println("Quicksort");
//        pa.q_order();
//        System.out.println("Shellsort");
//        pa.s_order();

//        Graph grafo = new DirectedGraph(5);
//        grafo.insert(1,5,0.65f);
//        grafo.insert(2,4,0.85f);
//
//        System.out.println(grafo.toString());

        DirectLableGraph gd = new DirectLableGraph<>(5, String.class);
        gd.label_vertex(1, "Nole");
        gd.label_vertex(2, "AAA");
        gd.label_vertex(3, "BBBB");
        gd.label_vertex(4, "CCCC");
        gd.label_vertex(5, "DDD");
        gd.insert_label("DDD", "CCCC", 10.67f);
        gd.insert_label("DDD", "BBBB", 5.65f);
        gd.insert_label("Nole", "AAA", 7.67f);
        System.out.println(gd.toString());

    }
}
