package blf.core.filters;

import java.util.Arrays;
import java.util.List;

import blf.core.Instruction;
import blf.core.ProgramState;

/**
 * Program
 */
public class Program extends FilterInstruction {

    public Program(FilterInstruction... instructions) {
        this(Arrays.asList(instructions));
    }

    public Program(List<Instruction> instructions) {
        super(instructions);
    }

    public void execute(ProgramState state) {
        try {
            this.executeInstructions(state);
            state.getWriters().writeAllData();
        } catch (final Throwable ex) {
            final String message = "Error when executing the program.";
            state.getExceptionHandler().handleExceptionAndDecideOnAbort(message, ex);
        }
        // finally {
        // state.close();
        // }
    }

}
