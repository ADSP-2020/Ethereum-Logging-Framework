package blf.core;

import blf.core.instructions.Instruction;
import blf.core.state.ProgramState;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Program
 */
public class Program {

    private final List<Instruction> instructions;

    public Program(Instruction... instructions) {
        this(Arrays.asList(instructions));
    }

    public Program(List<Instruction> instructions) {
        if (instructions == null) {
            this.instructions = new LinkedList<>();
        } else {
            this.instructions = new LinkedList<>(instructions);
        }
    }

    public void execute(ProgramState state) {
        for (Instruction instruction : this.instructions) {
            instruction.execute(state);
        }

        try {
            state.getWriters().writeAllData();
        } catch (Throwable throwable) {
            // TODO: remove the throw of Throwable or handle it inside the method where the Throwable is thrown
            state.getExceptionHandler().handleExceptionAndDecideOnAbort(throwable.getMessage(), throwable);
        }

        state.close();
    }

}
