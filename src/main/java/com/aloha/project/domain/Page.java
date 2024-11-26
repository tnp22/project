package com.aloha.project.domain;

import lombok.Data;

@Data
public class Page {
    private static final long PAGE_NUM = 1;
    private static final long SIZES = 10;
    private static final long COUNT = 10;

    private long page;
    private long size;
    private long count;
    private long total;

    private long start;
    private long end;
    private long first;
    private long last;

    private long prev;
    private long next;

    private long index;

    public Page() {
        this(0);
    }
    public Page(long total) {
        this(PAGE_NUM, total);
    }
    public Page(long page, long total) {
        this(page, SIZES, COUNT, total);
    }
    public Page(long page, long size, long count, long total) {
        this.page = page;
        this.size = size;
        this.count = count;
        this.total = total;
        calc();
    }

    public void setTotal(long total) {
        this.total = total;
        calc();
    }

    public void calc() {
        // 첫 번호
        this.first = 1;
        // 마지막 번호
        this.last = (this.total - 1) / size + 1;
        // 시작 번호
        this.start = ( (page-1) / count ) * count + 1;
        // 끝 번호
        this.end = ( (page-1) / count + 1 ) * count;
        if( this.end > this.last ) this.end = this.last;

        // 이전 번호
        this.prev = this.page - 1;
        // 다음 번호
        this.next = this.page + 1;
        // 데이터 순서 번호
        this.index = (this.page - 1) * this.size;
    }
}
