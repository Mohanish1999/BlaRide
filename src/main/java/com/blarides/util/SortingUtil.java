package com.blarides.util;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.blarides.domain.constant.SortingType;

@Component
public class SortingUtil {

    public static Sort getSortType (String sortMethod) {
        Sort sortType = null;
        switch(sortMethod) {
            case "date-asc" :
                sortType = SortingType.sortByDateASC;
                break;
            case "date-desc" :
                sortType = SortingType.sortByDateDESC;
                break;
            case "price-asc" :
                sortType = SortingType.sortByPriceASC;
                break;
            case "price-desc" :
                sortType = SortingType.sortByPriceDESC;
                break;
        }
        return sortType;
    }
}
