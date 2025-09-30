package com.visitscotland.brxm.pagebuilder;

public class InvalidContentException extends PageCompositionException {
    public InvalidContentException(String documentPath, String message) {
        super(documentPath, message);
    }
}
