package com.visitscotland.brxm.comparator;

/**
 * Wrapper Exception for Exceptions originated by BloomReach or Jack Rabbit
 */
public class BrxmWrapperException extends Exception {
    public BrxmWrapperException(Exception e) {
        super(e);
    }
}
