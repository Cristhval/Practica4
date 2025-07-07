package org.unl.music.base.controller.data_struct.grafos;

import org.unl.music.base.controller.data_struct.list.LinkedList;

import java.util.ArrayList;
import java.util.Scanner;

public class Prim2 {
    char[][] maz;//donde se guardan los datos del laberinto

    public char[][] getMaz() {
        return maz;
    }

    public void generar(int r, int c) {
        //construir laberinto lleno de muros
        StringBuilder s = new StringBuilder(c);
        for (int x = 0; x < c; x++) {
            s.append('0');

        }

        maz = new char[r][c];
        for (int x = 0; x < r; x++) {
            maz[x] = s.toString().toCharArray();
        }


        //seleccionar punto inicial para evitar bordes
        Point st = new Point(1 + (int) (Math.random() * (r - 2)), 1 + (int) (Math.random() * (c - 2)), null);
        maz[st.r][st.c] = 'S';

        //vecinos directos del nodo inicial"S"
        ArrayList<Point> frontier = new ArrayList<Point>();
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if ((x == 0 && y == 0) || (x != 0 && y != 0)) {
                    continue;
                }
                try {
                    if (maz[st.r + x][st.c + y] == ' ') {
                        continue;
                    }
                } catch (Exception e) {
                    continue;
                }
                frontier.add(new Point(st.r + x, st.c + y, st));
            }
        }

        Point last = null;
        while (!frontier.isEmpty()) {
            Point cu = frontier.remove((int) (Math.random() * frontier.size()));
            Point op = cu.opposite();

            try {
                //evitar que op sea nulo o este en el borde
                if (op == null || op.r <= 0 || op.r >= r - 1 || op.c <= 0 || op.c >= c - 1) {
                    continue;
                }

                if (maz[cu.r][cu.c] == '0' && maz[op.r][op.c] == '0') {
                    maz[cu.r][cu.c] = ' ';
                    maz[op.r][op.c] = ' ';
                    last = op;

                    for (int x = -1; x <= 1; x++) {
                        for (int y = -1; y <= 1; y++) {
                            if ((x == 0 && y == 0) || (x != 0 && y != 0)) {
                                continue;
                            }
                            try {
                                if (maz[op.r + x][op.c + y] == ' ') {
                                    continue;
                                }
                            } catch (Exception e) {
                                continue;
                            }
                            frontier.add(new Point(op.r + x, op.c + y, op));
                        }
                    }
                }
            } catch (Exception e) {

            }

            //cuando termina marcar salida
            if (frontier.isEmpty() && last != null) {
                maz[last.r][last.c] = 'E';

                // abrir solo una salida
                int distArriba = last.r;
                int distAbajo = r - 1 - last.r;
                int distIzquierda = last.c;
                int distDerecha = c - 1 - last.c;

                int minDist = Math.min(Math.min(distArriba, distAbajo), Math.min(distIzquierda, distDerecha));

                if (minDist == distArriba) {
                    for (int i = last.r - 1; i >= 0; i--) {
                        maz[i][last.c] = ' ';
                    }
                } else if (minDist == distAbajo) {
                    for (int i = last.r + 1; i < r; i++) {
                        maz[i][last.c] = ' ';
                    }
                } else if (minDist == distIzquierda) {
                    for (int j = last.c - 1; j >= 0; j--) {
                        maz[last.r][j] = ' ';
                    }
                } else { //distDerecha
                    for (int j = last.c + 1; j < c; j++) {
                        maz[last.r][j] = ' ';
                    }
                }

            }
        }

        //Verifica que 'S' y 'E' existan; si no estan se regenerar el laberinto
        boolean tieneS = false, tieneE = false;
        for (char[] fila : maz) {
            for (char ch : fila) {
                if (ch == 'S') tieneS = true;
                if (ch == 'E') tieneE = true;
            }
        }
        if (!tieneS || !tieneE) {
            System.out.println(" Regenerando laberinto por falta de 'S' o 'E'...");
            generar(r, c);
        }

        // construir cadena de texto del laberinto
