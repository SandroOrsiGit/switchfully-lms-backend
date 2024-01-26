package com.switchfully.switchfullylmsbackend.repositories;

import com.switchfully.switchfullylmsbackend.entities.Codelab;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CodelabRepository extends JpaRepository<Codelab, Long> {
    List<Codelab> findByModuleId(Long moduleId);
}
