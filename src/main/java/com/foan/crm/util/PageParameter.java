package com.foan.crm.util;

import lombok.Data;

@Data
public class PageParameter {
    private int perPageSize;
    private int currentPage;
    private int totalCount;
    private int totalPage;
    private int offset;

    public PageParameter(int perPageSize, int currentPage, int totalCount) {
        this.perPageSize = perPageSize;
        this.currentPage = currentPage;
        this.totalCount = totalCount;
        if (totalCount % perPageSize == 0) {
            this.totalPage = totalCount / perPageSize;
        } else {
            this.totalPage = totalCount / perPageSize + 1;
        }
        this.offset = (currentPage - 1) * perPageSize;
    }

    public PageParameter(int perPageSize, int currentPage) {
        this.perPageSize = perPageSize;
        this.currentPage = currentPage;
        this.offset = (currentPage - 1) * perPageSize;
    }
}
