package com.visitscotland.brxm.comparator;

public class UnexpectedNullException extends VsContractException {
    public UnexpectedNullException(String property) {
        super(String.format("Unexpected null value for property %s", property));
    }
}
