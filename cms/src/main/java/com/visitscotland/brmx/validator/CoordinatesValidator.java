package com.visitscotland.brmx.validator;

import com.visitscotland.brmx.beans.Coordinates;
import org.onehippo.cms.services.validation.api.ValidationContext;
import org.onehippo.cms.services.validation.api.ValidationContextException;
import org.onehippo.cms.services.validation.api.Validator;
import org.onehippo.cms.services.validation.api.Violation;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import java.util.Optional;

/**
 * jcr:Name = visitscotland:scotland-coordinates-validator
 *
 * @author jose.calcines
 */
//TODO investigate if we can create unit test for this validator
public class CoordinatesValidator implements Validator<Node> {

    private static final String NORTH_EXTREME = "extreme.north";
    private static final String SOUTH_EXTREME = "extreme.south";
    private static final String EAST_EXTREME = "extreme.east";
    private static final String WEST_EXTREME = "extreme.west";

    private double north;
    private double south;
    private double east;
    private double west;

    public CoordinatesValidator(final Node config) {
        try {
            north = config.getProperty(NORTH_EXTREME).getDouble();
            south = config.getProperty(SOUTH_EXTREME).getDouble();
            east = config.getProperty(EAST_EXTREME).getDouble();
            west = config.getProperty(WEST_EXTREME).getDouble();
            if (north < south || east < west) {
                throw new ValidationContextException("Coordinates configuration is wrong. Verify the node " + config.getPath());
            }
        } catch (RepositoryException e) {
            try {
                throw new ValidationContextException("Cannot read required properties for the CoordinatesValidator. Verify the node " + config.getPath(), e);
            } catch (RepositoryException nodeException) {
                throw new ValidationContextException("Unexpected Error when loading CoordinatesValidator class", nodeException);
            }
        }
    }

    public Optional<Violation> validate(final ValidationContext context, final Node document) {
        try {
            if (document.hasProperty(Coordinates.LATITUDE) || document.hasProperty(Coordinates.LONGITUDE)) {
                double lat = document.getProperty(Coordinates.LATITUDE).getDouble();
                double lon = document.getProperty(Coordinates.LONGITUDE).getDouble();
                if (north < lat || lat < south || east < lon || lon < west) {
                    return Optional.of(context.createViolation());
                }
            }
        } catch (RepositoryException e) {
            return Optional.of(context.createViolation());
        }

        return Optional.empty();
    }
}
