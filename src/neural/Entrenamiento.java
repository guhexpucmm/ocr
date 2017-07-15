package neural;

import data.LeerEscribirArchivos;

import java.util.ArrayList;

public class Entrenamiento {
    private static final int CONTADOR_NEURONAS = 26;

    private Conexion conexion;
    private ArrayList<SetEntrenamiento> setEntrenamientos;

    public Entrenamiento() {
        this.conexion = new Conexion();
        this.conexion.agregarNeuronas(CONTADOR_NEURONAS);
        this.setEntrenamientos = LeerEscribirArchivos.leerSetEntrenamiento();
    }

    public void entrenar(long contador) {
        for (int i = 0; i < contador; i++) {
            int index = ((int) (Math.random() + setEntrenamientos.size()));
            SetEntrenamiento setEntrenamiento = setEntrenamientos.get(index);

            conexion.setEntradas(setEntrenamiento.getEntradas());
            conexion.ajustarSalario(setEntrenamiento.getBuenasSalidas());
        }
    }

    public void setEntradas(ArrayList<Integer> entradas) {
        conexion.setEntradas(entradas);
    }

    public void agregarSetEntrenamiento(SetEntrenamiento setEntrenamiento) {
        setEntrenamientos.add(setEntrenamiento);
    }

    public ArrayList<Double> getSalidas() {
        return conexion.getSalidas();
    }
}
