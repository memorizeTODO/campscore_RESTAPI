package com.team5.campscore.model;

import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchResponse<T> {
    private Meta meta;
    private List<T> content; 

    @Getter
    @AllArgsConstructor
    public static class Meta {
        private long totalCount;
        private int totalPages;
        private int currentPage;
        private int pageSize;
        private boolean hasNext;
    }
}