package au.csiro.data61.aap.spec.types;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * SolidityType
 */
public abstract class SolidityType {
    private final Set<Class<?>> compatibleTypes;

    protected SolidityType(Class<?>... compatibleTypes) {
        this.compatibleTypes = new HashSet<>(Arrays.asList(compatibleTypes));
    }

    public abstract String getName();

    public boolean castableFrom(SolidityType type) {
        return type != null && this.compatibleTypes.contains(type.getClass());
    }

    @Override
    public String toString() {
        return this.getName();
    }
}