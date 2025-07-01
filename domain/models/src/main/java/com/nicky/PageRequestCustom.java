package com.nicky;

public class PageRequestCustom {
    private int page;
    private int size;

    public PageRequestCustom() {
    }

    public PageRequestCustom(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
