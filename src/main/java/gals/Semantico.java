package gals;

import core.GeradorCodigo;

public class Semantico implements Constants
{
    void executeAction(int action, Token token)	throws SemanticError
    {
        GeradorCodigo.getInstance().executeAction(action, token);
        System.out.println("Acão #"+action+", Token: "+token);
    }
}
