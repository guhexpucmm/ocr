package neural;

import java.util.ArrayList;

public class Entrenamiento {
    private static final int CONTADOR_NEURONAS = 26;

    private Conexion conexion;
    private ArrayList<SetEntrenamiento> setEntrenamientos;

    public Entrenamiento() {
        this.conexion = new Conexion();
        this.conexion.agregarNeuronas(CONTADOR_NEURONAS);
        //this.setEntrenamientos = LeerEscribirArchivos.
    }

    public void entrenar(ArrayList<Integer> entradas) {
        conexion.setEntradas(entradas);
    }

    public void agregarSetEntrenamiento(SetEntrenamiento setEntrenamiento) {
        setEntrenamientos.add(setEntrenamiento);
    }

    public ArrayList<Double> getSalidas() {
        return conexion.getSalidas();
    }
}
