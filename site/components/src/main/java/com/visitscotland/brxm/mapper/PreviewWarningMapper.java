package com.visitscotland.brxm.mapper;

import com.visitscotland.brxm.hippobeans.BaseDocument;
import com.visitscotland.brxm.model.ErrorModule;
import com.visitscotland.brxm.model.Module;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.springframework.stereotype.Controller;

@Controller
public class PreviewWarningMapper {

    public <T extends HippoBean> Module<T> createErrorModule(Module<T> source){
        Module<T> errorModule = new Module<>();
        errorModule.setHippoBean(source.getHippoBean());
        errorModule.setErrorMessages(source.getErrorMessages());
        return errorModule;
    }

    public ErrorModule createErrorModule(BaseDocument document, String message){
        return new ErrorModule(document, message);
    }
}
