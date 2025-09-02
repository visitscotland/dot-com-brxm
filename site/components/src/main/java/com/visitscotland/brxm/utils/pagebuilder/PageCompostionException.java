package com.visitscotland.brxm.utils.pagebuilder;

public class PageCompostionException extends Exception {

    public PageCompostionException(String message) {
        super(String.format("There has been an error composing the page: \n %s", message));
    }

    public PageCompostionException(String path, String message) {
        super(String.format("There has been an issue mapping the document at '%s': \n %s", path, message));
    }
}
