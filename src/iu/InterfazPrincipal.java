package iu;

import iu.componentes.PanelDibujo;
import neural.Entrenamiento;

import javax.swing.*;
import java.awt.*;

public class InterfazPrincipal extends JFrame {
    private final int RESOLUCION = 20;

    private Entrenamiento entrenamiento;

    private JPanel panelPrincipal;
    private PanelDibujo panelDibujo;

    private JSpinner spinnerCantidad;
    private JButton btnEntrenar;
    private JButton btnPredecir;
    private JButton btnBorrar;
    private JComboBox<String> comboBoxLetras;
    private JButton btnEntrenarXVeces;

    public static void main(String[] args) {
        new InterfazPrincipal();
    }

    public InterfazPrincipal() {
        super("OCR");

        entrenamiento = new Entrenamiento();

        setPanelPrincipal();
        setPanelCentro();
        setBottomSide();

        setClicks();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setSize(new Dimension(1000, 485));
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void setPanelPrincipal() {
        panelPrincipal = new JPanel();
        panelPrincipal.setBackground(Color.LIGHT_GRAY);
        setContentPane(panelPrincipal);
    }

    private void setPanelCentro() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setPreferredSize(new Dimension(410, 410));

        panelDibujo = new PanelDibujo(400, 400, RESOLUCION);

        panel.add(panelDibujo);

        panelPrincipal.add(panel, BorderLayout.CENTER);
    }

    private void setBottomSide() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(1200, 36));

        spinnerCantidad = new JSpinner();
        setSize(spinnerCantidad);

        btnEntrenarXVeces = new JButton("Entrenar X Veces");
        setSize(btnEntrenarXVeces);
        btnEntrenarXVeces.setBackground(Color.WHITE);

        btnPredecir = new JButton("Predecir letra");
        setSize(btnPredecir);
        btnPredecir.setBackground(Color.WHITE);

        btnBorrar = new JButton("Borrar");
        setSize(btnBorrar);
        btnBorrar.setBackground(Color.WHITE);

        comboBoxLetras = new JComboBox<>();
        setSize(comboBoxLetras);
        comboBoxLetras.setBackground(Color.WHITE);

        btnEntrenar = new JButton("Entrenar como letra");
        setSize(btnEntrenar);
        btnEntrenar.setBackground(Color.WHITE);

        panel.add(spinnerCantidad);
        panel.add(btnEntrenarXVeces);
        panel.add(btnPredecir);
        panel.add(btnBorrar);
        panel.add(comboBoxLetras);
        panel.add(btnEntrenar);

        panelPrincipal.add(panel, BorderLayout.SOUTH);
    }

    private void setClicks() {

    }

    private void setSize(JComponent component) {
        Dimension prefSize = component.getPreferredSize();
        prefSize = new Dimension(150, 25);
        component.setPreferredSize(prefSize);
    }
}