//        s = new StringBuilder();
//        for (int i = 0; i < r; i++) {
//            String aux = "";
//            for (int j = 0; j < c; j++) {
//                aux += maz[i][j] + " ";
//            }
//            aux = aux.substring(0, aux.length() - 1);
//            s.append(aux).append("\n");
//        }
//

    }

    static class Point {
        Integer r;
        Integer c;
        Point parent;

        public Point(int x, int y, Point p) {
            r = x;
            c = y;
            parent = p;
        }

        public Point opposite() {
            if (parent == null) return null;
            if (!this.r.equals(parent.r)) {
                return new Point(this.r + this.r.compareTo(parent.r), this.c, this);
            }
            if (!this.c.equals(parent.c)) {
                return new Point(this.r, this.c + this.c.compareTo(parent.c), this);
            }
            return null;
        }
    }



    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese el numero de filas minimo 30: ");
        int filas = sc.nextInt();
        System.out.print("Ingrese el numero de columnas minimo 30: ");
        int columnas = sc.nextInt();

        if (filas < 30) {
            filas = 30;
            System.out.println("Se procederz con 30 filas que es el minimo");
        }

        if (columnas < 30) {
            columnas = 30;
            System.out.println("Se procedera con 30 columnas que es el minimo");
        }

        System.out.println("Generando laberinto de " + filas + "x" + columnas);

        //Generar laberinto
        Prim2 prim = new Prim2();
        prim.generar(filas, columnas);
        char[][] laberinto = prim.getMaz();

        //Buscar coordenadas de S y E
        int filaS = -1, colS = -1, filaE = -1, colE = -1;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (laberinto[i][j] == 'S') {
                    filaS = i;
                    colS = j;
                }
                if (laberinto[i][j] == 'E') {
                    filaE = i;
                    colE = j;
                }
            }
        }

        if (filaS == -1 || filaE == -1) {
            System.out.println("No se encontro punto de inicio o fin");
            return;
        }

        // Construir grafo y ejecutar Dijkstra
        UndirectedGraph grafo = construirGrafo(laberinto);
        int idOrigen = filaS * columnas + colS;
        int idDestino = filaE * columnas + colE;

        float[] distancias = new float[filas * columnas];
        Dijkstra dij = new Dijkstra();
        LinkedList<Integer> camino = dij.dijkstra(grafo, idOrigen, idDestino, distancias);

        if (camino.getLength() == 0) {
            System.out.println("No hay camino entre S y E");
            return;
        }

        //Imprimir el camino con pesos acumulados
        System.out.println("\n Recorrido del camino mas corto (coordenadas y peso acumulado):");
        for (int i = 0; i < camino.getLength(); i++) {
            int id = camino.get(i);
            int fila = id / columnas;
            int col = id % columnas;
            System.out.printf("(%d, %d) -> Peso acumulado: %.0f\n", fila, col, distancias[id]);
        }

        //Mostrar la vista del laberinto
        LaberintoVista panel = LaberintoVista.mostrar(laberinto);

        //Animar recorrido en la vista //camino = dijsktra
        for (int i = 0; i < camino.getLength(); i++) {
            int id = camino.get(i);
            int fila = id / columnas;
            int col = id % columnas;
            if (laberinto[fila][col] == ' ') {
                laberinto[fila][col] = '.'; // marcar camino
            }
            panel.repaint();
            Thread.sleep(200);
        }
    }

    public static UndirectedGraph construirGrafo(char[][] laberinto) {
        int filas = laberinto.length;
        int columnas = laberinto[0].length;
        UndirectedGraph grafo = new UndirectedGraph(filas * columnas);

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (laberinto[i][j] != '0') {
                    int idActual = i * columnas + j;
                    int[][] vecinos = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
                    for (int[] dir : vecinos) {
                        int ni = i + dir[0];
                        int nj = j + dir[1];
                        if (ni >= 0 && ni < filas && nj >= 0 && nj < columnas && laberinto[ni][nj] != '0') {
                            int idVecino = ni * columnas + nj;
                            grafo.insert(idActual, idVecino, 1f);
                        }
                    }
                }
            }
        }
        return grafo;
    }


}
