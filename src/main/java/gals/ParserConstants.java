package gals;

public interface ParserConstants {
    int START_SYMBOL = 42;

    int FIRST_NON_TERMINAL = 42;
    int FIRST_SEMANTIC_ACTION = 77;

    int[][] PARSER_TABLE =
            {
                    {-1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                    {-1, -1, 2, -1, -1, -1, -1, -1, 2, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, 2, 2, -1, -1, -1, -1, -1, 1, -1, 2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                    {-1, -1, -1, -1, -1, -1, -1, 4, -1, -1, -1, -1, -1, -1, 4, 4, 4, -1, -1, -1, -1, -1, -1, -1, -1, -1, 3, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                    {-1, -1, -1, -1, -1, -1, -1, 6, -1, -1, -1, -1, -1, -1, 5, 5, 5, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                    {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 8, 9, 7, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                    {-1, -1, -1, -1, -1, -1, -1, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                    {-1, -1, -1, 11, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                    {-1, -1, 12, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                    {-1, -1, -1, -1, -1, -1, -1, 13, -1, -1, -1, -1, -1, -1, 13, 13, 13, -1, -1, -1, -1, -1, -1, 14, -1, -1, 13, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                    {-1, -1, 15, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                    {-1, -1, 16, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                    {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 17, 18, -1, -1, -1, 17, -1, 17, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                    {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 19, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                    {-1, -1, 20, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                    {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 22, -1, -1, -1, -1, 21, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                    {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 23, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                    {-1, -1, 24, 24, 24, 24, -1, -1, -1, -1, 24, -1, -1, -1, -1, -1, -1, 24, -1, 24, -1, -1, -1, -1, -1, -1, -1, -1, -1, 24, -1, 24, 24, -1, -1, -1, -1, -1, -1, -1, -1},
                    {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 26, -1, -1, -1, -1, 25, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                    {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 27, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                    {-1, -1, 28, -1, -1, -1, -1, -1, 28, -1, -1, 28, 29, -1, -1, -1, -1, -1, -1, -1, 28, 28, -1, -1, -1, -1, -1, 28, -1, 28, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                    {-1, -1, -1, -1, -1, -1, -1, -1, 30, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                    {-1, -1, 31, -1, -1, -1, -1, -1, 35, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 33, 32, -1, -1, -1, -1, -1, -1, -1, 34, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                    {-1, -1, 36, 36, 36, 36, -1, -1, -1, -1, 36, -1, -1, -1, -1, -1, -1, 36, -1, 36, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, 36, 36, -1, -1, -1, -1, -1, -1, -1, -1},
                    {-1, -1, 37, -1, -1, -1, 38, -1, 37, -1, -1, 37, -1, -1, -1, -1, -1, -1, 39, -1, 37, 37, -1, 37, -1, 37, -1, 37, 37, 37, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                    {-1, -1, 40, 40, 40, 40, -1, -1, -1, -1, 42, -1, -1, -1, -1, -1, -1, 43, -1, 41, -1, -1, -1, -1, -1, -1, -1, -1, -1, 40, -1, 40, 40, -1, -1, -1, -1, -1, -1, -1, -1},
                    {-1, -1, 44, 44, 44, 44, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 44, -1, 44, 44, -1, -1, -1, -1, -1, -1, -1, -1},
                    {-1, -1, 45, -1, -1, -1, 45, -1, 45, -1, -1, 45, -1, -1, -1, -1, -1, -1, 45, -1, 45, 45, -1, 45, -1, 45, -1, 45, 45, 45, -1, -1, -1, -1, -1, 46, 46, 46, 46, 46, 46},
                    {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 47, 48, 49, 50, 51, 52},
                    {-1, -1, 53, 53, 53, 53, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 53, -1, 53, 53, -1, -1, -1, -1, -1, -1, -1, -1},
                    {-1, -1, 54, -1, -1, -1, 54, -1, 54, -1, -1, 54, -1, -1, -1, -1, -1, -1, 54, -1, 54, 54, -1, 54, -1, 54, -1, 54, 54, 54, -1, 55, 56, -1, -1, 54, 54, 54, 54, 54, 54},
                    {-1, -1, 57, 57, 57, 57, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 57, -1, 57, 57, -1, -1, -1, -1, -1, -1, -1, -1},
                    {-1, -1, 58, -1, -1, -1, 58, -1, 58, -1, -1, 58, -1, -1, -1, -1, -1, -1, 58, -1, 58, 58, -1, 58, -1, 58, -1, 58, 58, 58, -1, 58, 58, 59, 60, 58, 58, 58, 58, 58, 58},
                    {-1, -1, 61, 62, 63, 64, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 65, -1, 66, 67, -1, -1, -1, -1, -1, -1, -1, -1},
                    {-1, -1, 68, -1, -1, -1, 68, -1, 68, -1, -1, 68, -1, -1, -1, -1, -1, -1, 68, -1, 68, 68, -1, 68, 69, 68, -1, 68, 68, 68, -1, 68, 68, 68, 68, 68, 68, 68, 68, 68, 68},
                    {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}
            };

    int[][] PRODUCTIONS =
            {
                    {44, 27, 43, 28},
                    {0},
                    {63, 43},
                    {0},
                    {45, 23, 49, 44},
                    {46},
                    {47},
                    {17},
                    {15},
                    {16},
                    {8, 25, 46, 24, 48, 26},
                    {4},
                    {3, 50},
                    {0},
                    {24, 49},
                    {52, 31, 64},
                    {3, 53},
                    {0},
                    {25, 64, 26},
                    {22, 30, 55, 29},
                    {52, 56},
                    {0},
                    {24, 55},
                    {21, 30, 58, 29},
                    {64, 59},
                    {0},
                    {24, 58},
                    {30, 64, 29, 14, 27, 43, 28, 61},
                    {0},
                    {13, 27, 43, 28},
                    {9, 27, 43, 12, 30, 64, 29, 10, 43, 28},
                    {51},
                    {54},
                    {57},
                    {60},
                    {62},
                    {66, 65},
                    {0},
                    {7, 66, 65},
                    {19, 66, 65},
                    {67},
                    {20},
                    {11},
                    {18, 66},
                    {70, 68},
                    {0},
                    {69, 70},
                    {36},
                    {37},
                    {38},
                    {39},
                    {40},
                    {41},
                    {72, 71},
                    {0},
                    {32, 72, 71},
                    {33, 72, 71},
                    {74, 73},
                    {0},
                    {34, 74, 73},
                    {35, 74, 73},
                    {3, 75},
                    {4},
                    {5},
                    {6},
                    {30, 64, 29},
                    {32, 74},
                    {33, 74},
                    {0},
                    {25, 64, 26}
            };

    String[] PARSER_ERROR =
            {
                    "",
                    "esperado fim de programa",
                    "esperado palavra reservada",
                    "esperado identificador",
                    "esperado constante numérica",
                    "esperado constante caractere",
                    "esperado constante literal",
                    "esperado and",
                    "esperado array",
                    "esperado do",
                    "esperado exit",
                    "esperado false",
                    "esperado if",
                    "esperado ifFalseDo",
                    "esperado ifTrueDo",
                    "esperado literal",
                    "esperado logical",
                    "esperado number",
                    "esperado not",
                    "esperado or",
                    "esperado true",
                    "esperado write",
                    "esperado read",
                    "esperado \":\"",
                    "esperado \",\"",
                    "esperado \"[\"",
                    "esperado \"]\"",
                    "esperado \"{\"",
                    "esperado \"}\"",
                    "esperado \")\"",
                    "esperado \"(\"",
                    "esperado \":=\"",
                    "esperado \"+\"",
                    "esperado \"-\"",
                    "esperado \"*\"",
                    "esperado \"/\"",
                    "esperado \"=\"",
                    "esperado \"!=\"",
                    "esperado \"<\"",
                    "esperado \"<=\"",
                    "esperado \">\"",
                    "esperado \">=\"",
                    "esperado  array  literal  logical  number  {",//"<programa> inv�lido",
                    "esperado  id  do  if  write  read  }  (",//"<lista_cmd> inv�lido",
                    "esperado  array  literal  logical  number  {",//"<declaracao_de_variavel> inv�lido",
                    "esperado  array  literal  logical  number",//"<tipo> inv�lido",
                    "esperado  literal  logical  number",//"<simples> inv�lido",
                    "esperado  array",//"<array> inv�lido",
                    "esperado  constante numérica",//"<arrsize> inv�lido",
                    "esperado  identificador",//"<lista_de_identificadores> inv�lido",
                    "esperado  array  literal  logical  number  ,  {",//"<lista_de_identificadores1> inv�lido",
                    "esperado  identificador",//"<comandoattr> inv�lido",
                    "esperado  identificador",//"<lista_id_attr> inv�lido",
                    "esperado  ,  [  )  :=",//"<lista_id_attr1> inv�lido",
                    "esperado  read",//"<comandoread> inv�lido",
                    "esperado  identificador",//"<lista_id_read> inv�lido",
                    "esperado  ,  )",//"<lista_id_read1> inv�lido",
                    "esperado  write",//"<comandowrite> inv�lido",
                    "esperado  expressão",//"<lista_expressoes> inv�lido",
                    "esperado  , )",//"<lista_expressoes1> inv�lido",
                    "esperado  (",//"<comandoselect> inv�lido",
                    "esperado  id do if ifFalseDo write read } (",//"<selectopcional> inv�lido",
                    "esperado  do",//"<comandorep> inv�lido",
                    "esperado  id do write read (",//"<comando> inv�lido",
                    "esperado  expressão",//"<expressao> inv�lido",
                    "esperado  expressão",//"<expressao1> inv�lido",
                    "esperado  expressão",//"<elemento> inv�lido",
                    "esperado  expressão",//"<relacional> inv�lido",
                    "esperado  expressão",//"<relacional1> inv�lido",
                    "esperado  expressão",//"<operador_relacional> inv�lido",
                    "esperado  expressão",//"<aritmetica> inv�lido",
                    "esperado  expressão",//"<aritmetica1> inv�lido",
                    "esperado  expressão",//"<termo> inv�lido",
                    "esperado  expressão",//"<termo1> inv�lido",
                    "esperado  expressão",//"<fator> inv�lido",
                    "esperado  expressão",//"<fator1> inv�lido",
                    "esperado  expressão",//"<fator2> inv�lido"
            };
}