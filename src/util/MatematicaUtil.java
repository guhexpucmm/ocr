package util;

public class MatematicaUtil {
    public static double valorSigmoid(Double arg) {
        return (1 / (1 + Math.exp(-arg)));
    }
}