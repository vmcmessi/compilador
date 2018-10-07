package core;

import gals.*;

import javax.swing.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalisadorLexico {

    private int ID_INICIO_PALAVRA_RESERVADA = 7;
    private int ID_FIM_PALAVRA_RESERVADA = 22;
    private int ID_INICIO_SIMBOLO_ESPECIAL = 23;
    private int ID_FIM_SIMBOLO_ESPECIAL = 41;


    private final JTextArea jMensagens;

    public AnalisadorLexico(JTextArea jMensagens) {
        this.jMensagens = jMensagens;
    }

    private int getLinha(String codigo, int posicao) {
        Pattern compile = Pattern.compile("\r\n|\n");
        Matcher matcher = compile.matcher(codigo.substring(0, posicao));
        int lines = 1;
        while (matcher.find())
            lines++;

        return lines;
    }

    public void analisar(String codigo) {
        final Lexico lexico = new Lexico();
        lexico.setInput(codigo);

        final ArrayList<String> palavrasReservadasInvalidas = new ArrayList<>();
        for (String palavrasReservada : ScannerConstants.SPECIAL_CASES_KEYS) {
            palavrasReservadasInvalidas.add(palavrasReservada.toLowerCase());
        }

        Token token;
        try {
            MensagemSucesso mensagemSucesso = new MensagemSucesso();

            while ((token = lexico.nextToken()) != null) {
                if (palavrasReservadasInvalidas.contains(token.getLexeme()) && !(token.getId() >= ID_INICIO_PALAVRA_RESERVADA && token.getId() <= ID_FIM_PALAVRA_RESERVADA)) {
                    throw new LexicalError("palavra reservada inválida", token.getPosition());
                }

                int linha = getLinha(codigo, token.getPosition());
                mensagemSucesso.add(linha, getClasseById(token.getId()), token.getLexeme());
            }

            jMensagens.setText(mensagemSucesso.getMensagem());


        } catch (LexicalError e) {
            //todo: Pegar todo o nome do identificador com problema

            jMensagens.setText(String.format("Erro na linha %d - %s",
                    getLinha(codigo, e.getPosition()),
                    e.getMessage()
            ));
        }
    }

    private static class MensagemSucesso {

        private List<Integer> linha = new LinkedList<>();
        private List<String> classe = new LinkedList<>();
        private List<String> lexema = new LinkedList<>();

        private int position = 0;

        void add(int linha, String classe, String lexema) {
            this.linha.add(position, linha);
            this.classe.add(position, classe);
            this.lexema.add(position, lexema);

            position++;
        }

        String getMensagem() {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder
                    .append("Linha")
                    .append('\t')
                    .append("Classe")
                    .append('\t')
                    .append("Lexema")
                    .append('\n');

            for (int i = 0; i < position; i++) {
                stringBuilder
                        .append(this.linha.get(i))
                        .append('\t')
                        .append(this.classe.get(i))
                        .append('\t')
                        .append(this.lexema.get(i))
                        .append('\n');
            }

            return stringBuilder.toString();
        }

    }

    private String getClasseById(int id) {

        if (id == Constants.t_palavra) {
            return "palavra";
        }

        if (id == Constants.t_id) {
            return "identificador";
        }

        if (id == Constants.t_conliteral) {
            return "constante literal";
        }

        if (id == Constants.t_concar) {
            return "constante caractere";
        }
        if (id == Constants.t_connum) {
            return "constante numérica";
        }

        if (id >= ID_INICIO_PALAVRA_RESERVADA && id <= ID_FIM_PALAVRA_RESERVADA) {
            return "palavra reservada";
        }

        if (id >= ID_INICIO_SIMBOLO_ESPECIAL && id <= ID_FIM_SIMBOLO_ESPECIAL) {
            return "símbolo especial";
        }

        return null;
    }
}
