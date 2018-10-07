package core;

import gals.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AnalisadorLexico {

    private final JTextArea jMensagens;

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

        Token token;
        try {
            MensagemSucesso mensagemSucesso = new MensagemSucesso();

            while ((token = lexico.nextToken()) != null) {
                if (palavrasReservadasInvalidas.contains(token.getLexeme()) && !(token.getId() >= 7 && token.getId() <= 21)) {
                    throw new LexicalError("palavra reservada inválida", token.getPosition());
                }

                mensagemSucesso.add(getLinha(codigo, token.getPosition()), getClasseById(token.getId()), token.getLexeme());
            }

            jMensagens.append(mensagemSucesso.getMensagem());


        } catch (LexicalError e) {
            //todo: Pegar todo o nome do identificador com problema


            System.out.println(e.getPosition());
            System.out.println(e.getMessage());

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
                    .append('\t')
                    .append('\t')
                    .append("Lexema")
                    .append('\n');

            for (int i = 0; i < position; i++) {
                stringBuilder
                        .append(this.linha.get(i))
                        .append('\t')
                        .append(this.classe.get(i))
                        .append('\t')
                        .append('\t')
                        .append('\t')
                        .append(this.lexema.get(i))
                        .append('\n');
            }

            return stringBuilder.toString();
        }

    }

    private String getClasseById(int id) {

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

        if (id >= 7 && id <= 21) {
            return "palavra reservada";
        }

        if (id >= 22 && id <= 40) {
            return "símbolo especial";
        }

        return null;
    }
}
