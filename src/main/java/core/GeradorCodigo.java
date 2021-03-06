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
    private ArrayList<Integer> labels = new ArrayList<>();
    private int indexLabel = 1;
    private String operador;
    private String tipovar;
    private int ultimoExecutado = 0;

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
        String filenameOpened = tela.getFilenameOpened();
        int indexDot = filenameOpened.lastIndexOf('.');
        String pathWithoutDot = filenameOpened.substring(0, indexDot);
        pathWithoutDot = pathWithoutDot.replace('\\', '/');

        Path file = Paths.get(String.format("%s.il", pathWithoutDot));
        Files.write(file, codigo, Charset.forName("UTF-8"));
        clearAll();
    }

    public void executeAction(int action, Token token) throws SemanticError {
        try {
            ultimoExecutado = action;
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
    private void empilha(String value) {
        this.pilha.push(value);
    }

    /**
     * Desempilha token
     */
    private String desempilha() {
        return this.pilha.pop();
    }

    /**
     * Adiciona codigo na lista
     */
    private void addCodigo(String instruction) {
        this.codigo.add(instruction);
    }

    /**
     * Adiciona codigo na lista formatado
     */
    private void addCodigo(String instruction, Object... args) {
        this.codigo.add(String.format(instruction, args));
    }

    //1
    private void somar() throws SemanticError {
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
    private void subtrair() throws SemanticError {
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
    private void multiplicar() throws SemanticError {
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
    private void dividir() throws SemanticError {
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
    private void empilhaInt64(Token token) {
        this.empilha(INT64);
        this.addCodigo("ldc.i8 %s", token.getLexeme());
        this.addCodigo("conv.r8");
    }

    //6
    private void empilhaFloat64(Token token) {
        this.empilha(FLOAT64);
        this.addCodigo("ldc.r8 %s", token.getLexeme());
    }

    //7
    private void verificarTipoSoma() throws SemanticError {
        String tipo = this.desempilha();
        if (tipo.equals(FLOAT64) || tipo.equals(INT64)) {
            this.empilha(tipo);
        } else {
            throw new SemanticError("Tipo incompatível em operação aritmética unária");
        }
    }

    //8
    private void verificarTipoSubtracao() throws SemanticError {
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
    private void relacional() throws SemanticError {
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
    private void empilhaFalse() {
        this.empilha("bool");
        this.addCodigo("ldc.i4.0");
    }

    //12
    private void empilhaTrue() {
        this.empilha("bool");
        this.addCodigo("ldc.i4.1");
    }

    //13
    private void not() throws SemanticError {
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
    private void write() {
        String tipo = this.desempilha();
        if (tipo.equals(INT64)) {
            this.addCodigo("conv.i8");
        }
        this.addCodigo("call void [mscorlib]System.Console::Write(%s)", tipo);
    }

    //15
    private void inicioCodigo() {
        this.addCodigo(".assembly extern mscorlib {}\n" +
                ".assembly _codigo_objeto{}\n" +
                ".module _codigo_objeto.exe\n" +
                "\n" +
                ".class private _UNICA{\n" +
                ".method static private void _PRINCIPAL(){\n" +
                ".entrypoint\n"
        );
    }

    //16
    private void fimCodigo() {
        this.addCodigo("ret\n" +
                "}\n" +
                "}");
    }

    //17
    private void and() throws SemanticError {
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
    private void or() throws SemanticError {
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
    private void constanteCaractere(Token token) throws SemanticError {
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
    private void constanteLiteral(Token token) {
        this.empilha(STRING);
        this.addCodigo("ldstr %s", token.getLexeme());
    }

    //101
    private void setTipoVar(Token token) {

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
    private void declararVariaveis() throws SemanticError {
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
    private void read() throws SemanticError {
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
    private void empilhaFatorExpressao(Token token) throws SemanticError {
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
    private void atribuicao() throws SemanticError {
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
    private void desviaFalse() {
        this.labels.add(this.labels.size() + 1);
        this.addCodigo("brfalse label%d", this.labels.get(this.labels.size() - 1));
    }

    //108
    //todo: check
    private void desvia() {
        if (ultimoExecutado == 108) {
            this.labels.remove(this.labels.size() - 1);
        }

        this.addCodigo("label%d:", this.labels.get(this.labels.size() - 1));
        this.labels.remove(this.labels.size() - 1);
    }

    //109
    //todo: check
    private void geraLabelDesvio() {
        this.labels.add(this.labels.size() + 1);
        this.addCodigo("br label%d", this.labels.get(this.labels.size() - 1));
        this.addCodigo("label%d:", this.labels.get(this.labels.size() - 2));
    }

    //110
    //todo: check
    private void desvia2() {
        this.labels.add(this.labels.size() + 1);
        this.addCodigo("label%d:", this.labels.get(this.labels.size() - 1));
    }

    //111
    //todo: check
    private void desviaTrue() {
        this.labels.add(this.labels.size() + 1);
        this.addCodigo("brtrue label%d", this.labels.get(this.labels.size() - 1));
    }

    //112
    //todo: check
    private void geraLabelDesvio2() {
        this.addCodigo("br label%d", this.labels.get(this.labels.size() - 2));
        this.addCodigo("label%d:", this.labels.get(this.labels.size() - 1));
    }


}