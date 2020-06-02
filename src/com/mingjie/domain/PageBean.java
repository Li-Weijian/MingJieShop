package com.mingjie.domain;

import java.util.List;

/**
 * @Author:Liweijian
 * @Description:
 */
public class PageBean<T> {

    private int currentPage;
    private int currentCount;
    private int totalPage;
    private int totalCount;
    private List<T> list;

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public List<T> getList() {
        return list;
    }
}
