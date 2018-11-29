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
    private String FLOAT64 = "float64";
    private String INT64 = "int64";
    private String BOOL = "bool";
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
        }
    }

    /**
     * @return GeradorCodigo
     */
    public static GeradorCodigo getInstance() {
        if (instance == null) instance = new GeradorCodigo();
        return instance;
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

    public void somar() {
        String tipo1 = this.desempilha();
        String tipo2 = this.desempilha();

        if (tipo1.equals(FLOAT64) || tipo2.equals(FLOAT64)) {
            this.empilha(FLOAT64);
        } else {
            this.empilha(INT64);
        }

        this.addCodigo("add");
    }

    public void subtrair() {
        String tipo1 = this.desempilha();
        String tipo2 = this.desempilha();

        if (tipo1.equals(FLOAT64) || tipo2.equals(FLOAT64)) {
            this.empilha(FLOAT64);
        } else {
            this.empilha(INT64);
        }

        this.addCodigo("sub");
    }

    public void multiplicar() {
        String tipo1 = this.desempilha();
        String tipo2 = this.desempilha();

        if (tipo1.equals(FLOAT64) || tipo2.equals(FLOAT64)) {
            this.empilha(FLOAT64);
        } else {
            this.empilha(INT64);
        }

        this.addCodigo("mul");
    }

    public void dividir() throws SemanticError {
        String tipo1 = this.desempilha();
        String tipo2 = this.desempilha();

        if (tipo1.equals(tipo2)) {
            this.empilha(tipo1);
        } else {
            //todo
            throw new SemanticError("");
        }

        this.addCodigo("div");
    }

    public void empilhaInt64(Token token) {
        this.empilha(INT64);
        this.addCodigo("ldc.i8 %s", token.getLexeme());
        this.addCodigo("conv.r8");
    }

    public void empilhaFloat64(Token token) {
        this.empilha(FLOAT64);
        this.addCodigo("ldc.i8 %s", token.getLexeme());
    }

    public void verificarTipoSoma() throws SemanticError {
        String tipo = this.desempilha();
        if (tipo.equals(FLOAT64) || tipo.equals(INT64)) {
            this.empilha(tipo);
        } else {
            //todo
            throw new SemanticError("");
        }
    }

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

    public void empilhaFalse() {
        this.empilha("bool");
        this.addCodigo("ldc.i4.0");
    }

    public void empilhaTrue() {
        this.empilha("bool");
        this.addCodigo("ldc.i4.1");
    }

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

    public void write() {
        String tipo = this.desempilha();
        if (tipo.equals(INT64)) {
            this.addCodigo("conv.i8");
        }
        this.addCodigo("call void [mscorlib]System.Console::Write(%s)", tipo);
    }

    public void salvaTipoVar(Token token) {
        //todo
    }

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

    public void fimCodigo() {
        this.addCodigo("ret\n" +
                "}\n" +
                "}");
    }

}