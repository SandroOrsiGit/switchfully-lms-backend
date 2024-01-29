package com.switchfully.switchfullylmsbackend.repositories;

import com.switchfully.switchfullylmsbackend.entities.ClassGroup;
import com.switchfully.switchfullylmsbackend.entities.CodelabProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CodelabProgressRepository extends JpaRepository<CodelabProgress, Long> {

    List<CodelabProgress> findByCodelabId(Long id);
}