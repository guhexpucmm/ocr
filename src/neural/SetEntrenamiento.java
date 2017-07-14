package neural;

import java.util.ArrayList;

public class SetEntrenamiento {
    private ArrayList<Integer> entradas;
    private ArrayList<Double> buenasSalidas;

    public SetEntrenamiento(ArrayList<Integer> entradas, ArrayList<Double> buenasSalidas) {
        this.entradas = entradas;
        this.buenasSalidas = buenasSalidas;
    }

    public ArrayList<Integer> getEntradas() {
        return entradas;
    }

    public ArrayList<Double> getBuenasSalidas() {
        return buenasSalidas;
    }
}
