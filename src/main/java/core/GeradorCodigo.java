package core;

import gals.SemanticError;
import gals.Token;

import java.util.*;

public class GeradorCodigo {

    private static GeradorCodigo instance;

    private Stack<String> pilha;
    private Queue<String> codigo;
    private Queue<String> listaIdentificadores;
    private Map<String, String> tabelaSimbolos;

    //
    public static final String FLOAT64 = "float64";
    public static final String INT64 = "int64";
    public static final String STRING = "string";
    public static final String BOOL = "boolean";
    public static final String TRUE = "true";
    public static final String FALSE = "false";
    private String FLOAT_SEPARATOR = ".";
    private String operador;
    private String tipovar;

    private GeradorCodigo() {
        pilha = new Stack<>();
        codigo = new LinkedList<>();
        listaIdentificadores = new LinkedList<>();
        tabelaSimbolos = new HashMap<>();
    }

    public void executeAction(int action, Token token) throws SemanticError {
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
                desviaFalse(token);
                break;
            case 108:
                desvia(token);
                break;
            case 109:
                geraLabelDesvio();
                break;
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
    public void addCodigo(String instruction, String... args) {
        this.codigo.add(String.format(instruction, args));
    }

    //1
    public void somar() throws SemanticError {
        String tipo1 = this.desempilha();
        String tipo2 = this.desempilha();

        if (!Util.isTipoNumerico(tipo1) || !Util.isTipoNumerico(tipo2)) {
            //todo
            throw new SemanticError("");
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
            //todo
            throw new SemanticError("");
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
            //todo
            throw new SemanticError("");
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
                || !tipo1.equalsIgnoreCase(tipo2)
        ) {
            //todo
            throw new SemanticError("");
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
        this.addCodigo("ldc.i8 %s", token.getLexeme());
    }

    //7
    public void verificarTipoSoma() throws SemanticError {
        String tipo = this.desempilha();
        if (tipo.equals(FLOAT64) || tipo.equals(INT64)) {
            this.empilha(tipo);
        } else {
            //todo
            throw new SemanticError("");
        }
    }

    //8
    public void verificarTipoSubtracao() throws SemanticError {
        String tipo = this.desempilha();
        if (tipo.equals(FLOAT64) || tipo.equals(INT64)) {
            this.empilha(tipo);
        } else {
            //todo
            throw new SemanticError("");
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
            //todo
            throw new SemanticError("");
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
            //todo
            throw new SemanticError("");
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
            //todo
            throw new SemanticError("");
        }

        if (valor1.equalsIgnoreCase(TRUE)
                && valor2.equalsIgnoreCase(TRUE)) {
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
            //todo
            throw new SemanticError("");
        }

        if (valor1.equalsIgnoreCase(TRUE)
                || valor2.equalsIgnoreCase(TRUE)) {
            this.empilha(TRUE);
        } else {
            this.empilha(FALSE);
        }
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
                //todo
                throw new SemanticError("");
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
                //todo
                throw new SemanticError("");
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
            //todo
            throw new SemanticError("");
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
            //todo
            throw new SemanticError("");
        }

        String tipoId = tabelaSimbolos.get(id);
        String tipoExp = this.desempilha();

        if (!tipoExp.equals(tipoId)) {
            //todo
            throw new SemanticError("");
        }

        if (INT64.equals(tipoId)) {
            this.addCodigo("conv.r8");
        }

        this.addCodigo("stloc %s", id);
    }

    //107
    //todo: idk
    public void desviaFalse(Token token) {
        this.addCodigo("brfalse %s", token.getLexeme());
    }

    //108
    //todo: idk
    public void desvia(Token token) {
        this.addCodigo("%s:", token.getLexeme());
    }

    //109
    //todo: idk
    public void geraLabelDesvio() {
        String label1 = desempilha();
        String label2 = desempilha();

        this.addCodigo("br %s", label1);
        this.addCodigo("%s:", label2);
    }

}