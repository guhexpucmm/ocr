package neural;

import java.util.ArrayList;

public class Conexion {
    private ArrayList<Neurona> neuronas;

    public Conexion() {
        neuronas = new ArrayList<>();
    }

    public void agregarNeuronas(int count) {
        for (int i = 0; i < count; i++) {
            neuronas.add(new Neurona());
        }
    }

    public void setEntradas(ArrayList<Integer> entradas) {
        for(Neurona neurona : neuronas) {
            neurona.setEntradas(entradas);
        }
    }

    public ArrayList<Double> getSalidas() {
        ArrayList<Double> salidas = new ArrayList<>();

        for(Neurona neurona : neuronas) {
            salidas.add(neurona.getSalida());
        }

        return salidas;
    }

    public void ajustarSalario(ArrayList<Integer> buenasSalidas) {
        for (int i = 0; i < neuronas.size(); i++) {
            double delta = buenasSalidas.get(i) - neuronas.get(i).getSalida();

            neuronas.get(i).ajustarPeso(delta);
        }
    }
}
