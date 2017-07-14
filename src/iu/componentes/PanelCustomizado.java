package iu.componentes;

import iu.componentes.data.Seccion;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelCustomizado extends JPanel {
    protected ArrayList<Seccion> secciones;
    private int w;
    private int h;
    private int contador;

    public PanelCustomizado(int w, int h, int contador) {
        this.w = w;
        this.h = h;
        this.contador = contador;

        setPreferredSize(new Dimension(w, h));
        setBackground(Color.WHITE);

        generarSecciones();
    }

    private void generarSecciones() {
        secciones = new ArrayList<>();

        for (int i = 0; i < contador; i++) {
            for (int j = 0; j < contador; j++) {
                secciones.add(new Seccion(i * (w / contador), j * (h / contador), w / contador, h / contador));
            }
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        generarSecciones(g);
        dibujarSecciones(g);
    }

    private void generarSecciones(Graphics g) {
        g.setColor(Color.BLACK);

        for(Seccion seccion : secciones) {
            g.drawLine(0, seccion.getY(), w, seccion.getY());
            g.drawLine(seccion.getX(), 0, seccion.getX(), h);
        }
    }

    private void dibujarSecciones(Graphics g) {
        g.setColor(Color.BLACK);

        for(Seccion seccion : secciones) {
            if (seccion.isActivo())
                g.fillRect(seccion.getX(), seccion.getY(), seccion.getW(), seccion.getH());
        }
    }

    public ArrayList<Integer> getPixeles() {
        ArrayList<Integer> pixeles = new ArrayList<>();

        for(Seccion seccion : secciones) {
            if (seccion.isActivo())
                pixeles.add(1);
            else
                pixeles.add(0);
        }

        return pixeles;
    }

    public void clear() {
        for (Seccion seccion : secciones) {
            seccion.setActivo(false);
        }

        repaint();
    }

    public void dibujarLetra(ArrayList<Integer> pixeles) {
        for (int i = 0; i < pixeles.size(); i++) {
            if (pixeles.get(i) == 1)
                secciones.get(i).setActivo(true);
            else
                secciones.get(i).setActivo(false);
        }

        repaint();
    }

    public ArrayList<Seccion> getSecciones() {
        return secciones;
    }
}
