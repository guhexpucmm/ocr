package data;

import neural.SetEntrenamiento;

import java.io.*;
import java.util.ArrayList;

public class LeerEscribirArchivos {
    public static ArrayList<SetEntrenamiento> leerSetEntrenamiento() {
        ArrayList<SetEntrenamiento> trainingSets = new ArrayList<>();

        for (int i = 0; i < 26; i++) {
            char letterValue = (char) (i + 65);
            String letter = String.valueOf(letterValue);
            for (ArrayList<Integer> list : leerArchivo("/" + letter + ".txt")) {
                trainingSets.add(new SetEntrenamiento(list, SalidasValidas.getInstance().getSalidasValidas(letter)));
            }
        }

        return trainingSets;
    }

    private static ArrayList<ArrayList<Integer>> leerArchivo(String filename) {
        ArrayList<ArrayList<Integer>> inputs = new ArrayList<>();

        try {
            InputStream in = LeerEscribirArchivos.class.getClass().getResourceAsStream(filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                ArrayList<Integer> input = new ArrayList<>();
                for (int i = 0; i < line.length(); i++) {
                    int value = 0;
                    try {
                        value = Integer.parseInt(String.valueOf(line.charAt(i)));
                    } catch (Exception e) {
                    }
                    input.add(value);
                }
                inputs.add(input);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return inputs;
    }

    public static void guardarEnArchivo(ArrayList<Integer> input, String filename) {
        try {
            File file = new File("resources/" + filename + ".txt");
            File file2 = new File("resources/" + filename + ".arff");

            PrintWriter pw = new PrintWriter(new FileOutputStream(file, true));
            PrintWriter pa = new PrintWriter(new FileOutputStream(file2, true));

            int cont = 0;

            for (Integer i : input) {
                pw.write(Integer.toString(i));
                pa.write(Integer.toString(i));
                if(cont % 20 == 0) pa.write(",");
                cont++;
            }
            pa.write(filename);
            pw.write("\n");
            pa.write("\n");

            pw.close();
            pa.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
