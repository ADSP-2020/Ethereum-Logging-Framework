package blf.parsing;

import blf.grammar.BcqlBaseListener;
import org.antlr.v4.runtime.Token;

/**
 * XbelAnalyzer
 */
public abstract class SemanticAnalyzer extends BcqlBaseListener {
    protected final ErrorCollector errorCollector;

    public SemanticAnalyzer(ErrorCollector errorCollector) {
        assert errorCollector != null;
        this.errorCollector = errorCollector;
    }

    public abstract void clear();

    protected void addError(Token token, String message) {
        this.errorCollector.addSemanticError(token, message);
    }
}