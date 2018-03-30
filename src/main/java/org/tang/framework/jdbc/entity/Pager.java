package org.tang.framework.jdbc.entity;

import lombok.Data;
import org.tang.framework.entity.BaseModel;

import java.util.List;

@SuppressWarnings("serial")
@Data
public class Pager extends BaseModel {

    private Integer totalRows;
    private Integer pageSize = 10;
    private Integer currentPage;
    private Integer totalPage = 1;
    private Integer startRow;
    private Integer formNumber;
    private Integer viewBegin = 1;
    private Integer viewEnd = 1;
    private List<?> data;

    public Pager(Integer pageSize) {
        super();
        this.currentPage = 1;
        this.startRow = 0;
        this.pageSize = pageSize;
    }


    public Pager(Integer pageSize, Integer currentPage) {
        super();
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        if (pageSize == null || pageSize > 100) {
            pageSize = 20;
        }
        this.pageSize = pageSize;
        this.currentPage = currentPage;
    }

    public Pager() {
        this.currentPage = 1;
        this.startRow = 0;
    }

    public void setCurrentPage(Integer currPage) {
        if (currPage == null || currPage < 1) {
            currPage = 1;
        }
        this.currentPage = currPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize == null) {
            pageSize = 20;
        }
        if (pageSize > 100) {
            pageSize = 100;
        }
        this.pageSize = pageSize;
    }


    public void setTotalRows(Integer totalRows) {
        this.totalRows = totalRows;
        try {
            this.totalPage = totalRows / pageSize;
            Integer mod = totalRows % pageSize;
            if (mod > 0) {
                this.totalPage++;
            }
            if (this.totalPage == 0) {
                this.totalPage = 1;
            }
            if (this.currentPage > totalPage) {
                this.currentPage = totalPage;
            }
            this.startRow = (currentPage - 1) * pageSize;
            if (this.startRow < 0) {
                startRow = 0;
            }
            if (this.currentPage == 0 || this.currentPage < 0) {
                currentPage = 1;
            }
        } catch (Exception e) {
        }
        if (currentPage > 2) {
            viewBegin = currentPage - 2;
        }
        viewEnd = totalPage;
        if ((totalPage - currentPage) > 2) {
            viewEnd = currentPage + 2;
        }
    }

}
