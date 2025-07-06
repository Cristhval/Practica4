package org.unl.music.base.controller.data_struct.grafos;

import org.unl.music.base.controller.data_struct.list.LinkedList;

public class Dijkstra {

    public LinkedList<Integer> dijkstra(UndirectedGraph grafo, int origen, int destino) throws Exception {
        int n = grafo.nro_vertex();
        float[] dist = new float[n];
        boolean[] visitado = new boolean[n];
        int[] padre = new int[n];

        for (int i = 0; i < n; i++) {
            dist[i] = Float.MAX_VALUE;
            visitado[i] = false;
            padre[i] = -1;
        }

        dist[origen] = 0;

        for (int i = 0; i < n; i++) {
            int u = verticeConMinimaDistancia(dist, visitado, n);
            if (u == -1) break;
            visitado[u] = true;

            LinkedList<Adjacency> adyacentes = grafo.getList_adjacencies()[u];
            for (int j = 0; j < adyacentes.getLength(); j++) {
                Adjacency ady = adyacentes.get(j);
                int v = ady.getDestiny();
                float peso = ady.getWeigth();

                if (!visitado[v] && dist[u] + peso < dist[v]) {
                    dist[v] = dist[u] + peso;
                    padre[v] = u;
                }
            }
        }

        //reconstruir camino
        LinkedList<Integer> camino = new LinkedList<>();
        int actual = destino;
        while (actual != -1) {
            camino.add(actual, 0);//insertar al inicio
            actual = padre[actual];
        }

        if (camino.getLength() == 0 || !camino.get(0).equals(origen)) {
            return new LinkedList<>(); //no hay camino
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

    public void marcarCamino(LinkedList<int[]> recorrido, char[][] matriz) {
        for (int[] paso : recorrido.toArray()) {
            if (matriz[paso[0]][paso[1]] == ' ') {
                matriz[paso[0]][paso[1]] = '.'; //marca camino sobre matriz original
            }
        }
    }

}
