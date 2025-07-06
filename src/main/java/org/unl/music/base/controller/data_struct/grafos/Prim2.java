package org.unl.music.base.controller.data_struct.grafos;

import org.unl.music.base.controller.data_struct.list.LinkedList;

import java.util.ArrayList;

public class Prim2 {
    char[][] maz;

    public char[][] getMaz() {
        return maz;
    }

    public String generar(int r, int c) {
        // construir laberinto lleno de muros
        StringBuilder s = new StringBuilder(c);
        for (int x = 0; x < c; x++) {
            s.append('█');
        }
        maz = new char[r][c];
        for (int x = 0; x < r; x++) {
            maz[x] = s.toString().toCharArray();
        }

        // seleccionar punto inicial (evitar bordes)
        Point st = new Point(1 + (int) (Math.random() * (r - 2)), 1 + (int) (Math.random() * (c - 2)), null);
        maz[st.r][st.c] = 'S';

        // vecinos directos del nodo inicial
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
                // evitar que op sea nulo o esté en el borde
                if (op == null || op.r <= 0 || op.r >= r - 1 || op.c <= 0 || op.c >= c - 1) {
                    continue;
                }

                if (maz[cu.r][cu.c] == '█' && maz[op.r][op.c] == '█') {
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
                // ignorar excepciones
            }

            // cuando termina, marcar salida
            if (frontier.isEmpty() && last != null) {
                maz[last.r][last.c] = 'E';

                // --- NUEVO: abrir solo una salida, hacia el borde más cercano ---
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
                // ---------------------------------------------------------------
            }
        }

        //Verificar que 'S' y 'E' existan; si no, regenerar el laberinto
        boolean tieneS = false, tieneE = false;
        for (char[] fila : maz) {
            for (char ch : fila) {
                if (ch == 'S') tieneS = true;
                if (ch == 'E') tieneE = true;
            }
        }
        if (!tieneS || !tieneE) {
            System.out.println(" Regenerando laberinto por falta de 'S' o 'E'...");
            return generar(r, c);
        }

        // construir cadena de texto del laberinto
        s = new StringBuilder();
        for (int i = 0; i < r; i++) {
            String aux = "";
            for (int j = 0; j < c; j++) {
                aux += maz[i][j] + " ";
            }
            aux = aux.substring(0, aux.length() - 1);
            s.append(aux).append("\n");
        }

        return s.toString();
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
        int filas = 50, columnas = 30; // tamaño del laberinto

        // 1. Generar laberinto
        Prim2 prim = new Prim2();
        prim.generar(filas, columnas);
        char[][] laberinto = prim.getMaz();

        // 2. Construir grafo desde el laberinto
        UndirectedGraph grafo = construirGrafo(laberinto);

        // 3. Ejecutar Dijkstra
        Dijkstra dijkstra = new Dijkstra();
        LinkedList<int[]> recorrido = dijkstra.distancias(laberinto);

        // 4. Mostrar paso a paso
        dijkstra.mostrarPasoAPaso(recorrido, laberinto);

        // 5. Mostrar laberinto gráficamente en ventana
        LaberintoVista.mostrar(laberinto);
    }

    public static UndirectedGraph construirGrafo(char[][] laberinto) {
        int filas = laberinto.length;
        int columnas = laberinto[0].length;
        UndirectedGraph grafo = new UndirectedGraph(filas * columnas);

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (laberinto[i][j] != '█') {
                    int idActual = i * columnas + j;
                    int[][] vecinos = {{0, 1}, {1, 0}};
                    for (int[] dir : vecinos) {
                        int ni = i + dir[0];
                        int nj = j + dir[1];
                        if (ni >= 0 && ni < filas && nj >= 0 && nj < columnas && laberinto[ni][nj] != '█') {
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
