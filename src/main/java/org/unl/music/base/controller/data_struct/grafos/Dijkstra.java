package org.unl.music.base.controller.data_struct.grafos;

import org.unl.music.base.controller.data_struct.list.LinkedList;

public class Dijkstra {


    public LinkedList<Integer> dijkstra(UndirectedGraph grafo, int origen, int destino, float[] distancias) throws Exception {
        int n = grafo.nro_vertex();
        boolean[] visitado = new boolean[n];
        int[] padre = new int[n];

        for (int i = 0; i < n; i++) {
            distancias[i] = Float.MAX_VALUE;
            visitado[i] = false;
            padre[i] = -1;
        }

        distancias[origen] = 0;

        for (int i = 0; i < n; i++) {
            int u = verticeConMinimaDistancia(distancias, visitado, n);
            if (u == -1) break;
            visitado[u] = true;

            LinkedList<Adjacency> adyacentes = grafo.getList_adjacencies()[u];
            for (int j = 0; j < adyacentes.getLength(); j++) {
                Adjacency ady = adyacentes.get(j);
                int v = ady.getDestiny();
                float peso = ady.getWeigth();

                if (!visitado[v] && distancias[u] + peso < distancias[v]) {
                    distancias[v] = distancias[u] + peso;
                    padre[v] = u;
                }
            }
        }

        // reconstruir camino
        LinkedList<Integer> camino = new LinkedList<>();
        int actual = destino;
        while (actual != -1) {
            camino.add(actual, 0); // insertar al inicio
            actual = padre[actual];
        }

        if (camino.getLength() == 0 || !camino.get(0).equals(origen)) {
            return new LinkedList<>(); // no hay camino
        }

        return camino;
    }


    private int verticeConMinimaDistancia(float[] dist, boolean[] visitado, int n) {
        float min = Float.MAX_VALUE;
        int indice = -1;
        for (int i = 0; i < n; i++) {
            if (!visitado[i] && dist[i] < min) {
                min = dist[i];
                indice = i;
            }
        }
        return indice;
    }

}
