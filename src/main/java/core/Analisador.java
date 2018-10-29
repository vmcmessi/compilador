package core;

import gals.*;
import tela.tela;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analisador {

    private int ID_INICIO_PALAVRA_RESERVADA = 7;
    private int ID_FIM_PALAVRA_RESERVADA = 22;
    private int ID_INICIO_SIMBOLO_ESPECIAL = 23;
    private int ID_FIM_SIMBOLO_ESPECIAL = 41;


    private static JTextArea jMensagens;

    public Analisador(JTextArea jMensagens) {
        Analisador.jMensagens = jMensagens;
    }

    public void analisar() {
        final Lexico lexico = new Lexico();
        final Sintatico sintatico = new Sintatico();
        final Semantico semantico = new Semantico();

        lexico.setInput(tela.getCodigo());

        try {
            MensagemSucesso mensagemSucesso = new MensagemSucesso();
            sintatico.parse(lexico, semantico);

            jMensagens.setText(mensagemSucesso.getMensagem());
        } catch (LexicalError e) {
            jMensagens.setText(MensagemErro.getMensagemLexico(e.getPosition(), e.getMessage()));
        } catch (SyntaticError e) {
            jMensagens.setText(MensagemErro.getMensagemSintatico(e.getToken(), e.getMessage()));
        } catch (SemanticError semanticError) {
            //ignore
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
            return "Programa compilado com sucesso";
        }

        String getMensagemTabela() {
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

    private static class MensagemErro {

        private static int getLinha(int posicao) {
            Pattern compile = Pattern.compile("\r\n|\n");
            Matcher matcher = compile.matcher(tela.getCodigo().substring(0, posicao));
            int lines = 1;
            while (matcher.find())
                lines++;

            return lines;
        }

        static String getMensagemLexico(int position, String message) {
            final String tratar = TratamentoErro.tratarLexico(message, position);

            return String.format("Erro na linha %d - %s",
                    getLinha(position),
                    tratar
            );
        }

        static String getMensagemSintatico(Token token, String message) {
            final String tratar = TratamentoErro.tratarSintatico(message, token);

            return String.format("Erro na linha %d - %s",
                    getLinha(token.getPosition()),
                    tratar
            );
        }

    }
}
