package core;

import gals.Token;
import tela.tela;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TratamentoErro {

    static String tratarLexico(String msg, int posicao) {
        if (msg.equalsIgnoreCase("símbolo inválido")
                || msg.equalsIgnoreCase("palavra reservada inválida")) {
            msg = getSimbolo(posicao, true) + " " + msg;
        }

        return msg;
    }

    static String tratarSintatico(String msg, Token token) {
        if (token.getLexeme().equalsIgnoreCase("$")) {
            return "encontrado fim de programa " + msg;
        }

        switch (token.getId()){
            case 2:
                return getSimbolo(token.getPosition(), false) + " palavra reservada inválida";
            case 4:
                return "encontrado constante numérica " + msg;
            case 5:
                return "encontrado constante caractere " + msg;
            case 6:
                return "encontrado constante literal " + msg;

            default: return "encontrado " + getSimbolo(token.getPosition(), false) + " " + msg;
        }
    }

    private static String getSimbolo(int posicao, boolean exact) {
        final String codigo = tela.getCodigo();

        if(exact){
            return String.valueOf(codigo.charAt(posicao));
        }

        Pattern compile = Pattern.compile("\\S\\w*");
        Matcher matcher = compile.matcher(codigo.substring(posicao));

        if (matcher.find()) {
            return matcher.group();
        }

        return "";
    }

}