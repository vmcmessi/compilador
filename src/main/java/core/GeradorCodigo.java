package core;

import gals.SemanticError;
import gals.Token;
import tela.tela;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class GeradorCodigo {

    private static GeradorCodigo instance;

    private Stack<String> pilha;
    private Queue<String> codigo;
    private Queue<String> listaIdentificadores;
    private Map<String, String> tabelaSimbolos;
    private int indexLabel = 1;
    private String operador;
    private String tipovar;

    //
    public static final String FLOAT64 = "float64";
    public static final String INT64 = "int64";
    public static final String STRING = "string";
    public static final String BOOL = "boolean";
    public static final String TRUE = "true";
    public static final String FALSE = "false";

    private GeradorCodigo() {
        pilha = new Stack<>();
        codigo = new LinkedList<>();
        listaIdentificadores = new LinkedList<>();
        tabelaSimbolos = new HashMap<>();
    }

    private void clearAll() {
        pilha.clear();
        codigo.clear();
        listaIdentificadores.clear();
        tabelaSimbolos.clear();
        indexLabel = 1;
        operador = null;
        tipovar = null;
    }

    public StringBuilder gerarCodigo() {
        final StringBuilder stringBuilder = new StringBuilder();

        final int size = codigo.size();
        for (int i = 0; i < size; i++) {
            final String poll = codigo.poll();
            stringBuilder.append(poll);

            if (size != (i + 1)) {
                stringBuilder.append('\n');
            }
        }

        return stringBuilder;
    }

    public void writeToFile() throws IOException {
        Path file = Paths.get(String.format("./%s.il", tela.getFilenameOpened().split("\\.")[0]));
        Files.write(file, codigo, Charset.forName("UTF-8"));
        clearAll();
    }

    public void executeAction(int action, Token token) throws SemanticError {
        try {
            switch (action) {
                case 1:
                    somar();
                    break;
                case 2:
                    subtrair();
                    break;
                case 3:
                    multiplicar();
                    break;
                case 4:
                    dividir();
                    break;
                case 5:
                    empilhaInt64(token);
                    break;
                case 6:
                    empilhaFloat64(token);
                    break;
                case 7:
                    verificarTipoSoma();
                    break;
                case 8:
                    verificarTipoSubtracao();
                    break;
                case 9:
                    this.operador = token.getLexeme();
                    break;
                case 10:
                    relacional();
                    break;
                case 11:
                    empilhaTrue();
                    break;
                case 12:
                    empilhaFalse();
                    break;
                case 13:
                    not();
                    break;
                case 14:
                    write();
                    break;
                case 15:
                    inicioCodigo();
                    break;
                case 16:
                    fimCodigo();
                    break;
                case 17:
                    and();
                    break;
                case 18:
                    or();
                    break;
                case 19:
                    constanteCaractere(token);
                    break;
                case 20:
                    constanteLiteral(token);
                    break;
                case 101:
                    setTipoVar(token);
                    break;
                case 102:
                    listaIdentificadores.add(token.getLexeme());
                    break;
                case 103:
                    declararVariaveis();
                    break;
                case 104:
                    read();
                    break;
                case 105:
                    empilhaFatorExpressao(token);
                    break;
                case 106:
                    atribuicao();
                    break;
                case 107:
                    desviaFalse();
                    break;
                case 108:
                    desvia();
                    break;
                case 109:
                    geraLabelDesvio();
                    break;
                case 110:
                    desvia2();
                    break;
                case 111:
                    desviaTrue();
                    break;
                case 112:
                    geraLabelDesvio2();
                    break;
            }
        } catch (SemanticError semanticError) {
            throw new SemanticError(semanticError.getMessage(), token.getPosition());
        }
    }

    /**
     * @return GeradorCodigo
     */
    public static GeradorCodigo getInstance() {
        if (instance == null) instance = new GeradorCodigo();
        return instance;
    }


    public static void release() {
        instance = null;
    }

    /**
     * Empilha token
     */
    public void empilha(String value) {
        this.pilha.push(value);
    }

    /**
     * Desempilha token
     */
    public String desempilha() {
        return this.pilha.pop();
    }

    /**
     * Adiciona codigo na lista
     */
    public void addCodigo(String instruction) {
        this.codigo.add(instruction);
    }

    /**
     * Adiciona codigo na lista formatado
     */
    public void addCodigo(String instruction, Object... args) {
        this.codigo.add(String.format(instruction, args));
    }

    //1
    public void somar() throws SemanticError {
        String tipo1 = this.desempilha();
        String tipo2 = this.desempilha();

        if (!Util.isTipoNumerico(tipo1) || !Util.isTipoNumerico(tipo2)) {
            throw new SemanticError("Tipos incompatíveis em operação aritmética binária");
        }

        if (tipo1.equals(FLOAT64) || tipo2.equals(FLOAT64)) {
            this.empilha(FLOAT64);
        } else {
            this.empilha(INT64);
        }

        this.addCodigo("add");
    }

    //2
    public void subtrair() throws SemanticError {
        String tipo1 = this.desempilha();
        String tipo2 = this.desempilha();

        if (!Util.isTipoNumerico(tipo1) || !Util.isTipoNumerico(tipo2)) {
            throw new SemanticError("Tipos incompatíveis em operação aritmética binária");
        }

        if (tipo1.equals(FLOAT64) || tipo2.equals(FLOAT64)) {
            this.empilha(FLOAT64);
        } else {
            this.empilha(INT64);
        }

        this.addCodigo("sub");
    }

    //3
    public void multiplicar() throws SemanticError {
        String tipo1 = this.desempilha();
        String tipo2 = this.desempilha();

        if (!Util.isTipoNumerico(tipo1) || !Util.isTipoNumerico(tipo2)) {
            throw new SemanticError("Tipos incompatíveis em operação aritmética binária");
        }

        if (tipo1.equals(FLOAT64) || tipo2.equals(FLOAT64)) {
            this.empilha(FLOAT64);
        } else {
            this.empilha(INT64);
        }

        this.addCodigo("mul");
    }

    //4
    public void dividir() throws SemanticError {
        String tipo1 = this.desempilha();
        String tipo2 = this.desempilha();

        if (!Util.isTipoNumerico(tipo1)
                || !Util.isTipoNumerico(tipo2)
                || !tipo1.equals(tipo2)
        ) {
            throw new SemanticError("Tipos incompatíveis em operação aritmética binária");
        }

        this.empilha(tipo1);
        this.addCodigo("div");
    }

    //5
    public void empilhaInt64(Token token) {
        this.empilha(INT64);
        this.addCodigo("ldc.i8 %s", token.getLexeme());
        this.addCodigo("conv.r8");
    }

    //6
    public void empilhaFloat64(Token token) {
        this.empilha(FLOAT64);
        this.addCodigo("ldc.r8 %s", token.getLexeme());
    }

    //7
    public void verificarTipoSoma() throws SemanticError {
        String tipo = this.desempilha();
        if (tipo.equals(FLOAT64) || tipo.equals(INT64)) {
            this.empilha(tipo);
        } else {
            throw new SemanticError("Tipo incompatível em operação aritmética unária");
        }
    }

    //8
    public void verificarTipoSubtracao() throws SemanticError {
        String tipo = this.desempilha();
        if (tipo.equals(FLOAT64) || tipo.equals(INT64)) {
            this.empilha(tipo);
        } else {
            throw new SemanticError("Tipo incompatível em operação aritmética unária");
        }

        this.addCodigo("ldc.i8 -1");
        this.addCodigo("conv.r8");
        this.addCodigo("mul");
    }

    //10
    public void relacional() throws SemanticError {
        String tipo1 = this.desempilha();
        String tipo2 = this.desempilha();
        if (tipo1.equals(tipo2)) {
            this.empilha(BOOL);
        } else {
            throw new SemanticError("Tipos incompatíveis em operação relacional");
        }

        switch (operador) {
            case ">":
                this.addCodigo("cgt");
                break;
            case "<":
                this.addCodigo("clt");
                break;
            case "=":
                this.addCodigo("ceq");
                break;
        }

    }

    //11
    public void empilhaFalse() {
        this.empilha("bool");
        this.addCodigo("ldc.i4.0");
    }

    //12
    public void empilhaTrue() {
        this.empilha("bool");
        this.addCodigo("ldc.i4.1");
    }

    //13
    public void not() throws SemanticError {
        String tipo = this.desempilha();

        if (tipo.equals(BOOL)) {
            this.empilha(BOOL);
        } else {
            throw new SemanticError("Tipo incompatível em operação lógica unária");
        }
        this.addCodigo("ldc.i4.1");
        this.addCodigo("xor");
    }

    //14
    public void write() {
        String tipo = this.desempilha();
        if (tipo.equals(INT64)) {
            this.addCodigo("conv.i8");
        }
        this.addCodigo("call void [mscorlib]System.Console::Write(%s)", tipo);
    }

    //15
    public void inicioCodigo() {
        this.addCodigo(".assembly extern mscorlib {}\n" +
                ".assembly _codigo_objeto{}\n" +
                ".module _codigo_objeto.exe\n" +
                "\n" +
                ".class public _UNICA{\n" +
                ".method static public void _PRINCIPAL(){\n" +
                ".entrypoint\n"
        );
    }

    //16
    public void fimCodigo() {
        this.addCodigo("ret\n" +
                "}\n" +
                "}");
    }

    //17
    public void and() throws SemanticError {
        String valor1 = this.desempilha();
        String valor2 = this.desempilha();

        if (!Util.isBooleano(valor1) || !Util.isBooleano(valor2)) {
            throw new SemanticError("Tipos incompatíveis em operação lógica binária");
        }

        if (valor1.equals(TRUE)
                && valor2.equals(TRUE)) {
            this.empilha(TRUE);
        } else {
            this.empilha(FALSE);
        }
    }

    //18
    public void or() throws SemanticError {
        String valor1 = this.desempilha();
        String valor2 = this.desempilha();

        if (!Util.isBooleano(valor1) || !Util.isBooleano(valor2)) {
            throw new SemanticError("Tipos incompatíveis em operação lógica binária");
        }

        if (valor1.equals(TRUE)
                || valor2.equals(TRUE)) {
            this.empilha(TRUE);
        } else {
            this.empilha(FALSE);
        }
    }

    //19
    public void constanteCaractere(Token token) throws SemanticError {
        String constCaractere;
        switch (token.getLexeme()) {
            case "'\\s'":
                constCaractere = " ";
                break;
            case "'\\t'":
                constCaractere = "\\t";
                break;
            case "'\\n'":
                constCaractere = "\\n";
                break;
            default:
                throw new SemanticError("Constante caractere inválida");
        }
        this.empilha(STRING);
        this.addCodigo("ldstr \"%s\"", constCaractere);
    }

    //20
    public void constanteLiteral(Token token) {
        this.empilha(STRING);
        this.addCodigo("ldstr %s", token.getLexeme());
    }

    //101
    public void setTipoVar(Token token) {

        switch (token.getLexeme()) {
            case "number":
                tipovar = FLOAT64;
                break;
            case "logical":
                tipovar = BOOL;
                break;
            case "literal":
                tipovar = STRING;
                break;
        }

    }

    //103
    public void declararVariaveis() throws SemanticError {
        for (String id : listaIdentificadores) {
            if (tabelaSimbolos.containsKey(id)) {
                throw new SemanticError("Identificador já declarado");
            }

            tabelaSimbolos.put(id, tipovar);
            addCodigo(".locals(%s %s)", tipovar, id);
        }

        listaIdentificadores.clear();
    }

    //104
    public void read() throws SemanticError {
        for (String id : listaIdentificadores) {

            if (!tabelaSimbolos.containsKey(id)) {
                throw new SemanticError("Identificador não declarado");
            }

            String tipoId = tabelaSimbolos.get(id);
            String classe;
            switch (tipoId) {
                case "float64":
                    classe = "Double";
                    break;
                case "bool":
                    classe = "Boolean";
                    break;
                case "int64":
                    classe = "Int64";
                    break;
                case "string":
                    classe = "String";
                    break;
                default:
                    throw new SemanticError("");
            }


            this.addCodigo("call string [mscorlib]System.Console::ReadLine()");
            if (!tipoId.equals(STRING)) {
                this.addCodigo("call %s [mscorlib]System.%s::Parse(string)", tipoId, classe);
            }
            this.addCodigo("stloc %s", id);
        }

        listaIdentificadores.clear();
    }

    //105
    public void empilhaFatorExpressao(Token token) throws SemanticError {
        String id = token.getLexeme();
        if (!tabelaSimbolos.containsKey(id)) {
            throw new SemanticError("Identificador não declarado");
        }

        String tipoId = tabelaSimbolos.get(id);
        this.empilha(tipoId);
        this.addCodigo("ldloc %s", id);
        if (tipoId.equals(INT64)) {
            this.addCodigo("conv.r8");
        }
    }

    //106
    public void atribuicao() throws SemanticError {
        String id = listaIdentificadores.poll();

        if (!tabelaSimbolos.containsKey(id)) {
            throw new SemanticError("Identificador não declarado");
        }

        String tipoId = tabelaSimbolos.get(id);
        String tipoExp = this.desempilha();

        if (!tipoExp.equals(tipoId)) {
            throw new SemanticError("Tipo incompatível em comando de atribuição");
        }

        if (INT64.equals(tipoId)) {
            this.addCodigo("conv.r8");
        }

        this.addCodigo("stloc %s", id);
    }

    //107
    //todo: check
    public void desviaFalse() {
        this.addCodigo("brfalse label%d", ++indexLabel);
    }

    //108
    //todo: check
    public void desvia() {
        this.addCodigo("label%d:", indexLabel);
    }

    //109
    //todo: check
    public void geraLabelDesvio() {
        this.addCodigo("br label%d", indexLabel);
        this.addCodigo("label%d:", indexLabel - 1);
    }

    //110
    //todo: check
    public void desvia2() {
        this.addCodigo("label%d:", indexLabel);
    }

    //111
    //todo: check
    public void desviaTrue() {
        this.addCodigo("brtrue label%d", ++indexLabel);
    }

    //112
    //todo: check
    public void geraLabelDesvio2() {
        this.addCodigo("br label%d", indexLabel - 1);
        this.addCodigo("label%d:", indexLabel);
    }


}