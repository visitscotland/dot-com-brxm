package com.visitscotland.brxm.model.bsh;

import java.io.Serializable;
import java.util.List;

public class PaginatedResult<T> implements Serializable {

    private int total;
    private int pageSize;
    private List<T> results;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
