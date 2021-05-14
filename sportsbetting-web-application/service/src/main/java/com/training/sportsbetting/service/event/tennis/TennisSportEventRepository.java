package com.training.sportsbetting.service.event.tennis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.sportsbetting.domain.TennisSportEvent;

@Repository
interface TennisSportEventRepository extends JpaRepository<TennisSportEvent, Long> {

}
