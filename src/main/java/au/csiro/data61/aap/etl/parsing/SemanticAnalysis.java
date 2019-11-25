package au.csiro.data61.aap.etl.parsing;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import au.csiro.data61.aap.etl.util.CompositeEthqlListener;

/**
 * SemanticAnalysis
 */
class SemanticAnalysis extends CompositeEthqlListener<SemanticAnalyzer> {
    private final ErrorCollector errorCollector;    

    public SemanticAnalysis(ErrorCollector errorCollector) {
        assert errorCollector != null;
        this.errorCollector = errorCollector;
        

        final VariableAnalyzer varAnalyzer = new VariableAnalyzer(errorCollector);
        this.addListener(varAnalyzer);
        this.addListener(new ScopeAnalyzer(this.errorCollector, varAnalyzer));    
        this.addListener(new MethodCallAnalyzer(this.errorCollector, varAnalyzer));
    }

    public void analyze(ParseTree parseTree) {
        this.listenerStream().forEach(SemanticAnalyzer::clear);

        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(this, parseTree);
    }
    
}