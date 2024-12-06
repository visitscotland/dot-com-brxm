package com.visitscotland.brxm.personalisation;

import org.hippoecm.hst.container.valves.AbstractOrderableValve;
import org.hippoecm.hst.core.container.ContainerException;
import org.hippoecm.hst.core.container.ValveContext;

/**
 * Valve responsible for extracting user-specific information from the request.
 * This information is used to construct a {@code VisitorContext}, which is
 * attached to the request context and made available for downstream processing.
 *
 * <p>The valve ensures that user-specific details are consistently parsed and
 * encapsulated within the {@code VisitorContext}, enabling other components
 * in the request pipeline to utilise this context without redundant processing.
 *
 * <p><strong>Responsibilities:</strong>
 * <ul>
 *   <li>Parse relevant user information from the incoming request.</li>
 *   <li>Initialise and attach a {@code VisitorContext} to the request.</li>
 *   <li>Ensure data integrity and context consistency for downstream operations.</li>
 * </ul>
 */
final class PersonalisationValve extends AbstractOrderableValve {
    @Override
    public void invoke(ValveContext valveContext) throws ContainerException {
        valveContext.invokeNext();
    }
}