package com.visitscotland.brxm.pagebuilder;

public class PageCompositionException extends Exception {

    public PageCompositionException(String message) {
        super(String.format("There has been an error composing the page: \n %s", message));
    }

    public PageCompositionException(String path, String message) {
        super(String.format("There has been an issue mapping the document at '%s': \n %s", path, message));
    }

    public PageCompositionException(String path, String message, Exception e) {
        super(String.format("There has been an issue mapping the document at '%s': \n %s", path, message), e);
    }
}
