package org.unl.music.base.controller.data_struct.grafos;

import org.unl.music.base.controller.data_struct.list.LinkedList;

import java.util.HashMap;

public class Dijkstra {

    int[] inicio = null;
    int[] fin = null;
    boolean[][] visitado;
    int fila = 0;
    int columna = 0;
    int[][] movimiento = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    HashMap<String, String> lab = new HashMap<>(); // guardar el recorrido
    int[][] distanciaMinima;

    public LinkedList<int[]> distancias(char[][] matriz) throws Exception {
        fila = matriz.length;
        columna = matriz[0].length;
        visitado = new boolean[fila][columna];
        distanciaMinima = new int[fila][columna];
        LinkedList<int[]> recorrido = new LinkedList<>();

        // Buscar 'S' y 'E'
        for (int ii = 0; ii < fila; ii++) {
            for (int jj = 0; jj < columna; jj++) {
                if (matriz[ii][jj] == 'S') {
                    inicio = new int[]{ii, jj};
                }
                if (matriz[ii][jj] == 'E') {
                    fin = new int[]{ii, jj};
                }
            }
        }

        // Inicializar distancias
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                distanciaMinima[i][j] = Integer.MAX_VALUE;
            }
        }

        // Verificar que haya punto de inicio y fin
        if (inicio != null && fin != null) {
            LinkedList<int[]> cola = new LinkedList<>();
            cola.add(inicio, 0);
            int i = inicio[0];
            int j = inicio[1];
            visitado[i][j] = true;
            distanciaMinima[i][j] = 0;

            while (cola != null && !cola.isEmpty()) {
                int[] posicion = cola.delete(0);

                if (posicion[0] == fin[0] && posicion[1] == fin[1]) {
                    break; // se llegó al destino
                }

                for (int[] movi : movimiento) {
                    int nuevaFila = posicion[0] + movi[0];
                    int nuevaCol = posicion[1] + movi[1];

                    if (nuevaFila < 0 || nuevaCol < 0 || nuevaFila >= fila || nuevaCol >= columna) {
                        continue;
                    }

                    if (matriz[nuevaFila][nuevaCol] == '█') {
                        continue;
                    }

                    if (visitado[nuevaFila][nuevaCol]) {
                        continue;
                    }

                    if (distanciaMinima[posicion[0]][posicion[1]] + 1 < distanciaMinima[nuevaFila][nuevaCol]) {
                        distanciaMinima[nuevaFila][nuevaCol] = distanciaMinima[posicion[0]][posicion[1]] + 1;
                        cola.add(new int[]{nuevaFila, nuevaCol}, 0);
                        lab.put(nuevaFila + "," + nuevaCol, posicion[0] + "," + posicion[1]); // guardar padre
                    }

                    visitado[nuevaFila][nuevaCol] = true;
                }
            }

            // reconstruir el camino desde fin a inicio
            String salidaE = fin[0] + "," + fin[1];
            if (!lab.containsKey(salidaE)) {
                System.out.println("No se encontró un camino hasta E.");
                return recorrido;
            }

            while (!salidaE.equals(inicio[0] + "," + inicio[1])) {
                String[] partes = salidaE.split(",");
                recorrido.add(new int[]{Integer.parseInt(partes[0]), Integer.parseInt(partes[1])});
                salidaE = lab.get(salidaE);
                if (salidaE == null) break;
            }

            recorrido.add(inicio); // agregar el inicio
            recorrido = reverse(recorrido); // invertir para que empiece desde S
        } else {
            System.out.println("El laberinto no tiene S o E");
        }

        return recorrido;
    }

    private LinkedList<int[]> reverse(LinkedList<int[]> original) {
        LinkedList<int[]> reversed = new LinkedList<>();
        Object[] elementos = original.toArray();  // convierte a array
        for (int i = elementos.length - 1; i >= 0; i--) {
            reversed.add((int[]) elementos[i]);
        }
        return reversed;
    }


    public void caminoLab(LinkedList<int[]> recorrido, char[][] matriz, int tamanioFila, int tamanioColumna) {
        for (int[] valor : recorrido.toArray()) {
            if (matriz[valor[0]][valor[1]] == ' ') {
                matriz[valor[0]][valor[1]] = '.';
            }
        }

        StringBuilder s = new StringBuilder();
        for (int i = 0; i < tamanioFila; i++) {
            String aux = "";
            for (int j = 0; j < tamanioColumna; j++) {
                aux += matriz[i][j] + " ";
            }
            aux = aux.trim();
            s.append(aux).append("\n");
        }

        System.out.println(s.toString());
    }

    public void mostrarPasoAPaso(LinkedList<int[]> recorrido, char[][] matriz) throws InterruptedException {
        for (int[] paso : recorrido.toArray()) {
            if (matriz[paso[0]][paso[1]] == ' ') {
                matriz[paso[0]][paso[1]] = '.'; // marca el camino
            }
            imprimirLaberinto(matriz);
            Thread.sleep(150); // retardo para simular animación
        }
    }

    private void imprimirLaberinto(char[][] matriz) {
        for (char[] fila : matriz) {
            for (char c : fila) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
        System.out.println("-----------------------");
    }
}
