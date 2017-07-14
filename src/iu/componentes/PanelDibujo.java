package iu.componentes;

import iu.componentes.data.Seccion;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class PanelDibujo extends PanelCustomizado implements MouseMotionListener, MouseListener{

    public PanelDibujo(int w, int h, int contador) {
        super(w, h, contador);

        addMouseMotionListener(this);
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        pintarSelecciones(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        pintarSelecciones(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    private void pintarSelecciones(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            for (Seccion s : secciones) {
                if (e.getX() > s.getX() && e.getX() < s.getX() + s.getW() && e.getY() > s.getY() && e.getY() < s.getY() + s.getH()) {
                    s.setActivo(true);

                }
            }
        } else if (SwingUtilities.isRightMouseButton(e)) {
            for (Seccion s : secciones) {
                if (e.getX() > s.getX() && e.getX() < s.getX() + s.getW() && e.getY() > s.getY() && e.getY() < s.getY() + s.getH())
                    s.setActivo(false);
            }
        }

        repaint();
    }
}
