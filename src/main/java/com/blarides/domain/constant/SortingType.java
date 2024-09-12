package com.blarides.domain.constant;

import org.springframework.data.domain.Sort;

public class SortingType {
    public static final Sort sortByDateASC = Sort.by(Sort.Order.asc("date"));
    public static final Sort sortByPriceASC = Sort.by(Sort.Order.asc("price"));
    public static final Sort sortByDateDESC = Sort.by(Sort.Order.desc("date"));
    public static final Sort sortByPriceDESC = Sort.by(Sort.Order.desc("price"));

    private SortingType() {
        // empty to avoid instantiating this constant class
    }
}

