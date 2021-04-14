package com.hhgs.app.model.DO.hbase;

public class QueryObjectData {

    private String forceFetch="1";

    private  int orFilter=1;

    private String params;

    private String filterStr;

    private String orderBy="time DESC";

    public QueryObjectData(String params, String filterStr) {
        this.params = params;
        this.filterStr = filterStr;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getForceFetch() {
        return forceFetch;
    }

    public void setForceFetch(String forceFetch) {
        this.forceFetch = forceFetch;
    }

    public int getOrFilter() {
        return orFilter;
    }

    public void setOrFilter(int orFilter) {
        this.orFilter = orFilter;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getFilterStr() {
        return filterStr;
    }

    public void setFilterStr(String filterStr) {
        this.filterStr = filterStr;
    }
}
