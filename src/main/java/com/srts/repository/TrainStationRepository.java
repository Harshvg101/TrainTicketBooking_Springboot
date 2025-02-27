package com.srts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.srts.entity.TrainStation;

public interface TrainStationRepository extends JpaRepository<TrainStation, Long> {
}
