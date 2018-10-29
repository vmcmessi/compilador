package gals;

import com.sun.istack.internal.Nullable;

public class SyntaticError extends AnalysisError {
    @Nullable private final Token token;

    public SyntaticError(String msg, Token token) {
        super(msg, token.getPosition());
        this.token = token;
    }

    public SyntaticError(String msg) {
        super(msg);
        token = null;
    }

    public Token getToken() {
        return token;
    }
}
