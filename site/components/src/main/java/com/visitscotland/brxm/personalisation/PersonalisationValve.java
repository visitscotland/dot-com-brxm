package com.visitscotland.brxm.personalisation;

import org.hippoecm.hst.container.valves.AbstractOrderableValve;
import org.hippoecm.hst.core.container.ContainerException;
import org.hippoecm.hst.core.container.ValveContext;

final class PersonalisationValve extends AbstractOrderableValve {
    @Override
    public void invoke(ValveContext valveContext) throws ContainerException {
        valveContext.invokeNext();
    }
}