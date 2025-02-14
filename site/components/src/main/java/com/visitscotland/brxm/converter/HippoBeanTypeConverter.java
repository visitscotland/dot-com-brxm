package com.visitscotland.brxm.converter;

import org.hippoecm.hst.content.beans.standard.HippoBean;

/**
 * A functional interface for converting a {@link HippoBean} into a specified result type.
 *
 * @param <H> the type of the {@link HippoBean} to be converted, which must extend {@link HippoBean}
 * @param <R> the type of the result after conversion
 *
 * <p>This interface provides a single abstract method, {@link #convert(H)}, which takes
 * an instance of type H and returns an instance of type R. It is intended to be used
 * in scenarios where different conversion strategies for HippoBeans are needed.</p>
 */
@FunctionalInterface
public interface HippoBeanTypeConverter<H extends HippoBean, R> {
    R convert(H hippoBean);
}