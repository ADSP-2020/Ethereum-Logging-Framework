package blf.core.instructions;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import blf.core.state.ProgramState;
import blf.core.exceptions.ProgramException;
import blf.core.interfaces.Instruction;
import io.reactivex.annotations.NonNull;

/**
 * Scope
 */
public abstract class FilterInstruction implements Instruction {
    private final List<Instruction> instructions;

    protected FilterInstruction(@NonNull List<Instruction> instructions) {
        assert instructions.stream().allMatch(Objects::nonNull);
        this.instructions = new LinkedList<>(instructions);
    }

    protected void executeInstructions(ProgramState state) throws ProgramException {
        for (Instruction instruction : this.instructions) {
            instruction.execute(state);
        }
    }

    /**
     * Executes the nested EMIT instructions.
     *
     * @param programState The current ProgramState of the BLF.
     */
    protected void executeNestedInstructions(ProgramState programState) {
        try {
            this.executeInstructions(programState);
        } catch (ProgramException err) {
            String errorMsg = "Unable to execute nested EMIT instructions.";
            programState.getExceptionHandler().handleExceptionAndDecideOnAbort(errorMsg, err);
        }
    }
}