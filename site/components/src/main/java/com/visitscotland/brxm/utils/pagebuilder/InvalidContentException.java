package com.visitscotland.brxm.utils.pagebuilder;

import org.hippoecm.hst.content.beans.standard.HippoBean;

public class InvalidContentException extends PageCompositionException{
    public InvalidContentException(String documentPath, String message) {
        super(documentPath, message);
    }
}
