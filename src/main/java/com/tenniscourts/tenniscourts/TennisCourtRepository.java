package com.tenniscourts.tenniscourts;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TennisCourtRepository extends JpaRepository<TennisCourt, Long> {
    Optional<TennisCourt> findByName(String tennisCourtName);

    //List<TennisCourtDTO> findAvailableTimeSlots(Long tennisCourtId);
}
