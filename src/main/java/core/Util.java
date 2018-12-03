package core;

public class Util {

    public static boolean isTipoNumerico(String value){
        return value.equals(GeradorCodigo.FLOAT64) || value.equals(GeradorCodigo.INT64);
    }

    public static boolean isBooleano(String value){
        return value.equals(GeradorCodigo.TRUE) || value.equals(GeradorCodigo.FALSE);
    }

}
