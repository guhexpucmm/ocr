package neural;

import util.MatematicaUtil;

import java.util.ArrayList;

public class Neurona {
    private static final int BIAS = 1;
    private static final double RATIO_APRENDIZAJE = 0.1;

    private ArrayList<Integer> entradas;
    private ArrayList<Double> pesos;
    private double biasPeso;
    private double salida;

    public Neurona() {
        this.entradas = new ArrayList<>();
        this.pesos = new ArrayList<>();
        this.biasPeso = Math.random();
    }

    public void setEntradas(ArrayList<Integer> entradas) {
        if (this.entradas.size() == 0) {
            this.entradas = new ArrayList<>(entradas);
            generarPesos();
        }

        this.entradas = new ArrayList<>(entradas);
    }

    private void generarPesos() {
        for (int i = 0; i < entradas.size(); i++) {
            pesos.add(Math.random());
        }
    }

    public void calcularSalida() {
        double suma = 0;

        for (int i = 0; i < entradas.size(); i++) {
            suma += entradas.get(i) * pesos.get(i);
        }
        suma += BIAS * biasPeso;

        salida = MatematicaUtil.valorSigmoid(suma);
    }

    public void ajustarPeso(double delta) {
        for (int i = 0; i < entradas.size(); i++) {
            double d = pesos.get(i);
            d += RATIO_APRENDIZAJE * delta * entradas.get(i);
            pesos.set(i, d);
        }

        biasPeso += RATIO_APRENDIZAJE * delta * BIAS;
    }

    public double getSalida() {
        calcularSalida();

        return salida;
    }
}
