package iu;

import data.LeerEscribirArchivos;
import data.SalidasValidas;
import iu.componentes.PanelDibujo;
import neural.Entrenamiento;
import neural.SetEntrenamiento;
import util.LetraUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.util.ArrayList;

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

        inicializandoComponentes();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setSize(new Dimension(1200, 485));
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
        spinnerCantidad.setModel(new SpinnerNumberModel(0, 0, Double.MAX_VALUE, 1));
        setSize(spinnerCantidad);

        btnEntrenarXVeces = new JButton("Entrenar X veces");
        setSize(btnEntrenarXVeces);
        btnEntrenarXVeces.setBackground(Color.WHITE);

        btnPredecir = new JButton("Predecir letra");
        setSize(btnPredecir);
        btnPredecir.setBackground(Color.WHITE);

        btnBorrar = new JButton("Borrar");
        setSize(btnBorrar);
        btnBorrar.setBackground(Color.WHITE);

        comboBoxLetras = new JComboBox<>();
        for (int i = 0; i < LetraUtil.letras.size(); i++) {
            comboBoxLetras.addItem(LetraUtil.letras.get(i));
        }
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
        spinnerCantidad.addChangeListener(e -> {
            btnEntrenarXVeces.setText("Entrenar " + spinnerCantidad.getModel().getValue() + " veces");
        });

        spinnerCantidad.addInputMethodListener(new InputMethodListener() {
            @Override
            public void inputMethodTextChanged(InputMethodEvent event) {
                btnEntrenarXVeces.setText("Entrenar " + spinnerCantidad.getModel().getValue() + " veces");
            }

            @Override
            public void caretPositionChanged(InputMethodEvent event) {
                btnEntrenarXVeces.setText("Entrenar " + spinnerCantidad.getModel().getValue() + " veces");
            }
        });

        btnEntrenarXVeces.addActionListener(e -> {
            Number numero = (Number) spinnerCantidad.getModel().getValue();

            entrenamiento.entrenar(numero.intValue());

            JOptionPane.showMessageDialog(panelPrincipal, "Modelo entrenado " + spinnerCantidad.getModel().getValue() + " veces!");
        });

        btnPredecir.addActionListener(e -> {
            entrenamiento.setEntradas(panelDibujo.getPixeles());

            ArrayList<Double> salidas = entrenamiento.getSalidas();
            int index = 0;

            for (int i = 0; i < salidas.size(); i++) {
                if(salidas.get(i) > salidas.get(index)) {
                    index = i;
                }
            }

            actualizarPredicciones();

            System.out.println("" + (char) index + 65);
        });

        btnBorrar.addActionListener(e -> {
            panelDibujo.clear();
        });

        comboBoxLetras.addActionListener(e -> {
            btnEntrenar.setText("Entrenar como letra " + comboBoxLetras.getSelectedItem().toString());
        });

        btnEntrenar.addActionListener(e -> {
            String letra = (String) comboBoxLetras.getSelectedItem();

            entrenamiento.agregarSetEntrenamiento(new SetEntrenamiento(panelDibujo.getPixeles(), SalidasValidas.getInstance().getSalidasValidas(letra)));

            LeerEscribirArchivos.guardarEnArchivo(panelDibujo.getPixeles(), letra);

            JOptionPane.showMessageDialog(this, "Dibujo entrenado como letra " + letra, "Entrenando como letra " + letra, JOptionPane.PLAIN_MESSAGE);

            panelDibujo.clear();
        });
    }

    private void inicializandoComponentes() {
        btnEntrenarXVeces.setText("Entrenar " + spinnerCantidad.getModel().getValue() + " veces");
        btnEntrenar.setText("Entrenar como letra " + comboBoxLetras.getSelectedItem().toString());
    }

    private void setSize(JComponent component) {
        Dimension prefSize = component.getPreferredSize();
        prefSize = new Dimension(180, 25);
        component.setPreferredSize(prefSize);
    }

    private void actualizarPredicciones() {
        StringBuilder sb = new StringBuilder();
        ArrayList<Double> outputs = entrenamiento.getSalidas();
        for (int i = 0; i < outputs.size(); i++) {
            int letterValue = i + 65;
            sb.append((char) letterValue);
            double value = outputs.get(i);
            if (value < 0.01)
                value = 0;
            if (value > 0.99)
                value = 1;

            value *= 1000;
            int x = (int) (value);
            value = x / 1000.0;

            sb.append("\t " + value);
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
