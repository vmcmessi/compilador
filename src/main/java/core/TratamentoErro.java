package core;

import tela.tela;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TratamentoErro {

    static String tratar(String msg, int posicao) {
        if (msg.equalsIgnoreCase("símbolo inválido")
                || msg.equalsIgnoreCase("palavra reservada inválida")) {
            msg = getSimbolo(posicao) + " " + msg;
        }

        return msg;
    }

    private static String getSimbolo(int posicao) {
        final String codigo = tela.getCodigo();

        Pattern compile = Pattern.compile("\\S\\w*");
        Matcher matcher = compile.matcher(codigo.substring(posicao));

        if (matcher.find()) {
            return matcher.group();
        }

        return "";
    }

}