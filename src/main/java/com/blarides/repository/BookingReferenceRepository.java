package com.blarides.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.blarides.domain.entity.BookingReference;

import java.util.List;

public interface BookingReferenceRepository extends CrudRepository<BookingReference, Long> {
    List<BookingReference> findAll();
    List<BookingReference> findByAccountType(String accountType);
    List<BookingReference> findByAccountType(String accountType, Sort sort);
    List<BookingReference> findByArrivalIgnoreCaseContainingAndDepartureIgnoreCaseContainingAndDateForSearchAndAccountType(String arrival, String departure, String date, String accountType, Sort sort);
    List<BookingReference> findByArrivalIgnoreCaseContainingAndDepartureIgnoreCaseContainingAndAccountType(String arrival, String departure, String accountType, Sort sort);
}
