package au.csiro.data61.aap.program.suppliers;

import au.csiro.data61.aap.program.types.SolidityType;

/**
 * ValueSource
 */
public interface ValueSupplier {
    public Object getValue() throws Throwable;
    public SolidityType getType();    
}