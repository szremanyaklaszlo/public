package com.training.sportsbetting.service.event;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.training.sportsbetting.domain.SportEvent;

@Repository
interface SportEventRepository extends JpaRepository<SportEvent, Long> {

    @Query(value = "SELECT se FROM SportEvent se WHERE se.startTime > now()")
    List<SportEvent> findActiveSportEvents();

    @Query(value = "SELECT se FROM SportEvent se WHERE se.startTime > now()")
    Page<SportEvent> findActiveSportEvents(Pageable pageable);

    @Query(value = "SELECT se FROM SportEvent se WHERE se.startTime > now() AND se.title LIKE %?1%")
    Page<SportEvent> findActiveSportEventsByFilter(String tableSearchFilter, Pageable pageable);

    @Query(value = "SELECT se FROM SportEvent se WHERE se.id = ?1")
    Optional<SportEvent> findById(Long id);

}
