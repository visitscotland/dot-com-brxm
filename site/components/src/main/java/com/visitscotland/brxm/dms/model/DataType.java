package com.visitscotland.brxm.dms.model;

import java.util.Objects;

/**
 * This object has been duplacated from data-service-api: {@code com.visitscotland.brxm.dms.model.DataType}
 *
 * The DMS has been retired from the CMS and it is in the roadmap to fully remove all dependencies and logic related to it.
 */
public class DataType implements Comparable<DataType> {
    private final String id;
    private final String name;

    public DataType(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int compareTo(DataType paramT) {
        return this.getName().compareTo(paramT.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DataType dataType = (DataType) o;
        return Objects.equals(id, dataType.id) && Objects.equals(name, dataType.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
