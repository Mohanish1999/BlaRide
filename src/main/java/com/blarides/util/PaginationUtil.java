package com.blarides.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.blarides.domain.entity.BookingReference;

import java.util.List;
import java.util.Optional;

@Component
public class PaginationUtil {

    private static final int PAGE_SIZE = 5;
    private static final int INITIAL_PAGE = 0;

    public Page<BookingReference> getPagedReferences(final Optional<Integer> page, List<BookingReference> bookingReferences) {
        final int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        Pageable pageable = PageRequest.of(evalPage, PAGE_SIZE, Sort.unsorted());
        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), bookingReferences.size());
        List<BookingReference> subList = bookingReferences.subList(start, end);
        return new PageImpl<>(subList, pageable, bookingReferences.size());
    }
}
