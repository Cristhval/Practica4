package org.unl.music.base.controller.data_struct.grafos;

import javax.swing.*;
import java.awt.*;

public class LaberintoVista extends JPanel {
    private final char[][] laberinto;
    private final int TAM_BLOQUE = 20;

    public LaberintoVista(char[][] laberinto) {
        this.laberinto = laberinto;
        setPreferredSize(new Dimension(laberinto[0].length * TAM_BLOQUE, laberinto.length * TAM_BLOQUE));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < laberinto.length; i++) {
            for (int j = 0; j < laberinto[0].length; j++) {
                char c = laberinto[i][j];
                switch (c) {
                    case '█' -> g.setColor(Color.BLACK);// muro
                    case 'S' -> g.setColor(Color.GREEN);// inicio
                    case 'E' -> g.setColor(Color.RED);// fin
                    case '.' -> g.setColor(Color.CYAN);// camino
                    default -> g.setColor(Color.WHITE);// espacio libre
                }
                g.fillRect(j * TAM_BLOQUE, i * TAM_BLOQUE, TAM_BLOQUE, TAM_BLOQUE);
                g.setColor(Color.GRAY);
                g.drawRect(j * TAM_BLOQUE, i * TAM_BLOQUE, TAM_BLOQUE, TAM_BLOQUE);
            }
        }
    }


    public static LaberintoVista mostrar(char[][] laberinto) {
        JFrame frame = new JFrame("Laberinto Visual");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        LaberintoVista panel = new LaberintoVista(laberinto);
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(800, 600));

        frame.getContentPane().add(scrollPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        return panel;
    }
}
