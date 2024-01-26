package com.switchfully.switchfullylmsbackend.repositories;

import com.switchfully.switchfullylmsbackend.entities.ClassGroup;
import com.switchfully.switchfullylmsbackend.entities.CodelabProgress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodelabProgressRepository extends JpaRepository<CodelabProgress, Long> {

    CodelabProgress findByCodelabId(Long id);
}
