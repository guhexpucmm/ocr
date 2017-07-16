package iu.componentes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelSiNo extends JDialog implements ActionListener {

    private String titulo;
    private String header;
    private String contenido;
    private JButton btnSi;
    private JButton btnNo;

    public PanelSiNo(Frame parent, String titulo, String header, String contenido) {
        super(parent, titulo, true);

        this.titulo = titulo;
        this.header = header;
        this.contenido = contenido;


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
