package com.visitscotland.brxm.valves;

import java.util.Optional;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.hippoecm.hst.container.valves.AbstractOrderableValve;
import org.hippoecm.hst.core.container.ContainerException;
import org.hippoecm.hst.core.container.ValveContext;

import com.visitscotland.brxm.model.Persona;

public class PersonalisationValve extends AbstractOrderableValve {

    @Override
    public void invoke(ValveContext context) throws ContainerException {
        try {
            //The visitors characteristics can be extracted from query parameters, headers or cookies.

            //Country is always single for a visitor
            final String country = context.getServletRequest().getParameter("country");
            //Interests and businessSectors I am assuming can be multiple
            final String[] interests = Optional.ofNullable(context.getRequestContext().getServletRequest().getParameter("interests")).orElse(",").split(",");
            final String[] sectors = Optional.ofNullable(context.getRequestContext().getServletRequest().getParameter("sectors")).orElse(",").split(",");

            if (StringUtils.isNotEmpty(country) || ArrayUtils.isNotEmpty(interests) || ArrayUtils.isNotEmpty(sectors)) {
                Persona persona = new Persona();
                persona.setCountry(country);
                persona.setInterests(interests);
                persona.setSectors(sectors);
                context.getRequestContext().setAttribute("persona", persona);
            }
        } finally {
            context.invokeNext();
        }
    }
}
