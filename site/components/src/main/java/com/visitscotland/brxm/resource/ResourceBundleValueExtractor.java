package com.visitscotland.brxm.resource;

import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.ResourceBundle;
import java.util.Optional;
import java.util.HashMap;
import java.util.Objects;
import java.util.List;
import java.util.Map;

@Component
class ResourceBundleValueExtractor {
    /**
     * Extracts a value from the given {@link ResourceBundle} for the specified key.
     *
     * <p>If the key is not present in the resource bundle, this method returns {@link Optional#empty()}.
     * Otherwise, it returns an {@link Optional} containing the corresponding value.</p>
     *
     * @param resourceBundle the {@link ResourceBundle} to extract the value from; must not be {@code null}
     * @param key the key to look up in the resource bundle; must not be {@code null}
     * @return an {@link Optional} containing the value associated with the key, or {@link Optional#empty()} if the key is not found
     */
    Optional<String> extractValueFromResourceBundle(final @Nonnull ResourceBundle resourceBundle,
                                                    final @Nonnull String key) {
        if (!resourceBundle.containsKey(key)) {
            return Optional.empty();
        }

        final String resourceBundleValue = resourceBundle.getString(key);
        return Optional.of(resourceBundleValue);
    }

    /**
     * Extracts all values from the given {@link ResourceBundle} as an unmodifiable list.
     *
     * <p>This method retrieves all keys from the resource bundle, maps each key to its corresponding
     * value, and collects these values into an unmodifiable {@link List}.</p>
     *
     * @param resourceBundle the {@link ResourceBundle} to extract values from; must not be {@code null} -
     *                       it will result in a {@link NullPointerException}.
     * @return an unmodifiable {@link List} containing all values from the resource bundle
     */
    List<String> extractValuesFromResourceBundleAsList(final @Nonnull ResourceBundle resourceBundle) {
        Objects.requireNonNull(resourceBundle, "resourceBundle cannot be null");

        return resourceBundle
            .keySet()
            .stream()
            .map(resourceBundle::getString)
            .filter(Predicate.not(String::isBlank))
            .collect(Collectors.toUnmodifiableList());
    }

    /**
     * Extracts all key-value pairs from the given {@link ResourceBundle} and returns them as an unmodifiable {@link Map}.
     * <p>
     * The method iterates over all keys in the provided resource bundle and maps each key to its corresponding string value.
     * </p>
     *
     * @param resourceBundle the resource bundle from which to extract key-value pairs; must not be {@code null}
     * @return an unmodifiable map containing all key-value pairs from the resource bundle
     * @throws NullPointerException if {@code resourceBundle} is {@code null}
     */
    Map<String, String> extractValuesFromResourceBundleAsMap(final @Nonnull ResourceBundle resourceBundle) {
        Objects.requireNonNull(resourceBundle, "resourceBundle cannot be null");
        final Map<String, String> bundleMap = new HashMap<>();

        for (final String key : resourceBundle.keySet()) {
            bundleMap.put(key, resourceBundle.getString(key));
        }

        return Map.copyOf(bundleMap);
    }
}