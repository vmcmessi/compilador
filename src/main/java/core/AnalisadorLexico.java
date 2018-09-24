package core;

import gals.LexicalError;
import gals.Lexico;
import gals.ScannerConstants;
import gals.Token;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class AnalisadorLexico {

    private final JTextArea jMensagens;
    private final char[] specialChars = new char[]{'\n', '\t', ' '};


    public AnalisadorLexico(JTextArea jMensagens) {
        this.jMensagens = jMensagens;
    }

    private int getLinha(String codigo, int posicao) {
        String[] lines = codigo.split("\r\n|\n", posicao);
        return lines.length;
    }

    public void analisar(String codigo) {
        final Lexico lexico = new Lexico();
        lexico.setInput(codigo);

        final ArrayList<String> palavrasReservadasInvalidas = new ArrayList<>();
        for (String palavrasReservada : ScannerConstants.SPECIAL_CASES_KEYS) {
            palavrasReservadasInvalidas.add(palavrasReservada.toLowerCase());
        }

        Token token = null;
        try {

            while ((token = lexico.nextToken()) != null) {
                if (palavrasReservadasInvalidas.contains(token.getLexeme()) && !(token.getId() >= 7 && token.getId() <= 21)) {
                    throw new LexicalError("palavra reservada inválida", token.getPosition());
                }


                System.out.println(token.getLexeme());
            }

            jMensagens.setText("Compilado com sucesso");
        } catch (LexicalError e) {
            //todo: Pegar todo o nome do identificador com problema
            final String[] split = codigo.split("[^\\w+$]+", e.getPosition());
            jMensagens.setText(String.format("Erro na linha %d - %s %s",
                    getLinha(codigo, e.getPosition()),
                    split[split.length - 1].substring(e.getPosition()),
                    e.getMessage()
            ));
        }
    }

}
