package com.eshop.common.utils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ADMIN on 2017/10/26.
 */
public class EUIDataGridResult implements Serializable{
    private long total;
    private List<?> rows;

    public EUIDataGridResult() {
    }

    public EUIDataGridResult(long total, List<?> rows) {
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
