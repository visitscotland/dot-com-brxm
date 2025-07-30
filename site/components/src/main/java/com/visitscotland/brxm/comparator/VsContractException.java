package com.visitscotland.brxm.comparator;

/**
 * This exception represent a broken contract between developers and flags that the method is not designed to accept
 * that value
 *
 * This usually happens when values passed are expected to be sanitized or the values to match certain characteristics.
 *
 * When this Exception happens
 */
public class VsContractException extends Exception {

    public VsContractException(String message) {
        super(message);
    }

}
